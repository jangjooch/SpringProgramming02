package com.company.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch02")
// 이것으로 인하여 기본적으로 /ch02로 들어왔을 경우 작동하게 된다.
// 즉 하위 모든 메소드들은 /ch02를 기본으로 가진다는 것이다
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("ch02 content() activate");
		return "ch02/content";
	}
	// 실질적으로 위의 메소드는 
	// context-root/ch02/content에 접근하였을 때 실행되는 것이다.
	
	// Get방식의 요청일 경우만 작용
	@GetMapping("/getMethod")
	public String getMethod() {
		logger.info("ch02 content() activate");
		return "ch02/contentget";
	}
	
	// 이렇듯 Post방식의 요청일 경우만 작용
	@PostMapping("/postMethod")
	public String postMethod() {
		logger.info("ch02 content() activate");
		return "ch02/contentpost";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		logger.info("ch02 content() activate");
		return "ch02/joinForm";
	}
	
	// form을 통하여 넘어오는 것은 post방식으로 주로 넘어온다.
	@PostMapping("/join")
	public String join() {
		logger.info("ch02 content() activate");
		return "ch02/content";
	}
	
}
