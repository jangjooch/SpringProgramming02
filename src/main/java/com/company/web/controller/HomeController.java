package com.company.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web_ch09_01.service.Ch09CommonService;


// @Controller 는 Annotation이라 하며 행동 양식을 설정하는 것이라 생각하면 된다.
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// HomeController.class에서 사용할 log에 대한 작업
	
	@Autowired
	private Ch09CommonService commonService;
	// root-context에서 생성되었기 때문에 전체적으로 사용이 가능하다.
	// 만약 servlet-context_ch09_01에서 생성되었다면 사용되지 못했을 것이다.
	// ContextLoadListener의 bean으로 선언되었기에 @Service가 직접적으로 이어지지 않아도 사용할 수 있다.
	
	// RequestMapping()은 해당 Path로 들어오는 요청이 있을 경우 해당 메소드를 실행토록 하는 것이다.
	// 우리는 web.xml에서 Dispatcher를 설정하는 과정이 있었기에 자동적으로 첫 요청은 /로 오게 되기때문에
	// 자동적으로 "home"값을 반환한다.
	@RequestMapping("/")
	public String home() {
		logger.info("HOMECONTROLLER ACTIVATE");
		commonService.method();
		return "home";
		// 이는 곧 /WEB-INF/views/XXX.jsp 의 XXX에 home이 들어가게 된다.
		// 이에 대한 설정은
		/* servlet-context.xml에서 설정된
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jsp" />
		</bean>
		
		 */
	}
	
	// 즉 아래의 메소드는 
	// URL http://localhost:8080/SpringProgramming02/info
	// 이 요청될 시 실행된다.
	@RequestMapping("/info")
	public String info() {
		return "info";
	}
	
}
