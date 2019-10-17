package com.company.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.web.dao.Ch09Dao_injection;

public class Ch09Service_injection03 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service_injection03.class);
	
	public Ch09Service_injection03() {
		logger.info("Ch09Service_injection03 Created");
	}
	
	private Ch09Dao_injection dao;
	
	public void setDao(Ch09Dao_injection dao) {
		logger.info("Ch09Service_injection03 setDao activate");
		this.dao = dao;
	}
	public void getInsert3() {
		logger.info("Ch09Service_injection03 method getInsert3 activate");
		dao.insert();
	}
}
