package com.human.thymeleaf.entity;

import java.time.LocalDateTime;

public class Notification {
	private int nid;
	private int dstSuid;
	private String title;
	private String content;
	private int status;
	private LocalDateTime genTime;
	private String dstName;
	
	public Notification() { }
	public Notification(int dstSuid, String title, String content) {
		this.dstSuid = dstSuid;
		this.title = title;
		this.content = content;
	}
	public Notification(int nid, int dstSuid, String title, String content, int status, LocalDateTime genTime) {
		this.nid = nid;
		this.dstSuid = dstSuid;
		this.title = title;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
	}
	public Notification(int nid, int dstSuid, String title, String content, int status, LocalDateTime genTime,
			String dstName) {
		this.nid = nid;
		this.dstSuid = dstSuid;
		this.title = title;
		this.content = content;
		this.status = status;
		this.genTime = genTime;
		this.dstName = dstName;
	}
	
	@Override
	public String toString() {
		return "Notification [nid=" + nid + ", dstSuid=" + dstSuid + ", title=" + title + ", content=" + content
				+ ", status=" + status + ", genTime=" + genTime + ", dstName=" + dstName + "]";
	}
	
	public String getDstName() {
		return dstName;
	}
	public void setDstName(String dstName) {
		this.dstName = dstName;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getDstSuid() {
		return dstSuid;
	}
	public void setDstSuid(int dstSuid) {
		this.dstSuid = dstSuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
}
