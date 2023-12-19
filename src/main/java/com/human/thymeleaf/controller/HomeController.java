package com.human.thymeleaf.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.thymeleaf.auth.PrincipalDetails;
import com.human.thymeleaf.entity.Message;
import com.human.thymeleaf.entity.Notification;
import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.service.MessageService;
import com.human.thymeleaf.service.NotificationService;

@Controller
public class HomeController {
	@Autowired NotificationService notiService;
	@Autowired MessageService msgService;
	
	@GetMapping(value= {"/", "/index"})
	public String index(HttpSession session, Model model,
						@AuthenticationPrincipal PrincipalDetails principalDetails) {
		SecurityUser securityUser = principalDetails.getSecurityUser();
		int sessSuid = securityUser.getSuid();
		String sessMid = securityUser.getSuname();
		session.setAttribute("sessSuid", sessSuid);
		session.setAttribute("sessProvider", securityUser.getProvider());
		session.setAttribute("sessMname", securityUser.getNickname());
		session.setAttribute("sessProfile", securityUser.getImgPath());
		
		List<Notification> notiList = notiService.getNotificationList(sessSuid, NotificationService.NOTI_NEW);
		List<Message> msgList = msgService.getMessageList(sessSuid, MessageService.MSG_NEW);
		int notiNum = notiList.size();
		int msgNum = msgService.getMessageSize(sessSuid, MessageService.MSG_NEW);
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
	
	@ResponseBody
	@GetMapping("/success")
	public String success(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		return "<h1>/success</h1><br>" + principalDetails.getSecurityUser();
	}
	
}
