package com.human.thymeleaf.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.human.thymeleaf.entity.Message;
import com.human.thymeleaf.entity.Notification;
import com.human.thymeleaf.service.MessageService;
import com.human.thymeleaf.service.NotificationService;

@Controller
public class HomeController {
	@Autowired NotificationService notiService;
	@Autowired MessageService msgService;
	
	@GetMapping(value= {"/", "/index"})
	public String index(HttpSession session, Model model) {
		String sessMid, sessMname, sessProfile;
		if (session.getAttribute("sessMid") == null) {
			sessMid = "james";
			sessMname = "James Dean";
			sessProfile = "james.jpg";
			session.setAttribute("sessMid", sessMid);
			session.setAttribute("sessMname", sessMname);
			session.setAttribute("sessProfile", sessProfile);
		}
		sessMid = (String) session.getAttribute("sessMid");
		List<Notification> notiList = notiService.getNotificationList(sessMid, 0);
		List<Message> msgList = msgService.getMessageList(sessMid, MessageService.MSG_NEW);
		int notiNum = notiList.size();
		int msgNum = msgService.getMessageSize(sessMid, MessageService.MSG_NEW);
		session.setAttribute("notiNum", notiNum);
		session.setAttribute("notiList", notiList);
		session.setAttribute("msgNum", msgNum);
		session.setAttribute("msgList", msgList);
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("title", "Thymeleaf Project");
		model.addAttribute("footMsg", "CK World Corp.");
		return "hello";
	}

}
