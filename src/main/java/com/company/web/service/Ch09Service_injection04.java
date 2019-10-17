package com.company.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.web.dao.Ch09Dao_injection;
import com.company.web.dao.Ch09Dao_injection02;

@Component("service04")
// 관리 객체화. 앵간하면 설정하지 마라. 겹치면 오류난다. 찾기도 힘들고
// 이것을 @Service로 대체 할 수 있다.
public class Ch09Service_injection04 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service_injection04.class);
	
	public Ch09Service_injection04() {
		logger.info("Ch09Service_injection04 Created");
	}
	
	
	@Autowired
	// 자동적으로 엮어준다. 즉 servlet-context의 <property>와 같은 역할이다. 자동적으로 연결된다.
	private Ch09Dao_injection02 dao2;
	// 이렇게 되면 자동적으로 dao2가 자동적으로 구현이 이루어진다.
	// 그냥 필드 위에 @Autowired 하면 된다. 즉 필드 주입 이다.
	
	public void getInsert4() {
		logger.info("Ch09Service_injection04 method getInsert4 activate");
		dao2.insert2();
	}
}
