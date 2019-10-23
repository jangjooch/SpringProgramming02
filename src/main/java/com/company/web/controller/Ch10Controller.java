package com.company.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.web.dto.Ch10Board;
import com.company.web.dto.Ch10Member;
import com.company.web.service.Ch10LoginResult;
import com.company.web.service.Ch10Service;

@Controller
@RequestMapping("/ch10")
public class Ch10Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch10Controller.class);
	
	// @Autowired
	// private DataSource datasource;
	
	@Resource(name="dataSource")
	// root-context에 등록된 bean 구현 객체의 id가 dataSource인 것을 가져온다.
	private DataSource dataSource;
	
	
	@RequestMapping("/content")
	public String content() {
		return "ch10/content";
	}
	
	@RequestMapping("/connectionTest")
	public void connectionTest(HttpServletResponse response) {
		logger.info("connectionTest Activate");
		
		boolean result = false;
		
		// Connection Pool에서 Connection 대여
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(conn != null) {
				// dataSource가 성공적으로 Connection을 반환하였을 경우
				// 즉 설정이 정상적으로 작동하였을 경우를 뜻한다.
				result = true;
			}
			// Connection Pool로 Connection을 반납
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 반환형이 void이기에 response에 html을 심어준다.
		try {
			response.setContentType("application/json; charset=UTF-8");
			// json형태로 보내기 위해 ContentType 설정
			PrintWriter pw;
			pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", result);
			// json key값 value값 생성
			pw.print(jsonObject.toString());
			// pw에 json을 String으로 바꾸어 전송.
			// 이렇게 설정된 html
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	// root-context.xml에서 bean으로 생성한 관리객체를 가져온다.
	
	@RequestMapping("/getMemberByMid")
	public String getMemberByMid(String mid, Model model) {
		// 매개 mid는 .jsp의 script로 전달될 것이다.
		
		Ch10Member member = sqlSessionTemplate.selectOne("member.selectMemberByMid", mid);
		// "member.selectMemberByMid"는 member.xml의 namespace값.사용할 select id
		// root-context에 저장된 sqlSesstionTemplate에서 mybatis.xml이 저장된 위치를 찾고
		// 해당위치에서 namespace조회하여 해당 myBatis.xml을 찾고 그 MyBatis.xml에서
		// 어떠한 select를 사용할 것이지 정하는 것이다.
		// selectOne은 단일 객체만 받기때문에 selectOne을 사용하는 것이다.
		
		// Ch10Member member = sqlSessionTemplate.selectList(mid);
		// 이것은 List로 반환된 내용을 가져온다.
		// 이거을 사용하기 위해서는 각 정보의 저장이 배열형태여야 한다.
		
		model.addAttribute("member",member);
		
		
		return "ch10/getMemberByMid";
		// dto의 값을 초기화 하고 이동
		// ch10/getMemberByMid.jsp로 이동
	}
	
	@Autowired
	private Ch10Service ch10service;
	
	@RequestMapping("/boardList")
	public String boardList(Model model, @RequestParam(defaultValue = "1")int pageNumber, HttpSession session) {
		// pageNumber가 초기화가 이루어 지지 않았을 경우 실행토록
		
		//페이지당 행수
		int rowsPerPage = 10;
		//이전, 다음을 클릭했을 때 나오는 페이지 수
		int pagesPerGroup = 5;
		//전체 게시물 수
		int totalRowNum = ch10service.getTotalRowNum();
		//전체 페이지 수
		int totalPageNum = totalRowNum / rowsPerPage;
		if(totalRowNum % rowsPerPage != 0) totalPageNum++;
		//전체 그룹 수
		int totalGroupNum = totalPageNum / pagesPerGroup;
		if(totalPageNum % pagesPerGroup != 0) totalGroupNum++;
		
		//현재 페이지의 그룹번호
		int groupNo = (pageNumber-1)/pagesPerGroup + 1;
		//현재 그룹의 시작 페이지 번호
		int startPageNo = (groupNo-1)*pagesPerGroup + 1;
		//현재 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNum) endPageNo = totalPageNum;
		
		//현재 페이지의 시작 행번호
		int startRowNo = (pageNumber-1)*rowsPerPage + 1;
		//현재 페이지의 끝 행번호
		int endRowNo = pageNumber*rowsPerPage;
		if(pageNumber == totalPageNum) endRowNo = totalRowNum;
		
		//현재 페이지의 게시물 가져오기
		List<Ch10Board> boardList = ch10service.getBoardList(startRowNo, endRowNo);
		
		//JSP로 페이지 정보 넘기기
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("totalGroupNum", totalGroupNum);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNumber", pageNumber);
		session.setAttribute("pageNumber", pageNumber);
		// Detail이후 다시 boardList로 갈때 pageNumber를 유지하기 위해서
		
		model.addAttribute("boardList", boardList);
		// 실제 출력할 List형의 데이터
		return "ch10/boardList";
	}
	
	@RequestMapping("write100Board")
	public String write100Board() {
		for(int i = 0 ; i < 100 ; i++) {
			Ch10Board board = new Ch10Board();
			board.setBtitle("btitle" + i);
			board.setBcontent("bcontent" + i);
			board.setBwriter("KIM");
			ch10service.writeBoardList(board);
		}
		return "redirect:/ch10/boardList";
		// 추가 이후 진행된 사항을 출력하기 위함.
	}
	
	@RequestMapping("writeBoardForm")
	public String writeBoardForm(HttpSession session) {
		// 로그인 되어있는 지 확인
		// String mid = (String)session.getAttribute("mid");
		if(session.getAttribute("mid") == null) {
			return "redirect:/ch10/loginForm";
		}
		
		// writeForm으로 이동
		return "ch10/writeBoardForm";
		// 이후 Form에서 writeBoard가 실행되도록 하는 것이다.
	}
	
	@RequestMapping("writeBoard")
	public String writeBoard(Ch10Board ch10board, HttpSession session) {
		
		ch10board.setBwriter((String)session.getAttribute("mid"));
		
		// writeForm에서 저장되는 파라미터 키값 btitle, bwriter, bcontent를 이용하여
		// 자동적으로 이에 해당하는 Ch10Board의 변수에 맞는 형태에 저장되어 매계변수로 받아 사용한다.

		logger.info("Before DAO : "  + ch10board.getBnumber());
		int insertedRows = ch10service.writeBoardList(ch10board);
		// service를 통해 insert sql을 만들기 위해 작성된 내용이 저장된 ch10board를 전달
		logger.info("After DAO : "  + ch10board.getBnumber());
		logger.info("Changed rows : " + insertedRows);
		// 여기서 ch10board.getBnumber()로 값을 전달받기 위하여
		//<selectKey keyProperty="bnumber" resultType="int" order="BEFORE">
		//	select SEQUENCE1.nextval from dual
		//</selectKey>
		// 를 통하여 insert에서 실행되는 SQL의 bnumber 값을 미리 전달받아 저장하여 ch10board에 유지 되도록 한다. 
		// 또한 변경된 정보들의 수를 가져와 변경된 정보들만 출력이 가능하게 할 수 있다.
		
		
		return "redirect:/ch10/boardList";
		// 만약 return "/ch10/boardList"만 되어있다면
		// URL이 writeBoard로 되어있는 상태에서 boardList의 화면이 나와있기 때문에
		// 새로고침을 하는 경우 write가 다시 중복되는 일이 발생한다. 그렇기 때문에
		// 이를 방지하기 위하여 현재 이미 진행된 내용을 수행하고
		// URL까지 boardList로 바꾸어 주기 위하여
		// redirect:/ch10/boardList를 사용하여
		// writeBoard를 수행하고 boardList를 출력한 다음에
		// ch10/boardList URL을 다시 수행토록하는 것이다. 즉
		// 자동적으로 새로고침이 이루어지도록 하는 것이다.
	}
	
	
	@RequestMapping("loginForm")
	public String loginForm(String error, Model model) {
		// get방식으로 error가 넘어올 경우
		// 즉 로그인이 성공하면 get으로 넘어오는 것이 없기에 error에 NULL값이지만
		// 그렇지 않을 경우 error 메세지가 있기에 출력이 가능하다.
		if(error != null) {
			if(error.equals("fail_id")) {
				model.addAttribute("Id_error","InCorrect ID");
			}
			else if(error.equals("fail_pw")) {
				model.addAttribute("Pw_error","InCorrect PW");
			}
			// model에 저장해서 넘긴다.
		}
		return "ch10/loginForm";
	}
	
	@PostMapping("login")
	public String login(String mid, String mpassword, HttpSession session) {
		// submit으로 전달받은 id값이 mid와 mpassword를 받는다.
		
		// Serivce에서 DB와 대조하여 확인한다.
		// 상태에 따른 반환을 저장할 것을 받는다.
		// 로그인, ID틀림, PW틀림
		// 아래 방법은 열거타입으로 판단
		Ch10LoginResult result = ch10service.login(mid,mpassword);
		
		if(result == Ch10LoginResult.FailId) {
			// session.setAttribute("error", "InCorrect ID");
			// return "redirect:/ch10/loginForm";
			// session으로 넘겨 줄 수 있다.			
			return "redirect:/ch10/loginForm?error=fail_id";
			// 혹은 그냥 get방식으로 넘겨줄 수 있다.
		}
		else if(result == Ch10LoginResult.FailPw) {
			//session.setAttribute("error", "InCorrect PW");
			//return "redirect:/ch10/loginForm";			
			return "redirect:/ch10/loginForm?error=fail_pw";
		}
		
		
		// ID와 PW가 맞다면 Success일 테니까
		session.setAttribute("mid", mid); // 로그인된 ID 저장
		return "redirect:/ch10/boardList";
	}
	
	@RequestMapping("logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("mid");
		return "redirect:/ch10/boardList";
	}
	
	// <a 태그로 오는 방식은 Get방식으로 요청을 받는다
	@GetMapping("joinIn")
	public String joinForm() {
		return "ch10/joinForm";
	}
	
	// 이후 폼에서 Post로 전달 받음
	@PostMapping("joinIn")
	public String joinIn(Ch10Member member) {
		ch10service.join(member);
		return "redirect:/ch10/loginForm";
	}
	
	@RequestMapping("checkMid")
	public void checkMid(String mid, HttpServletResponse response) throws Exception {
		// json을 만들기 위해 response 받기
		boolean result = ch10service.exists(mid);
		
		response.setContentType("application/json;charset=UTF-8");
		// json을 보내기 위한 pw생성
		PrintWriter pw = response.getWriter();

		// json생성 및 초기화
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("result", result);
		
		// json 보내기
		pw.print(jsonobject.toString());
		
		pw.flush();
		pw.close();	
	}
	
	@RequestMapping("boardDetail")
	public String boardDetail(int bnumber, Model model) {
		
		// hitcount를 늘려줄 service 실행
		ch10service.upHitcount(bnumber);
		
		Ch10Board board = ch10service.getBoard(bnumber);
		
		model.addAttribute("board",board);
		// ch10/boardDetail에서 사용할 수 있게
		return "ch10/boardDetail";
	}
	
	// 처음 <a 태그로 받았을 때,
	@GetMapping("updateBoard")
	public String updateBoardForm(int bnumber, Model model) {
		Ch10Board board = ch10service.getBoard(bnumber);
		// bnumber에 해당하는 보드 가져오고
		model.addAttribute("board", board);
		// 이를 post방식으로 넘겨준다.
		// 기존의 bwriter나 bnumber의 값은 동일해야 하니까
		// 이를 그대로 출력해준다.
		return "ch10/updateBoardForm";
	}
	
	// 위에서 받는다.
	@PostMapping("updateBoard")
	public String updateBoard(Ch10Board board, HttpSession session) {
		
		ch10service.updateBoard(board);
		
		int pageNumber = (Integer)session.getAttribute("pageNumber");
		
		return "redirect:/ch10/boardList?pageNumber="+pageNumber;
	}
	
	@RequestMapping("deleteBoard")
	public String deleteBoard(int bnumber, HttpSession session) {
		
		ch10service.deleteBoard(bnumber);
		
		int pageNumber = (Integer)session.getAttribute("pageNumber");
		return "redirect:/ch10/boardList?pageNumber="+pageNumber;
	}
}
