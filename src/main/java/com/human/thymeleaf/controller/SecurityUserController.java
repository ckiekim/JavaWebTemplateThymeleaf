package com.human.thymeleaf.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.human.thymeleaf.auth.PrincipalDetails;
import com.human.thymeleaf.entity.Notification;
import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.entity.UserProfile;
import com.human.thymeleaf.service.NotificationService;
import com.human.thymeleaf.service.SecurityUserService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/security-user")
public class SecurityUserController {
	@Autowired private SecurityUserService securityUserService;
	@Autowired private NotificationService notificationService;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	private final Logger log = LoggerFactory.getLogger(getClass());
	private String category = "user";
	
	@ResponseBody
	@GetMapping("/success")
	public String success(HttpSession session, Authentication authentication,
						@AuthenticationPrincipal PrincipalDetails userDetails) {
//		Enumeration<String> attributeNames = session.getAttributeNames();
//		while (attributeNames.hasMoreElements()) {
//			String attribute = attributeNames.nextElement();
//			System.out.println(attribute);			// SPRING_SECURITY_CONTEXT
//		}
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.debug(context.getAuthentication().toString());
		PrincipalDetails detail = (PrincipalDetails) context.getAuthentication().getPrincipal();
		log.debug("context username: " + detail.getUsername());
		PrincipalDetails principalDetail = (PrincipalDetails) authentication.getPrincipal();
		log.debug("authentication username: " + principalDetail.getUsername());
		log.debug("userDetails username: " + userDetails.getUsername());
		return "<h1>success</h1><br>" + context.getAuthentication()
				+ "<br><br>" + authentication;
	}
	
	// session 영역내에 SPRING_SECURITY_CONTEXT라는 이름으로 Spring Security Session이 존재
	// 이 안에는 Authentication 타입의 데이터만 들어갈 수 있음
	// Authentication type
	//		1. UserDetails : 일반 로그인
	//		2. OAuth2User : 소셜(OAuth2) 로그인
	// 해결책 - 특정한 클래스를 만들고 UserDetails, OAuth2User를 implement 하게 해줌
	// 기존에 UserDetails를 implement 했던 PrincipalDetails class가 OAuth2User도 implement 하도록 변경해주면 됨
	
	@ResponseBody
	@GetMapping("/oauth2success")
	public String oauth2success(Authentication authentication,
						@AuthenticationPrincipal OAuth2User oauth) {
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		log.debug("oAuth2User: " + oAuth2User.getAttributes());
		log.debug("oauth: " + oauth.getAttributes());
		return "<h1>oauth2 success</h1><br>" + oAuth2User.getAttributes() 
				+ "<br><br>" + oauth.getAttributes();
	}
	
	@ResponseBody
	@GetMapping("/both")
	public String both(HttpSession session,  Authentication authentication,
						@AuthenticationPrincipal PrincipalDetails principalDetails) {
//		log.debug("SecurityUser: " + principalDetails.getSecurityUser());
//		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//		System.out.println(context.getAuthentication().toString());
//		PrincipalDetails detail = (PrincipalDetails) context.getAuthentication().getPrincipal();
//		System.out.println("context username: " + detail.getUsername());
		PrincipalDetails principalDetail = (PrincipalDetails) authentication.getPrincipal();
//		System.out.println("authentication username: " + principalDetail.getUsername());
		return "<h1>both success</h1><br>" + "SecurityUser: " + principalDetails.getSecurityUser()
				+ "<br><br>" + authentication + "<br><br>" + principalDetail.getSecurityUser();
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
		return "security-user/register";
	}
	
	@PostMapping("/register")
	public String registerProc(String suname, String pwd, String pwd2, String nickname, String email, MultipartFile picture) {
		SecurityUser securityUser = securityUserService.findByName(suname);
		if (securityUser != null || pwd == null || !pwd.equals(pwd2))
			return "securityUser/register";
		String hashedPwd = bCryptPasswordEncoder.encode(pwd);
		String filename = picture.getOriginalFilename();
		String imgPath = "/file/profileDownload/" + suname + "_" + filename;
		String profilePath = uploadDir + "profileUpload/" + suname + "_" + filename;
		try {
			picture.transferTo(new File(profilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		securityUser = new SecurityUser(suname, hashedPwd, email, nickname, "ck world", imgPath);
		securityUserService.insertSecurityUser(securityUser);
		securityUser = securityUserService.findByName(suname);
		securityUserService.insertUserProfile(securityUser.getSuid());
		
		Notification notification = new Notification(securityUser.getSuid(), "회원가입 환영", "회원 가입을 환영합니다.");
		notificationService.insertNotification(notification);
		return "redirect:/security-user/login";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		log.trace("========================== loginForm()");
		return "security-user/login";
	}
	
	@GetMapping("/profile")
	public String profile(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		log.trace("========================== profile()");
		model.addAttribute("menu", "profile");
		model.addAttribute("category", category);
		
		String suname = principalDetails.getSecurityUser().getSuname();
		UserProfile userProfile = securityUserService.getUserProfile(suname);
		model.addAttribute("profile", userProfile);
		return "security-user/profile";
	}
	
	@PostMapping("/profile")
	public String profileProc(UserProfile userProfile, HttpSession session,
							@AuthenticationPrincipal PrincipalDetails principalDetails) {
		SecurityUser securityUser = principalDetails.getSecurityUser();
		securityUser.setEmail(userProfile.getEmail());
		securityUser.setNickname(userProfile.getNickname());
		securityUser.setImgPath(userProfile.getImgPath());
		session.setAttribute("sessProfile", userProfile.getImgPath());
		securityUserService.updateSecurityUser(securityUser);
		securityUserService.updateUserProfile(userProfile);
		
		Notification notification = new Notification(securityUser.getSuid(), "프로파일 변경", "사용자 프로파일이 변경되었습니다.");
		notificationService.insertNotification(notification);
		List<Notification> notiList = notificationService.getNotificationList(securityUser.getSuid(), NotificationService.NOTI_NEW);
		session.setAttribute("notiNum", notiList.size());
		session.setAttribute("notiList", notiList);
		return "redirect:/security-user/profile";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("menu", "list");
		model.addAttribute("category", category);
		List<SecurityUser> list = securityUserService.getSecurityUserList();
		model.addAttribute("userList", list);
		return "security-user/list";
	}
	
	@PostMapping("/password")
	public String changePassword(String pwd, String newPwd, String newPwd2, HttpSession session,
							@AuthenticationPrincipal PrincipalDetails principalDetails) {
		SecurityUser securityUser = principalDetails.getSecurityUser();
		if (securityUser.getProvider().equals("ck world") &&
				bCryptPasswordEncoder.matches(pwd, securityUser.getPwd()) &&
				newPwd != null && newPwd.length() >= 4 && newPwd.equals(newPwd2)) {
			securityUser.setPwd(bCryptPasswordEncoder.encode(newPwd));
			securityUserService.updateSecurityUser(securityUser);
			
			Notification notification = new Notification(securityUser.getSuid(), "패스워드 변경", "패스워드가 변경되었습니다.");
			notificationService.insertNotification(notification);
			List<Notification> notiList = notificationService.getNotificationList(securityUser.getSuid(), NotificationService.NOTI_NEW);
			session.setAttribute("notiNum", notiList.size());
			session.setAttribute("notiList", notiList);
		}
		return "redirect:/security-user/profile";
	}
	
}
