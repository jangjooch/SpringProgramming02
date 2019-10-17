package com.company.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.web.dao.Ch09Dao_injection;
import com.company.web.dao.Ch09Dao_injection02;

@Component
public class Ch09Service_injection05 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service_injection05.class);
	
	public Ch09Service_injection05() {
		logger.info("Ch09Service_injection05 Created");
	}
	
	
	@Autowired	
	private Ch09Dao_injection02 dao2;

	
	public void getInsert5() {
		logger.info("Ch09Service_injection05 method getInsert5 activate");
		dao2.insert2();
	}
}
