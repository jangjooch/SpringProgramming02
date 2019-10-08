package com.company.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
