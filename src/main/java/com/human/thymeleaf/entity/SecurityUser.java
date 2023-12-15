package com.human.thymeleaf.entity;

import java.time.LocalDate;

public class SecurityUser {
	private int suid;
	private String suname;
	private String pwd;
	private String email;
	private String nickname;
	private String provider;
	private LocalDate regDate;
	private String role;
	private String imgPath;
	
	public SecurityUser() { }
	public SecurityUser(String suname, String pwd, String email, String nickname, String provider, String imgPath) {
		this.suname = suname;
		this.pwd = pwd;
		this.email = email;
		this.nickname = nickname;
		this.provider = provider;
		this.imgPath = imgPath;
	}
	public SecurityUser(int suid, String suname, String pwd, String email, String nickname, String provider,
			LocalDate regDate, String role, String imgPath) {
		this.suid = suid;
		this.suname = suname;
		this.pwd = pwd;
		this.email = email;
		this.nickname = nickname;
		this.provider = provider;
		this.regDate = regDate;
		this.role = role;
		this.imgPath = imgPath;
	}
	
	@Override
	public String toString() {
		return "SecurityUser [suid=" + suid + ", suname=" + suname + ", pwd=" + pwd + ", email=" + email + ", nickname="
				+ nickname + ", provider=" + provider + ", regDate=" + regDate + ", role=" + role + ", imgPath="
				+ imgPath + "]";
	}
	
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getSuname() {
		return suname;
	}
	public void setSuname(String suname) {
		this.suname = suname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
