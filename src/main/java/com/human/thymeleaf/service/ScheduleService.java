package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.SchDay;
import com.human.thymeleaf.entity.Schedule;

public interface ScheduleService {

	Schedule getSchedule(int sid);
	
	List<Schedule> getDaySchedList(int suid, String sdate);
	
	List<Schedule> getMonthSchedList(int suid, String month, int lastDay);
	
	List<Schedule> getCalendarSchedList(int suid, String startDate, String endDate);

	SchDay generateSchDay(int suid, String sdate, int date, int isOtherMonth);
	
	void insert(Schedule schedule);
	
	void update(Schedule schedule);
	
	void delete(int sid);
	
}
