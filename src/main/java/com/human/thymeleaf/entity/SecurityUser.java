package com.human.thymeleaf.entity;

import java.time.LocalDate;

public class SecurityUser {
	private int suid;
	private String email;
	private String pwd;
	private String suname;
	private String nickname;
	private String provider;
	private String providerId;
	private LocalDate regDate;
	private String role;
	
	public SecurityUser() { }
	public SecurityUser(String email, String pwd, String suname, String nickname, String provider, String providerId,
			String role) {
		this.email = email;
		this.pwd = pwd;
		this.suname = suname;
		this.nickname = nickname;
		this.provider = provider;
		this.providerId = providerId;
		this.role = role;
	}
	public SecurityUser(int suid, String email, String pwd, String suname, String nickname, String provider,
			String providerId, LocalDate regDate, String role) {
		this.suid = suid;
		this.email = email;
		this.pwd = pwd;
		this.suname = suname;
		this.nickname = nickname;
		this.provider = provider;
		this.providerId = providerId;
		this.regDate = regDate;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "SecurityUser [suid=" + suid + ", email=" + email + ", pwd=" + pwd + ", suname=" + suname + ", nickname="
				+ nickname + ", provider=" + provider + ", providerId=" + providerId + ", regDate=" + regDate
				+ ", role=" + role + "]";
	}
	
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSuname() {
		return suname;
	}
	public void setSuname(String suname) {
		this.suname = suname;
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
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
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
}
