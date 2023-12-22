package com.human.thymeleaf.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.human.thymeleaf.service.ScheduleService;
import com.human.thymeleaf.util.SchedUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleController {
	private String category = "board";
	@Autowired private ScheduleService scheduleService;
	@Autowired private SchedUtil schedUtil;
	
	@GetMapping(value = {"/board/calendar/{arrow}", "/board/calendar"})
	public String calendar(@PathVariable(required = false) String arrow, HttpSession session, Model model) {
		LocalDate today = LocalDate.now();
		
		model.addAttribute("menu", "calendar");
		model.addAttribute("category", category);
		return "board/calendar";
	}
}
