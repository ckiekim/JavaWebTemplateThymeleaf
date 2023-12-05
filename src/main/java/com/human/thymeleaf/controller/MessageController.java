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
import com.human.thymeleaf.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	private String category = "message";
	@Autowired private MessageService msgService;
	
	@GetMapping(value = {"/list/{mid}", "/list"})
	public String list(@PathVariable(required=false) String mid, Model model) {
		model.addAttribute("menu", "list");
		model.addAttribute("category", category);
		List<Message> list = null;
		if (mid == null)
			list = msgService.getMessageListAll();
		else
			list = msgService.getMessageList(mid, 0);
		model.addAttribute("messageList", list);
		return "message/list";
	}
	
	@GetMapping(value = {"/write/{mto}", "/write"})
	public String writeForm(@PathVariable(required=false) String mto, Model model) {
		model.addAttribute("menu", "write");
		model.addAttribute("category", category);
		model.addAttribute("mto", mto);
		return "message/write";
	}
	
	@PostMapping("/write")
	public String writeProc(String mto, String content, HttpSession session) {
		String sessMid = (String) session.getAttribute("sessMid");
		Message message = new Message(sessMid, mto, content);
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
		msgService.updateMessage(message);
		model.addAttribute("message", message);
		
		String sessMid = (String) session.getAttribute("sessMid");
		int msgNum = msgService.getMessageSize(sessMid, MessageService.MSG_NEW);
		session.setAttribute("msgNum", msgNum);
		return "message/view";
	}
	
	@GetMapping("/delete/{mid}")
	public String delete(@PathVariable int mid, HttpServletRequest request, HttpSession session) {
		Message message = msgService.getMessage(mid);
		message.setStatus(MessageService.MSG_DELETED);
		msgService.updateMessage(message);
		String referer = request.getParameter("referer");
		
		String sessMid = (String) session.getAttribute("sessMid");
		int msgNum = msgService.getMessageSize(sessMid, MessageService.MSG_NEW);
		session.setAttribute("msgNum", msgNum);
		return "redirect:" + referer;
	}
}
