package com.human.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/components")
public class ComponentsController {
	private String category = "components";
	
	@GetMapping("/alerts")
	public String alerts(Model model) {
		model.addAttribute("menu", "alerts");
		model.addAttribute("category", category);
		return "components/alerts";
	}
	@GetMapping("/accordion")
	public String accordion(Model model) {
		model.addAttribute("menu", "accordion");
		model.addAttribute("category", category);
		return "components/accordion";
	}
	@GetMapping("/badges")
	public String badges(Model model) {
		model.addAttribute("menu", "badges");
		model.addAttribute("category", category);
		return "components/badges";
	}
	@GetMapping("/breadcrumbs")
	public String breadcrumbs(Model model) {
		model.addAttribute("menu", "breadcrumbs");
		model.addAttribute("category", category);
		return "components/breadcrumbs";
	}
	@GetMapping("/buttons")
	public String buttons(Model model) {
		model.addAttribute("menu", "buttons");
		model.addAttribute("category", category);
		return "components/buttons";
	}
	@GetMapping("/cards")
	public String cards(Model model) {
		model.addAttribute("menu", "cards");
		model.addAttribute("category", category);
		return "components/cards";
	}
	@GetMapping("/carousel")
	public String carousel(Model model) {
		model.addAttribute("menu", "carousel");
		model.addAttribute("category", category);
		return "components/carousel";
	}
	@GetMapping("/list-group")
	public String listGroup(Model model) {
		model.addAttribute("menu", "list-group");
		model.addAttribute("category", category);
		return "components/list-group";
	}
	@GetMapping("/modal")
	public String modal(Model model) {
		model.addAttribute("menu", "modal");
		model.addAttribute("category", category);
		return "components/modal";
	}
	@GetMapping("/tabs")
	public String tabs(Model model) {
		model.addAttribute("menu", "tabs");
		model.addAttribute("category", category);
		return "components/tabs";
	}
	@GetMapping("/pagination")
	public String pagination(Model model) {
		model.addAttribute("menu", "pagination");
		model.addAttribute("category", category);
		return "components/pagination";
	}
	@GetMapping("/progress")
	public String progress(Model model) {
		model.addAttribute("menu", "progress");
		model.addAttribute("category", category);
		return "components/progress";
	}
	@GetMapping("/spinners")
	public String spinners(Model model) {
		model.addAttribute("menu", "spinners");
		model.addAttribute("category", category);
		return "components/spinners";
	}
	@GetMapping("/tooltips")
	public String tooltips(Model model) {
		model.addAttribute("menu", "tooltips");
		model.addAttribute("category", category);
		return "components/tooltips";
	}
	
}
