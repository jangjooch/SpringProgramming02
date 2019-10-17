package com.company.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web.dto.Ch10Member;

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
	
	
	
}
