package com.company.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.company.web.service.Ch09Service_injection;

@Component("ch09Dao02")
// 위의 것이 있다면 해당 클래스는 관리객체가 된다는 뜻이다. 즉 servlet-context에서 설정하지 않아도 된다.
// 매게변수가 id가 된다. 디폴트는 ch09Dao_injection02 이다.
// 근데 어차피 다 자동적으로 이루어지기에 디폴트로 사용하는 것이 가장 좋다.
public class Ch09Dao_injection02 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service_injection.class);
	
	public Ch09Dao_injection02() {
		logger.info("Ch09Dao02 Created");
	}
	
	public void insert2() {
		logger.info("Ch09Dao02 method insert() activate");
	}
	
}
