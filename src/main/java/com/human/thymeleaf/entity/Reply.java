package com.human.thymeleaf.entity;

import java.time.LocalDateTime;

public class Reply {
	private int rid;
	private int suid;
	private int bid;
	private String content;
	private LocalDateTime regTime;
	private int isMine;
	private String suName;
	
	public Reply() { }
	public Reply(int suid, int bid, String content, int isMine) {
		this.suid = suid;
		this.bid = bid;
		this.content = content;
		this.isMine = isMine;
	}
	public Reply(int rid, int suid, int bid, String content, LocalDateTime regTime, int isMine, String suName) {
		this.rid = rid;
		this.suid = suid;
		this.bid = bid;
		this.content = content;
		this.regTime = regTime;
		this.isMine = isMine;
		this.suName = suName;
	}
	
	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", suid=" + suid + ", bid=" + bid + ", content=" + content + ", regTime=" + regTime
				+ ", isMine=" + isMine + ", suName=" + suName + "]";
	}
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getRegTime() {
		return regTime;
	}
	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}
	public int getIsMine() {
		return isMine;
	}
	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
	}
}
