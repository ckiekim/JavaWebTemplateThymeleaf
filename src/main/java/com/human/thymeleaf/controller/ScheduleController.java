package com.human.thymeleaf.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.thymeleaf.entity.Anniversary;
import com.human.thymeleaf.entity.SchDay;
import com.human.thymeleaf.entity.Schedule;
import com.human.thymeleaf.service.AnniversaryService;
import com.human.thymeleaf.service.ScheduleService;
import com.human.thymeleaf.util.SchedUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	private String category = "board";
	@Autowired private ScheduleService scheduleService;
	@Autowired private AnniversaryService anniversaryService;
	@Autowired private SchedUtil schedUtil;
	
	@GetMapping(value = {"/calendar/{arrow}", "/calendar"})
	public String calendar(@PathVariable(required = false) String arrow, HttpSession session, Model model) {
		int year = 2000, month = 1;
		LocalDate today = LocalDate.now();
		String date = "일 월 화 수 목 금 토".split(" ")[today.getDayOfWeek().getValue() % 7];
		String sessionMonthYear = (String) session.getAttribute("scheduleMonthYear");
		if (sessionMonthYear == null) {
			year = today.getYear();
			month = today.getMonthValue();
		} else {
			year = Integer.parseInt(sessionMonthYear.substring(0,4));
			month = Integer.parseInt(sessionMonthYear.substring(5));
		}
		
		if (arrow != null) {
			switch(arrow) {
			case "left":
				month = month - 1;
				if (month == 0) {
					month = 12;
					year = year - 1;
				}
				break;
			case "right":
				month = month + 1;
				if (month == 13) {
					month = 1;
					year = year + 1;
				}
				break;
			case "left2":
				year = year - 1;
				break;
			case "right2":
				year = year + 1;
			}
		}
		sessionMonthYear = String.format("%d.%02d", year, month);
		session.setAttribute("scheduleMonthYear", sessionMonthYear);
		int sessSuid = (int) session.getAttribute("sessSuid");
		
		List<SchDay> week = new ArrayList<>();
		List<List<SchDay>> calendar = new ArrayList<>();
		LocalDate startDay = LocalDate.parse(String.format("%d-%02d-01", year, month));
		int startDate = startDay.getDayOfWeek().getValue() % 7;		// 1 ~ 7 사이의 값을 0 ~ 6 사이의 값으로
		LocalDate lastDay = startDay.withDayOfMonth(startDay.lengthOfMonth());
		int lastDate = lastDay.getDayOfWeek().getValue() % 7;
		
		// 아래에서 k는 날짜, i는 요일을 가리킴
		String sdate = null;
		// 첫번째 주
		if (startDate != 0) {
			LocalDate prevSunDay = startDay.minusDays(startDate);
			int prevDay = prevSunDay.getDayOfMonth();
			int prevYear = prevSunDay.getYear();
			int prevMonth = prevSunDay.getMonthValue();
			for (int i=0; i<startDate; i++) {
				sdate = String.format("%d%02d%d", prevYear, prevMonth, prevDay+i);
				week.add(scheduleService.generateSchDay(sessSuid, sdate, i, 1));
			}
		}
		for (int i=startDate, k=1; i<7; i++, k++) {
			sdate = String.format("%d%02d%02d", year, month, k);
			week.add(scheduleService.generateSchDay(sessSuid, sdate, i, 0));
		}
		calendar.add(week);
		
		// 둘째 주부터 해당월의 마지막 날까지
		int day = 8 - startDate;
		for (int k=day, i=0; k<=lastDay.getDayOfMonth(); k++, i++) {
			if (i % 7 == 0)
				week = new ArrayList<>();
			sdate = String.format("%d%02d%02d", year, month, k);
			week.add(scheduleService.generateSchDay(sessSuid, sdate, i % 7, 0));
			if (i % 7 == 6)
				calendar.add(week);
		}
		
		// 마지막 날 다음일부터 다음달 토요일까지
		if (lastDate != 6) {
			LocalDate nextDay = lastDay.plusDays(1);
			int nextYear = nextDay.getYear();
			int nextMonth = nextDay.getMonthValue();
			for (int i=lastDate+1, k=1; i<7; i++, k++) {
				sdate = String.format("%d%02d%02d", nextYear, nextMonth, k);
				week.add(scheduleService.generateSchDay(sessSuid, sdate, i, 1));
			}
			calendar.add(week);
		}
		
		model.addAttribute("calendar", calendar);
		model.addAttribute("today", today + "(" + date + ")");
		model.addAttribute("year", year);
		model.addAttribute("month", String.format("%02d", month));
		model.addAttribute("numberOfWeeks", calendar.size());
		model.addAttribute("height", 600/calendar.size());
		model.addAttribute("todaySdate", String.format("%d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth()));
		model.addAttribute("timeList", schedUtil.genTime());
		model.addAttribute("menu", "calendar");
		model.addAttribute("category", category);
		return "board/calendar";
	}
	
	@PostMapping("/insert")
	public String insert(String importance, String title, String startDate, String startTime, String endDate, String endTime,
							String place, String memo, HttpSession session) {
		int isImportant = (importance == null) ? 0 : 1;
		int sessSuid = (int) session.getAttribute("sessSuid");
		String sdate = startDate.replace("-", "");
		memo = (memo == null) ? "" : memo;
		Schedule schedule = new Schedule(sessSuid, sdate, title, place, startTime, endTime, isImportant, memo);
//		System.out.println(schedule);
		scheduleService.insert(schedule);
		return "redirect:/schedule/calendar";
	}
	
	@ResponseBody
	@GetMapping("/detail/{sid}")
	public String detail(@PathVariable int sid) {
		Schedule sched = scheduleService.getSchedule(sid);
		JSONObject jSched = new JSONObject();
		jSched.put("sid", sid);
		jSched.put("title", sched.getTitle());
		jSched.put("place", sched.getPlace());
		jSched.put("sdate", sched.getSdate());
		jSched.put("startTime", sched.getStartTime());
		jSched.put("endTime", sched.getEndTime());
		jSched.put("isImportant", sched.getIsImportant());
		jSched.put("memo", sched.getMemo());
//		System.out.println(jSched.toString());
		return jSched.toString();
	}
	
	@PostMapping("/update")
	public String update(String importance, int sid, String title, String startDate, String startTime, String endDate, String endTime,
			String place, String memo, HttpSession session) {
		int isImportant = (importance == null) ? 0 : 1;
		int sessSuid = (int) session.getAttribute("sessSuid");
		String sdate = startDate.replace("-", "");
		memo = (memo == null) ? "" : memo;
		Schedule schedule = new Schedule(sid, sessSuid, sdate, title, place, startTime, endTime, isImportant, memo);
		scheduleService.update(schedule);
		return "redirect:/schedule/calendar";
	}
	
	@GetMapping("/delete/{sid}")
	public String delete(@PathVariable int sid) {
		scheduleService.delete(sid);
		return "redirect:/schedule/calendar";
	}
	
	@PostMapping("/insertAnniv")
	public String insertAnniv(String holiday, String aname, String annivDate, HttpSession session) {
		int isHoliday = (holiday == null) ? 0 : 1;
		String adate = annivDate.replace("-", "");
		int sessSuid = (int) session.getAttribute("sessSuid");
		Anniversary anniversary = new Anniversary(sessSuid, aname, adate, isHoliday);
		anniversaryService.insert(anniversary);
		return "redirect:/schedule/calendar";
	}
	
}
