package com.company.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.web.dto.Ch03DTOMember;

@Controller
@RequestMapping("/ch03")
public class Ch03Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch03/content";
	}
	
	@RequestMapping("/join")
	public String getJoin(String mid, String mname, String mpassword, @RequestParam(defaultValue = "0") int mage, @DateTimeFormat(pattern = "yyyy-MM-dd") Date mbirth) {
		// Parameter의 id를 매개변수 삼아 받는다.
		// Date형식의 파라미터를 바로 Date형식으로 받을 수 없다.
		// 고로 String 타입으로 받거나
		// @DateTimeFormat(pattern = "yyyy-MM-dd") 형태처럼 Date의 출력형태를 지정하여야 한다.
		// 만약 요청 Parameter의 name으로 받은 것이 없다면 null이 배정되는데
		// int형으로 받는다고 한다면 null이 될 수 없기에 오류가 발생한다.
		// 그렇기에 default값을 @RequestParam(defaultValue = "0")을 통하여
		// 설정한다면 값이 넘어오지 않더라도 오류가 발생하지 않을 것이다.
		logger.info("mid 		: " + mid);
		logger.info("mname		: " + mname);
		logger.info("mpassword	: " + mpassword);
		logger.info("mage		: " + mage);
		logger.info("mbirth		: " + mbirth);
		return "ch03/content";
	}
	
	@RequestMapping("/join2")
	public String getJoin2(Ch03DTOMember member) {
		logger.info("join2 activate");
		logger.info("mid 		: " + member.getMid());
		logger.info("mname		: " + member.getMname());
		logger.info("mpassword	: " + member.getMpassword());
		logger.info("mage		: " + member.getMage());
		logger.info("mbirth		: " + member.getMbirth());
		return "ch03/content";
	}
	
	@RequestMapping("/join3")
	public String getJoin3(Ch03DTOMember member,HttpServletRequest request) {
		logger.info("join3 activate");
		logger.info("mid 		: " + member.getMid());
		logger.info("mname		: " + member.getMname());
		request.setAttribute("mid", member.getMid());
		request.setAttribute("mname", member.getMname());
		// 이것을 이용하여 ch03/join.jsp에서의 json을 생성한다.
		return "ch03/join3";
		// views의 ch03/join.jsp 가 필요하다
	}
}
