package com.company.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web.service.Ch09Member;
import com.company.web.service.Ch09MemberService01;
import com.company.web.service.Ch09MemberService02;
import com.company.web.service.Ch09Service_injection;
import com.company.web.service.Ch09Service_injection02;
import com.company.web.service.Ch09Service_injection03;
import com.company.web.service.Ch09Service_injection04;
import com.company.web.service.Ch09Service_injection05;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	// getInsert() 에서 Ch09Service_injection의 getInsert를 실행시키기 위한 생성
	private Ch09Service_injection service;
	public void setCh09service(Ch09Service_injection service) {
		this.service = service;
	}


	@RequestMapping("/getInsert")
	public String getInsert() {
		logger.info("Ch09Controller method getInert() activate");
		service.getInsert();
		return "redirect:/ch09/content";
	}
	
	private Ch09Service_injection02 service02;
	public void setCh09service2(Ch09Service_injection02 service2) {
		this.service02 = service2;
	}
	@RequestMapping("/getInsert2")
	public String getInsert2() {
		logger.info("Ch09Controller method getInert2() activate");
		service02.getInsert2();
		return "redirect:/ch09/content";
	}
	
	private Ch09Service_injection03 service03;
	public void setService3(Ch09Service_injection03 sesrvice03) {
		this.service03 = sesrvice03;
	}
	@RequestMapping("/getInsert3")
	public String getInsert3() {
		logger.info("Ch09Controller method getInsert3() activate");
		service03.getInsert3();
		return "redirect:/ch09/content";
	}
	
	private String strData;
	public void setStrData(String strData) {
		this.strData = strData;
	}
	@RequestMapping("/showData")
	public String showStrData() {
		logger.info(strData);
		return "redirect:/ch09/content";
	}
	
	private Ch09Service_injection04 service04;
	@Autowired
	// property 설정 간략화
	public void setService3(Ch09Service_injection04 sesrvice04) {
		this.service04 = sesrvice04;
	}
	// 위는 setter 주입, 아래는 field 주입
	@Autowired
	private Ch09Service_injection04 service04_F;
	// 위의 과정을 이렇게 하여 필드 주입 을 진행하여도 된다.
	
	@RequestMapping("/getInsert4")
	public String getInsert4() {
		logger.info("Ch09Controller method getInsert4() activate");
		service04.getInsert4();
		return "redirect:/ch09/content";
	}
	
	@Autowired
	private Ch09Service_injection05 service05;
	@RequestMapping("/getInsert5")
	public String getInsert5() {
		logger.info("Ch09Controller method getInsert5() activate");
		service05.getInsert5();
		return "redirect:/ch09/content";
	}
	
	/*
	@Autowired
	private Ch09MemberService01 member01;	
	@Autowired
	private Ch09MemberService02 member02;
	*/
	
	@Resource(name = "ch09MemberService01")
	private Ch09Member memberInterface01;
	// 이렇듯 Interface를 구현가능하다.
	// ch09MemberService01의 앞자리가 대문자인것을 가져와(=Ch09MemberService01)
	// 구현하도록 한다. 왜냐면 Interface는 하나의 방향으로만 구현이 가능한 것이 아니기 때문이다.
	// 혹은 name에 @Service("id이름")을 통하여 자체적으로 설정으로 구현이 가능하다.
	
	
}
