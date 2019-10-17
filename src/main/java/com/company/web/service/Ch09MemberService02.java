package com.company.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Ch09MemberService02 implements Ch09Member{
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09MemberService02.class);
	
	@Override
	public void join() {
		// TODO Auto-generated method stub
		logger.info("MemberService02 Join");
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		logger.info("MemberService02 Login");
	}
	
	
}
