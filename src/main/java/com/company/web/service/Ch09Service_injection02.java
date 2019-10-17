package com.company.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.web.dao.Ch09Dao_injection;


public class Ch09Service_injection02 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service_injection02.class);
	
	
	public Ch09Service_injection02() {
		logger.info("Ch09Service02 Created");
	}
	
	private Ch09Dao_injection dao;
	// 아직 구현안됨. 즉 null 상태이다.
	// 단순히 private Ch09Dao_injection dao = new Ch09Dao_injection()
	// 를 통해 만들 수 있지만 이렇게 되면 service를 생성할 때마다 dao를 생성한다.
		
	// setter를 이용한 주입
	public void setCh09dao2(Ch09Dao_injection ch09dao) {
		logger.info("Ch09Service02 method setDao2() activate");
		this.dao = ch09dao;
	}
	
	public void getInsert2() {
		logger.info("Ch09Service02 method getInsert2() activate");
		dao.insert();
	}
	
	
	
}
