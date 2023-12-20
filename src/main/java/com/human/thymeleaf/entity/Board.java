package com.human.thymeleaf.entity;

import java.time.LocalDateTime;

public class Board {
	private int bid;
	private int suid;
	private String title;
	private String content;
	private LocalDateTime modTime;
	private int viewCount;
	private int replyCount;
	private int likeCount;
	private String files;
	private int isDeleted;
	private String suName;
	
	public Board() { }
	public Board(int suid, String title, String content, String files) {
		this.suid = suid;
		this.title = title;
		this.content = content;
		this.files = files;
	}
	public Board(int bid, int suid, String title, String content, String files) {
		this.bid = bid;
		this.suid = suid;
		this.title = title;
		this.content = content;
		this.files = files;
	}
	public Board(int bid, int suid, String title, String content, LocalDateTime modTime, int viewCount, int replyCount,
			int likeCount, String files, int isDeleted, String suName) {
		this.bid = bid;
		this.suid = suid;
		this.title = title;
		this.content = content;
		this.modTime = modTime;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.likeCount = likeCount;
		this.files = files;
		this.isDeleted = isDeleted;
		this.suName = suName;
	}
	
	@Override
	public String toString() {
		return "Board [bid=" + bid + ", suid=" + suid + ", title=" + title + ", content=" + content + ", modTime="
				+ modTime + ", viewCount=" + viewCount + ", replyCount=" + replyCount + ", likeCount=" + likeCount
				+ ", files=" + files + ", isDeleted=" + isDeleted + ", suName=" + suName + "]";
	}
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
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
	public LocalDateTime getModTime() {
		return modTime;
	}
	public void setModTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
	}
}
