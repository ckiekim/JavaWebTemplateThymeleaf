package com.human.thymeleaf.entity;

import java.time.LocalDateTime;

public class Message {
	private int mid;
	private String mfrom;
	private String mto;
	private String content;
	private int status;
	private LocalDateTime genTime;
	private String fromName;
	private String fromProfile;
	
	public Message() { }
	public Message(String mfrom, String mto, String content) {
		this.mfrom = mfrom;
		this.mto = mto;
		this.content = content;
	}
	public Message(int mid, String mfrom, String mto, String content, int status, LocalDateTime genTime) {
		this.mid = mid;
		this.mfrom = mfrom;
		this.mto = mto;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
	}
	public Message(int mid, String mfrom, String mto, String content, int status, LocalDateTime genTime,
			String fromName, String fromProfile) {
		this.mid = mid;
		this.mfrom = mfrom;
		this.mto = mto;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
		this.fromName = fromName;
		this.fromProfile = fromProfile;
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMfrom() {
		return mfrom;
	}
	public void setMfrom(String mfrom) {
		this.mfrom = mfrom;
	}
	public String getMto() {
		return mto;
	}
	public void setMto(String mto) {
		this.mto = mto;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getGenTime() {
		return genTime;
	}
	public void setGenTime(LocalDateTime genTime) {
		this.genTime = genTime;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromProfile() {
		return fromProfile;
	}
	public void setFromProfile(String fromProfile) {
		this.fromProfile = fromProfile;
	}
	
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", mfrom=" + mfrom + ", mto=" + mto + ", content=" + content + ", status="
				+ status + ", genTime=" + genTime + ", fromName=" + fromName + ", fromProfile=" + fromProfile + "]";
	}
}
