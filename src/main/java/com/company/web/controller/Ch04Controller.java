package com.company.web.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch04")
public class Ch04Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch04Controller.class);
	
	@RequestMapping("/content")
	public String content(@RequestHeader("User-Agent") String userAgent,
			HttpServletRequest request) {
		logger.info("User-Agent : " + userAgent);
		
		
		String browser = null;
		if(userAgent.contains("Chrome")) {
			browser = "Chrome";
		}
		else if(userAgent.contains("Trident")){
			browser = "IE 11";
		}
		request.setAttribute("browser", browser);
		
		return "ch04/content";
	}
	
	@RequestMapping("/setCookie")
	public String setCookie(HttpServletResponse response,String mname) {
		//mname = URLEncoder.encode(mname,"UTF-8");
		// 위와 같이 강제적으로 encoding이 가능하다
		Cookie cookie = new Cookie("mname", mname);
		// 입력받은 Parameter를 mname으로 전달받아 key값 mname의 쿠키를 생성
		// new Cookie(Key, Value);
		///cookie.setMaxAge(365*24*60*60);
		// cookie의 생명주기 설정. 1년 내내 저장
		// 파일 시스템 어딘가에 cookie를 유지하기 위해 저장
		// cookie.setMaxAge(0);
		// 이렇게 되면 페이지 종료와 동시에 사라진다.
		// 즉 저장되지 않는다.
		response.addCookie(cookie);
		// response에 쿠키를 추가
		// 즉 입력 받은 것에 대한 요청에 따라 Cookie를 반응한다.
		return "ch04/content";
	}
	
	@RequestMapping("/getCookie")
	public String getCookie(HttpServletRequest request) {
		// @CookieValue String name
		// name Key값을 가진 Cookie를 매개변수로 잡는다.
		
		Cookie[] cookies = request.getCookies();
		// request.getCookies() 는 전달된 Cookie의 배열을 반환한다.
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("mname")) {
					// Cookie의 Key값이 mname이라면
					request.setAttribute("mname", cookie.getValue());
				}
			}
		}		
		// setAttribute로 저장하고 이를 getCookie.jsp로 전달한다.
		return "ch04/getCookie";
	}
}
