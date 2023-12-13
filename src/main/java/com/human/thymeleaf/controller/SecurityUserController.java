package com.human.thymeleaf.controller;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.thymeleaf.auth.PrincipalDetails;
import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.service.SecurityUserService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/security-user")
public class SecurityUserController {
	@Autowired private SecurityUserService securityUserService;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@ResponseBody
	@GetMapping("/success")
	public String success(HttpSession session) {
//		Enumeration<String> attributeNames = session.getAttributeNames();
//		while (attributeNames.hasMoreElements()) {
//			String attribute = attributeNames.nextElement();
//			System.out.println(attribute);			// SPRING_SECURITY_CONTEXT
//		}
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		System.out.println(context.getAuthentication());
		PrincipalDetails detail = (PrincipalDetails) context.getAuthentication().getPrincipal();
		System.out.println("username: " + detail.getUsername());
		return "<h1>success</h1><br>" + context.getAuthentication();
	}
	
	@ResponseBody
	@GetMapping("/admin")
	public String admin() {
		return "<h1>관리자 페이지</h1>";
	}
	
	@Secured("ROLE_ADMIN")
	@ResponseBody
	@GetMapping("/info")
	public String info() {
		return "<h1>개인정보 페이지</h1>";
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
		log.info("loginForm()");
		return "securityUser/login";
	}
	
//	@PostMapping("/login")
//	public String loginProc(String suname, String pwd) {
//		System.out.println("suname: " + suname + ", pwd: " + pwd);
//		return "redirect:/security-user/success";
//	}
	
}
