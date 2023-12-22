package com.human.thymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.AnniversaryDaoOracle;
import com.human.thymeleaf.db.ScheduleDaoOracle;
import com.human.thymeleaf.entity.Anniversary;
import com.human.thymeleaf.entity.SchDay;
import com.human.thymeleaf.entity.Schedule;

@Service
public class ScheduleServiceOracleImpl implements ScheduleService {

	@Autowired private ScheduleDaoOracle schedDao;
	@Autowired private AnniversaryDaoOracle annivDao;
	
	@Override
	public Schedule getSchedule(int sid) {
		Schedule sched = schedDao.getSchedule(sid);
		return sched;
	}

	@Override
	public List<Schedule> getDaySchedList(int suid, String sdate) {
		List<Schedule> list = schedDao.getSchedList(suid, sdate, sdate);
		return list;
	}

	@Override
	public List<Schedule> getMonthSchedList(int suid, String month, int lastDay) {
		String startDay = month + "01";
		String endDay = month + lastDay;
		List<Schedule> list = schedDao.getSchedList(suid, startDay, endDay);
		return list;
	}

	@Override
	public List<Schedule> getCalendarSchedList(int suid, String startDate, String endDate) {
		List<Schedule> list = schedDao.getSchedList(suid, startDate, endDate);
		return list;
	}

	@Override
	public SchDay generateSchDay(int suid, String sdate, int date, int isOtherMonth) {
		List<Anniversary> annivList = annivDao.getAnnivList(suid, sdate, sdate);
		List<Schedule> schedList = schedDao.getSchedList(suid, sdate, sdate);
		int day = Integer.parseInt(sdate.substring(6));
		int isHoliday = 0;
		List<String> aList = new ArrayList<>();
		for (Anniversary a: annivList) {
			aList.add(a.getAname());
			if (isHoliday == 0)
				isHoliday = a.getIsHoliday();
		}
		SchDay schDay = new SchDay(day, date, isHoliday, isOtherMonth, sdate, aList, schedList);
		return schDay;
	}

	@Override
	public void insert(Schedule schedule) {
		schedDao.insert(schedule);
	}

	@Override
	public void update(Schedule schedule) {
		schedDao.update(schedule);
	}

	@Override
	public void delete(int sid) {
		schedDao.delete(sid);
	}

}
