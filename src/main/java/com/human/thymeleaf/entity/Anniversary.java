package com.human.thymeleaf.entity;

public class Anniversary {
	private int aid;
	private int suid;
	private String aname;
	private String adate;
	private int isHoliday;
	
	public Anniversary() { }
	public Anniversary(int suid, String aname, String adate, int isHoliday) {
		this.suid = suid;
		this.aname = aname;
		this.adate = adate;
		this.isHoliday = isHoliday;
	}
	public Anniversary(int aid, int suid, String aname, String adate, int isHoliday) {
		this.aid = aid;
		this.suid = suid;
		this.aname = aname;
		this.adate = adate;
		this.isHoliday = isHoliday;
	}
	
	@Override
	public String toString() {
		return "Anniversary [aid=" + aid + ", suid=" + suid + ", aname=" + aname + ", adate=" + adate + ", isHoliday="
				+ isHoliday + "]";
	}
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public int getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(int isHoliday) {
		this.isHoliday = isHoliday;
	}
}
