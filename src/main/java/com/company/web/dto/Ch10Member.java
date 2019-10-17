package com.company.web.dto;

public class Ch10Member {
	
	private String mid;
	private String mname;
	private String mpassword;
	// 데이터 결과 저장을 위한 dto는 이렇듯 private으로 되어
	// setter가 존재하여야 한다. setter가 있어야
	// property로 넘어올 수 있기 때문이다.
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	
	
	
}
