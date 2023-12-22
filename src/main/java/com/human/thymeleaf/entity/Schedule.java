package com.human.thymeleaf.entity;

public class Schedule {
	private int sid;
	private int suid;
	private String sdate;
	private String title;
	private String place;
	private String startTime;
	private String endTime;
	private int isImportant;
	private String memo;
	
	public Schedule() { }
	public Schedule(int suid, String sdate, String title, String place, String startTime, String endTime,
			int isImportant, String memo) {
		this.suid = suid;
		this.sdate = sdate;
		this.title = title;
		this.place = place;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isImportant = isImportant;
		this.memo = memo;
	}
	public Schedule(int sid, int suid, String sdate, String title, String place, String startTime, String endTime,
			int isImportant, String memo) {
		this.sid = sid;
		this.suid = suid;
		this.sdate = sdate;
		this.title = title;
		this.place = place;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isImportant = isImportant;
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "schedule [sid=" + sid + ", suid=" + suid + ", sdate=" + sdate + ", title=" + title + ", place=" + place
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", isImportant=" + isImportant + ", memo="
				+ memo + "]";
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getIsImportant() {
		return isImportant;
	}
	public void setIsImportant(int isImportant) {
		this.isImportant = isImportant;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
