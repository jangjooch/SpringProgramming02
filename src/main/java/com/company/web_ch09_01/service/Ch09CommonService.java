package com.company.web_ch09_01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class Ch09CommonService {
	
	// Annotation을 사용하지 않는 이유는 이 Service의 생성이 root-context에서 선언되었기 때문에
	// 다수의 다른 servlet-context 등에서 사용될 수 있기때문에 각기 쓰임이 달라질 수 있기 때문이다.
	// 즉 유일하지 않기 때문에 @Component를 사용하지 않는다.
	// 그렇지만 다른 곳에서 사용될 때, @AutoWired나 @Resources로 접근이 가능하다. 이미 생성되었기 때문에
	private static final Logger logger = LoggerFactory.getLogger(Ch09CommonService.class);
	
	public void method() {
		logger.info("Ch09CommonService method Activate");
	}
	// 만약 여기에 Ch09Service_injection에서 사용하던 것처럼 dao를 사용한다면
	// 그 dao도 똑같이 root-context에서 생성해 주어야 한다. 그렇지 않다면
	// dao가 선언된 곳에서만 사용될 수 있기 때문이다.
}
