package com.human.thymeleaf.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.thymeleaf.entity.Message;
import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.service.MessageService;
import com.human.thymeleaf.service.SecurityUserService;

@Controller
@RequestMapping("/message")
public class MessageController {
	private String category = "message";
	@Autowired private MessageService msgService;
	@Autowired private SecurityUserService userService;
	
	@GetMapping(value = {"/list/{dstSuid}", "/list"})
	public String list(@PathVariable(required=false) Integer dstSuid, HttpSession session, Model model) {
		model.addAttribute("menu", "list");
		model.addAttribute("category", category);
		List<Message> list = null;
		if (dstSuid == null)
			list = msgService.getMessageListAll();
		else {
			list = msgService.getMessageList(dstSuid, 0);
			String sessMname = (String) session.getAttribute("sessMname");
			list.forEach(x -> x.setDstName(sessMname));
		}
		model.addAttribute("messageList", list);
		return "message/list";
	}
	
	@GetMapping(value = {"/write/{mid}", "/write"})
	public String writeForm(@PathVariable(required=false) Integer mid, HttpSession session, Model model) {
		model.addAttribute("menu", "write");
		model.addAttribute("category", category);
		if (mid == null) {
			List<SecurityUser> list = userService.getSecurityUserList();
			int sessSuid = (Integer) session.getAttribute("sessSuid");
			list.removeIf(x -> x.getSuid() == sessSuid);
			model.addAttribute("userList", list);
			return "message/write-no-to";
		} else {
			Message message = msgService.getMessage(mid);
			model.addAttribute("message", message);
			return "message/write";
		}
	}
	
	@PostMapping("/write")
	public String writeProc(int dstSuid, String content, HttpSession session) {
		int sessSuid = (Integer) session.getAttribute("sessSuid");
		Message message = new Message(sessSuid, dstSuid, content);
		msgService.insertMessage(message);
		return "redirect:/message/list";
	}
	
	@GetMapping("/view/{mid}")
	public String view(@PathVariable int mid, HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("menu", "view");
		model.addAttribute("category", category);
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		
		Message message = msgService.getMessage(mid);
		message.setStatus(MessageService.MSG_READ);
		msgService.updateMessageStatus(message);
		model.addAttribute("message", message);
		
		int sessSuid = (Integer) session.getAttribute("sessSuid");
		List<Message> msgList = msgService.getMessageList(sessSuid, MessageService.MSG_NEW);
		session.setAttribute("msgList", msgList);
		int msgNum = msgService.getMessageSize(sessSuid, MessageService.MSG_NEW);
		session.setAttribute("msgNum", msgNum);
		return "message/view";
	}
	
	@GetMapping("/delete/{mid}")
	public String delete(@PathVariable int mid, HttpServletRequest request, HttpSession session) {
		Message message = msgService.getMessage(mid);
		message.setStatus(MessageService.MSG_DELETED);
		msgService.updateMessageStatus(message);
		String referer = request.getParameter("referer");
		
		int sessSuid = (Integer) session.getAttribute("sessSuid");
		int msgNum = msgService.getMessageSize(sessSuid, MessageService.MSG_NEW);
		session.setAttribute("msgNum", msgNum);
		return "redirect:" + referer;
	}
}
