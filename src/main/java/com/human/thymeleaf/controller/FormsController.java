package com.human.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forms")
public class FormsController {
	private String category = "forms";
	
	@GetMapping("/elements")
	public String elements(Model model) {
		model.addAttribute("menu", "elements");
		model.addAttribute("category", category);
		return "forms/elements";
	}
	
	@GetMapping("/layouts")
	public String layouts(Model model) {
		model.addAttribute("menu", "layouts");
		model.addAttribute("category", category);
		return "forms/layouts";
	}
	
	@GetMapping("/editors")
	public String editors(Model model) {
		model.addAttribute("menu", "editors");
		model.addAttribute("category", category);
		return "forms/editors";
	}
	
	@GetMapping("/validation")
	public String validation(Model model) {
		model.addAttribute("menu", "validation");
		model.addAttribute("category", category);
		return "forms/validation";
	}
	
}
