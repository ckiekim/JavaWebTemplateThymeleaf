package com.human.thymeleaf.controller;

import java.io.File;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.thymeleaf.entity.Member;
import com.human.thymeleaf.entity.Profile;
import com.human.thymeleaf.service.MemberService;

@Controller
@RequestMapping("/members")
public class MembersController {
	private String category = "members";
	@Autowired private MemberService memberService;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("menu", "list");
		model.addAttribute("category", category);
		List<Member> list = memberService.getMemberList();
		model.addAttribute("memberList", list);
		return "members/list";
	}
	
	@GetMapping("/profile")
	public String profile(HttpSession session, Model model) {
		model.addAttribute("menu", "profile");
		model.addAttribute("category", category);
		
		String sessMid = (String) session.getAttribute("sessMid");
		Profile profile = memberService.getProfile(sessMid);
		model.addAttribute("profile", profile);
		return "members/profile";
	}
	
	@PostMapping("/profile")
	public String profileProc(Profile profile, HttpSession session) {
		Member member = new Member(profile.getMid(), profile.getMname(), profile.getEmail(),
									profile.getImgPath());
		session.setAttribute("sessProfile", profile.getImgPath());
		memberService.updateMember(member);
		memberService.updateProfile(profile);
		return "redirect:/members/profile";
	}
	
	@GetMapping("/register")
	public String register() {
		return "members/register";
	}

	@PostMapping("/register")
	public String registerProc(MultipartHttpServletRequest req) {
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");
		String email = req.getParameter("email");
		MultipartFile picture = req.getFile("picture");
		String filename = picture.getOriginalFilename();
		Member existingMember = memberService.getMember(mid);
		if (existingMember == null) {
			String profilePath = uploadDir + "profileUpload/" + mid + "_" + filename;
			try {
				picture.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Member member = new Member(mid, mname, email, mid + "_" + filename);
			memberService.insertMember(member);
			Profile profile = new Profile(mid);
			memberService.insertProfile(profile);
			return "redirect:/members/login";
		} else {
			return "members/register";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "members/login";
	}
	
	@PostMapping("/login")
	public String loginProc(String mid, String pwd, HttpSession session) {
		Member member = memberService.getMember(mid);
		session.setAttribute("sessMid", member.getMid());
		session.setAttribute("sessMname", member.getMname());
		session.setAttribute("sessProfile", member.getImgPath());
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/members/login";
	}
	
}
