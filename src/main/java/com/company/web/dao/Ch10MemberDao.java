package com.company.web.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.web.dto.Ch10Member;

@Component
public class Ch10MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Ch10Member selectMember(String mid) {
		Ch10Member member = sqlSessionTemplate.selectOne("member.selectMemberByMid",mid);
		// sqlSessionTemplate에 등록된 Mybatis파일의 member.xml의 
		// namespace.sql생성문 id, 변수에 넣을 값
		// 
		return member;
	}

	public int insert(Ch10Member member) {
		// TODO Auto-generated method stub
		int rows = sqlSessionTemplate.insert("member.insertMember", member);
		return rows;
	}
	
}
