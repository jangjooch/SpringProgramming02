package com.company.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.company.web.service.Ch09Service_injection;


public class Ch09Dao_injection {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service_injection.class);
	
	public Ch09Dao_injection() {
		logger.info("Ch09Dao Created");
	}
	
	public void insert() {
		logger.info("Ch09Dao method insert() activate");
	}
	
}
