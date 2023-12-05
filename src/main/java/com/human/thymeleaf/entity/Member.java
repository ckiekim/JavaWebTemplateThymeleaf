package com.human.thymeleaf.entity;

import java.time.LocalDate;

public class Member {
	private String mid;
	private String mname;
	private String email;
	private LocalDate regDate;
	private String imgPath;
	
	public Member() { }
	public Member(String mid, String mname, String email, String imgPath) {
		this.mid = mid;
		this.mname = mname;
		this.email = email;
		this.imgPath = imgPath;
	}
	public Member(String mid, String mname, String email, LocalDate regDate, String imgPath) {
		this.mid = mid;
		this.mname = mname;
		this.email = email;
		this.regDate = regDate;
		this.imgPath = imgPath;
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", mname=" + mname + ", email=" + email + ", regDate=" + regDate + ", imgPath="
				+ imgPath + "]";
	}
}
