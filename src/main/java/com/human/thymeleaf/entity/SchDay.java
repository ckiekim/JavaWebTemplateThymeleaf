package com.human.thymeleaf.entity;

import java.util.List;

public class SchDay {
	private int day;
	private int date;		// 요일 (0-일요일, ..., 6-토요일)
	private int isHoliday;
	private int isOtherMonth;
	private String sdate;
	private List<String> annivList;
	private List<Schedule> schedList;
	
	public SchDay() { }
	public SchDay(int day, int date, int isHoliday, int isOtherMonth, String sdate, List<String> annivList,
			List<Schedule> schedList) {
		this.day = day;
		this.date = date;
		this.isHoliday = isHoliday;
		this.isOtherMonth = isOtherMonth;
		this.sdate = sdate;
		this.annivList = annivList;
		this.schedList = schedList;
	}
	
	@Override
	public String toString() {
		return "SchDay [day=" + day + ", date=" + date + ", isHoliday=" + isHoliday + ", isOtherMonth=" + isOtherMonth
				+ ", sdate=" + sdate + ", annivList=" + annivList + ", schedList=" + schedList + "]";
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(int isHoliday) {
		this.isHoliday = isHoliday;
	}
	public int getIsOtherMonth() {
		return isOtherMonth;
	}
	public void setIsOtherMonth(int isOtherMonth) {
		this.isOtherMonth = isOtherMonth;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public List<String> getAnnivList() {
		return annivList;
	}
	public void setAnnivList(List<String> annivList) {
		this.annivList = annivList;
	}
	public List<Schedule> getSchedList() {
		return schedList;
	}
	public void setSchedList(List<Schedule> schedList) {
		this.schedList = schedList;
	}
}
