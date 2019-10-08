package com.company.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Ch03DTOMember {

	// 파라미터와 값이 동일해야 한다.
	// 그래야 입력된 정보들이 해당 파라미터 값으로 초기화된다.
	private String mid;
	private String mname;
	private String mpassword;
	private int mage;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date mbirth;
	
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
	public int getMage() {
		return mage;
	}
	public void setMage(int mage) {
		this.mage = mage;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
}
