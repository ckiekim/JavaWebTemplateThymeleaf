package com.human.thymeleaf.controller;

import java.net.URI;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/chatbot")
public class ChatbotController {
	private String category = "chatbot";
	@Value("${restApi.counselUrl}") private String counselUrl;
	@Value("${restApi.legalUrl}") private String legalUrl;
	@Value("${restApi.bardUrl}") private String bardUrl;

	@GetMapping("/counsel")
	public String counselForm(Model model) {
		model.addAttribute("menu", "counsel");
		model.addAttribute("category", category);
		return "chatbot/counsel";
	}
	
	@ResponseBody
	@PostMapping("/counsel") 
	public String counselProc(String userInput) throws Exception {
		userInput = URLEncoder.encode(userInput, "utf-8");
		URI uri = new URI(counselUrl + "?userInput=" + userInput);
		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response = rest.getForEntity(uri, String.class);
		return response.getBody();
	}
	
	@GetMapping("/legal")
	public String legalForm(Model model) {
		model.addAttribute("menu", "legal");
		model.addAttribute("category", category);
		return "chatbot/legal";
	}
	
	@ResponseBody
	@PostMapping("/legal")
	public String legalProc(String userInput) throws Exception {
		URI uri = new URI(legalUrl);
		// parameter setting
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("userInput", userInput);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, new HttpHeaders());
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}
	
//	@GetMapping("/bard")
//	public String bardForm(Model model) {
//		model.addAttribute("menu", "bard");
//		model.addAttribute("category", category);
//		return "chatbot/bard";
//	}
//	
//	@ResponseBody
//	@PostMapping("/bard")
//	public String bardProc(String userInput) throws Exception {
//		URI uri = new URI(bardUrl);
//		// parameter setting
//		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//		body.add("userInput", userInput);
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, new HttpHeaders());
//		
//		RestTemplate rest = new RestTemplate();
//		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, entity, String.class);
//		return response.getBody();
//	}
	
}
