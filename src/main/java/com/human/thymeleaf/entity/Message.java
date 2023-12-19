package com.human.thymeleaf.entity;

import java.time.LocalDateTime;

public class Message {
	private int mid;
	private int srcSuid;
	private int dstSuid;
	private String content;
	private int status;
	private LocalDateTime genTime;
	private String srcName;
	private String dstName;
	private String srcProfile;
	
	public Message() { }
	public Message(int srcSuid, int dstSuid, String content) {
		this.srcSuid = srcSuid;
		this.dstSuid = dstSuid;
		this.content = content;
	}
	public Message(int mid, int srcSuid, int dstSuid, String content, int status, LocalDateTime genTime) {
		this.mid = mid;
		this.srcSuid = srcSuid;
		this.dstSuid = dstSuid;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
	}
	public Message(int mid, int srcSuid, int dstSuid, String content, int status, LocalDateTime genTime,
			String srcName, String srcProfile) {
		this.srcSuid = srcSuid;
		this.dstSuid = dstSuid;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
		this.srcName = srcName;
		this.srcProfile = srcProfile;
	}
	public Message(int mid, int srcSuid, int dstSuid, String content, int status, LocalDateTime genTime, 
			String srcName, String dstName, String srcProfile) {
		this.mid = mid;
		this.srcSuid = srcSuid;
		this.dstSuid = dstSuid;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
		this.srcName = srcName;
		this.dstName = dstName;
		this.srcProfile = srcProfile;
	}
	
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", srcSuid=" + srcSuid + ", dstSuid=" + dstSuid + ", content=" + content
				+ ", status=" + status + ", genTime=" + genTime + ", srcName=" + srcName + ", dstName=" + dstName
				+ ", srcProfile=" + srcProfile + "]";
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getSrcSuid() {
		return srcSuid;
	}
	public void setSrcSuid(int srcSuid) {
		this.srcSuid = srcSuid;
	}
	public int getDstSuid() {
		return dstSuid;
	}
	public void setDstSuid(int dstSuid) {
		this.dstSuid = dstSuid;
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
	public String getSrcName() {
		return srcName;
	}
	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}
	public String getDstName() {
		return dstName;
	}
	public void setDstName(String dstName) {
		this.dstName = dstName;
	}
	public String getSrcProfile() {
		return srcProfile;
	}
	public void setSrcProfile(String srcProfile) {
		this.srcProfile = srcProfile;
	}
}
