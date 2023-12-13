package com.human.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.service.SecurityUserService;

@Controller
@RequestMapping("/security-user")
public class SecurityUserController {
	@Autowired private SecurityUserService securityUserService;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@ResponseBody
	@GetMapping("/success")
	public String success() {
		return "<h1>success</h1>";
	}
	
	@ResponseBody
	@GetMapping("/admin")
	public String admin() {
		return "<h1>관리자 페이지</h1>";
	}

	@GetMapping("/register")
	public String registerForm() {
		return "securityUser/register";
	}
	
	@PostMapping("/register")
	public String registerProc(String suname, String pwd, String pwd2, String nickname, String email) {
		SecurityUser securityUser = securityUserService.findByName(suname);
		if (securityUser != null || pwd == null || !pwd.equals(pwd2))
			return "securityUser/register";
		String hashedPwd = bCryptPasswordEncoder.encode(pwd);
		securityUser = new SecurityUser(email, hashedPwd, suname, nickname, "", "", "ROLE_USER");
		securityUserService.insertSecurityUser(securityUser);
		return "redirect:/security-user/login";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "securityUser/login";
	}
	
//	@PostMapping("/login")
//	public String loginProc(String suname, String pwd) {
//		System.out.println("suname: " + suname + ", pwd: " + pwd);
//		return "redirect:/security-user/success";
//	}
	
}
