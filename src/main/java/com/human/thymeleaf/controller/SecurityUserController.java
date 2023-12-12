package com.human.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.thymeleaf.service.SecurityUserService;

@Controller
@RequestMapping("/security-user")
public class SecurityUserController {
	@Autowired SecurityUserService securityUserService;
	
	@ResponseBody
	@GetMapping("/success")
	public String success() {
		return "success";
	}

	@ResponseBody
	@GetMapping("/register")
	public String registerForm() {
		return "register form";
	}
	
	@ResponseBody
	@PostMapping("/register")
	public String registerProc() {
		return "register proc";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "securityUser/login";
	}
	
	@PostMapping("/login")
	public String loginProc(String suname, String pwd) {
		System.out.println("suname: " + suname + ", pwd: " + pwd);
		return "redirect:/security-user/success";
	}
	
}
