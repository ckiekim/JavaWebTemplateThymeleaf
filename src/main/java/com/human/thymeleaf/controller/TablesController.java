package com.human.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tables")
public class TablesController {
	private String category = "tables";
	
	@GetMapping("/general")
	public String general(Model model) {
		model.addAttribute("menu", "general");
		model.addAttribute("category", category);
		return "tables/general";
	}

	@GetMapping("/data")
	public String data(Model model) {
		model.addAttribute("menu", "data");
		model.addAttribute("category", category);
		return "tables/data";
	}
	
}
