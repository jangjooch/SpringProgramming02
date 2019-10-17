package com.company.web_ch09_01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web.service.Ch09Member;
import com.company.web.service.Ch09Service_injection05;
import com.company.web_ch09_01.service.Ch09CommonService;


// @Controller 는 Annotation이라 하며 행동 양식을 설정하는 것이라 생각하면 된다.

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private Ch09CommonService commonservice;
	// Ch09CommonService은 
	// 이것을 설정하는 servlet-context_ch09_01에서 
	// <context:component-scan base-package="com.company.web_ch09_01"/> 로 설정다면
	// 여기에서만 사용이 가능하지만 root-context에서 생성되었기 때문에 전체적으로 사용이 가능하다.
	
	
	// @Autowired
	// private Ch09Service_injection05 injection05;
	// 받지 못한다. 이것을 설정하는 
	// private Ch09Service_injection05 injection05;
	// 의 @Autowired가 <context:component-scan base-package="com.company.web/>를 통해서
	// 설정되기 때문이다.
	
	
	@RequestMapping("/dispatcher_ch09_01")
	// 여타 다름없이 @RequestMapping("/")를 하면 인식하지 못하는 경우가 발생한다.
	public String home() {
		logger.info("HOMECONTROLLER ACTIVATE");
		commonservice.method();
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
	
	
	
}
