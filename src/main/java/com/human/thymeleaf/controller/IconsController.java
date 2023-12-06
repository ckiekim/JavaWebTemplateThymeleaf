package com.human.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/icons")
public class IconsController {
	private String category = "icons";
	
	@GetMapping("/bootstrap")
	public String bootstrap(Model model) {
		model.addAttribute("menu", "bootstrap");
		model.addAttribute("category", category);
		return "icons/bootstrap";
	}
	
	@GetMapping("/remix")
	public String remix(Model model) {
		model.addAttribute("menu", "remix");
		model.addAttribute("category", category);
		return "icons/remix";
	}
	
	@GetMapping("/boxicons")
	public String boxicons(Model model) {
		model.addAttribute("menu", "boxicons");
		model.addAttribute("category", category);
		return "icons/boxicons";
	}
	
}
