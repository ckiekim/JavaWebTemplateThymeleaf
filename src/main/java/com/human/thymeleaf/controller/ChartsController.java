package com.human.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charts")
public class ChartsController {
	private String category = "charts";
	
	@GetMapping("/chartjs")
	public String chartjs(Model model) {
		model.addAttribute("menu", "chartjs");
		model.addAttribute("category", category);
		return "charts/chartjs";
	}
	
	@GetMapping("/apexcharts")
	public String apexcharts(Model model) {
		model.addAttribute("menu", "apexcharts");
		model.addAttribute("category", category);
		return "charts/apexcharts";
	}
	
	@GetMapping("/echarts")
	public String echarts(Model model) {
		model.addAttribute("menu", "echarts");
		model.addAttribute("category", category);
		return "charts/echarts";
	}
	
}
