package com.company.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingTest.class);
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		logger.debug("Controller Log Level Debug");
		logger.info("Controller Log Level Info");
		logger.warn("Controller Log Level Warn");
		logger.error("Controller Log Level Error");
		// 각기 Log Level에 따른 출력
		
	}
	
}
