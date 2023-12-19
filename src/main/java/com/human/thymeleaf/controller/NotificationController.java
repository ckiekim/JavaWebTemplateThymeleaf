package com.human.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.thymeleaf.entity.Notification;
import com.human.thymeleaf.service.NotificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	@Autowired private NotificationService notificationService;
	
	@GetMapping("/read/{nid}")
	public String read(@PathVariable(required=false) int nid, HttpServletRequest request, HttpSession session) {
		String referer = request.getHeader("Referer");
		Notification notification = notificationService.getNotification(nid);
		notification.setStatus(NotificationService.NOTI_READ);
		notificationService.updateNotification(notification);
		
		int sessSuid = (int) session.getAttribute("sessSuid");
		List<Notification> notiList = notificationService.getNotificationList(sessSuid, NotificationService.NOTI_NEW);
		session.setAttribute("notiList", notiList);
		session.setAttribute("notiNum", notiList.size());
		return "redirect:" + referer;
	}

}
