package com.company.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web.dto.Ch06Board;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("ch06 activate");
		return "ch06/content";
	}
	
	
	@PostMapping("/login")
	public String login(String memberId,String memberPassword, HttpSession session) {
		// 파라미터의 key값을 그대로 받는다.
		logger.info("login method activate");
		String result = "";
		if(memberId.equals("admin")) {
			if(memberPassword.equals("12345")) {
				result = "success";
			}
			else {
				result = "wrongPassword";
			}
		}
		else {
			result="wrongId";
		}
		session.setAttribute("result", result);
		// Model이나 Request로 저장한다면 response하고 난 뒤에
		// 정보가 유지되지 않기에 저장범위가 넓은 Session에 저장한다.
		return "redirect:/ch06/content";	
		// 위의 redirect:/ch06/content을 통하여 재요청 응답을 통하여 되돌아간다.
		// 즉 다시 /ch06/content를 통하여 content()가 실행 될 것이다.
		// redirect가 실행되지 않는다면 login url이 계속 유지되어 상태가 유지되지 않을 것이다.
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("result");
		return "redirect:/ch06/content";
	}
	
	@RequestMapping("fileDownload")
	public void download(String filename, HttpServletResponse response, HttpServletRequest request ) throws UnsupportedEncodingException {
		// 굳이 파일을 받는 과정에서 .jsp로 이동할 필요가 없기때문
		// 즉 현재 메소드에서 응답할 필요가 없다면 .jsp로의 이동이 없어도 되기에
		// 혹은 없어야되기에 return이 없도록 한다.
		logger.info(filename);
		
		// 보통 넘어노는 Request Http는 getHeader(String)를
		// 통하여 String을 매개로 하여금 특정 헤더를 가져올 수 잇다.
		// 또한 Body의 데이터는 HttpServletRequest는
		// InputStream과 Reader를 통해 받을 수 있다.
		
		// 응답을 만들기 위하여 HttpServletResponse를 통하여 응답 Http를 만든다.
		// 보통의 response에서는 text타입이지만 현재의 트리거는 image파일이 전달된다
		// 그렇기에 HttpServletRequest를 통하여 어떠한 것을 받는지 판별한다. 이후
		// body또한 image파일임을 확인하면
		// header에서의 context-type은 image/png or image/jpeg가 와야한다.
		// 고로 body에서는 response에서 OutputStream과 Writer를 받아 body에 저장하거나
		// header는 setHeader를 통하여 header를 재 구축한다.
		
		// 바이너리 데이터는 image, video, file, json, xml 이 있다.
		// 위의 것들은 OutputStream을 통하여 전달토록해야한다.
		// json이나 xml은 .jsp를 통하여 만들어 보낼수도 있지만, 메소드에서 생성하여 보낼 수 있다.
		
		// 응답 헤더 추가
		// response.addHeader("Content-Type", "image/jpeg");
		// text/html;charset=UTF-8, application.json,
		// image/jpeg 처럼 Content-Type 설정이 가능하다.
		// 혹은 이렇게 해도 됨.
		// response.setContentType("image/jpeg");
		ServletContext application = request.getServletContext();
		String mimeType = application.getMimeType(filename);
		response.setHeader("Content-Type", mimeType);
		// 이미지 파일의 경우에도 png, jpg 등 많은 종류가 있기에
		// 미리 확장자를 알아내어 이를 Content-Type에 배정한다.
		

		String userAgent = request.getHeader("User-Agent");
		// 현재 작동중인 브라우져의 종류를 알기 위함이다.
		String encodfilename = null;
		if(userAgent.contains("Trident/7.0") || userAgent.contains("MSIE"))
			// IE11 이하 버전에서 한글파일을 복원하기 위해 사용
			// 앞의 것은 11, 뒤의 것은 10이하
			encodfilename = URLEncoder.encode(filename,"UTF-8");
		else
			// 아래의 코드는 Webkit 기반 브라우져에서 한글파일을 복원하는 코드이다.
			// Chrome, Safari, Firfox, Opera, Edge
			encodfilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		logger.info(userAgent);
		logger.info(encodfilename);
		// filename이 한글이여도 encoding을 UTF-8로 바꾸어 출력토록 한다.
		response.setHeader("Content-Disposition", "attachment;filename=\""+ encodfilename + "\"");
		// Content-Disposition = attachment 는 파일을 다운로드 할 수 있도록 하는 것이다.
		// 이후 전달받음 이미지 파일을 다운로드 되고, 정해질 파일의 이름은 filename.jpg이다.
		
		String imagePath = application.getRealPath("/resources/image/"+filename);
		// 이미지 파일의 절대 주소를 가져오도록 한다. 자세한 것은 밑에 있다.
		File file = new File(imagePath);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		// 파일의 사이즈 정하기
		// String.valueOf(file.length())
		// 지정된 파일의 크기를 알 수 있다.
		
		// 본문에 데이터 추가
		try {
			OutputStream os = response.getOutputStream();
			// 이렇듯 HttpResponse를 통해 구현해야 하는 경우가 있다.
			// 이미지를 불러오기 위하여 절대경로가 필요하다.
			
			imagePath = application.getRealPath("/resources/image/"+filename);
			logger.info(imagePath);
			// 컴파일된 war파일 내부에 있을 image파일의 절대 주소를 가져온다.
			// ServletContext는 웹 서버에서 접근하면 생성되어 유지된다.
			// 즉 war파일에 대한 직접적인 접근이 가능하다.
			InputStream is = new FileInputStream(imagePath);
			// 이미지는 바이너리로서 가져올 수 있기에 바이트 배열로 구현한다.
			byte[] buffer = new byte[1024];
			while(true) {
				int readbyte = is.read(buffer);
				if(readbyte==-1)
					// 더이상 읽을 것이 존재하지 않을 경우
					break;
				os.write(buffer,0,readbyte);
			}
			os.flush();
			os.close();
			is.close();
//			PrintWriter pw = response.getWriter();
//			pw.print("<!DOCTYPE html>");
//			pw.print("<html>"
//					+ "<body>"
//					+ "Hello"
//					+ "</body>"
//					+ "</html>");
//			pw.flush();
//			pw.close();
			// html의 형태의 경우
			// pw.print("\"result\":\"ok\"");
			// json타입은 위와같이 하기만 하면 된다.
			// 이렇게 html을 만들어 보낼 수 있다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	int count01;
	int count02;
	
	
	@RequestMapping("/jsonDownLoad01")
	public String jspJson(Model model) {
		
		count01++;
		
		Ch06Board board = new Ch06Board();
		board.setNumber(100);
		board.setTitle("JSP Json Title");
		board.setContent("JSP Json Content");
		board.setHitcount(count01);
		board.setDate(new Date());
		board.setWriter("JSP Json Writer");
		
		model.addAttribute("board",board);
		
		return "ch06/jspJson";
	}
	
	@RequestMapping("/jsonDownLoad02")
	public void controllerJson(HttpServletResponse response) {
		
		count02++;
		
		Ch06Board board = new Ch06Board();
		board.setNumber(1000);
		board.setTitle("Controller Json Title");
		board.setContent("Controller Json Content");
		board.setHitcount(count02);
		board.setDate(new Date());
		board.setWriter("Controller Json Writer");
		
		response.setContentType("application/json;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("number", board.getNumber());
		jsonObject.put("title", board.getTitle());
		jsonObject.put("content", board.getContent());
		jsonObject.put("hitcount", board.getHitcount());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jsonObject.put("date", sdf.format(board.getDate()));
		jsonObject.put("writer", board.getWriter());
		
		String jsonStr = jsonObject.toString();
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.write(jsonStr);
		pw.flush();
		pw.close();
		
	}
	
}
