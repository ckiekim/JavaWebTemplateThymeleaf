package com.human.thymeleaf.controller;

import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandlingController implements ErrorController {
	private final String ERROR_404_PAGE_PATH = "error/error404";
	private final String ERROR_500_PAGE_PATH = "error/error500";
	private final String ERROR_ETC_PAGE_PATH = "error/error";
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest req, Model model) {
		// 에러 코드
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		// 에러 코드에 대한 상태 정보
		int statusCode = Integer.valueOf(status.toString());
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		
		if (status != null) {
			String timestamp = LocalDateTime.now().toString().substring(0, 19).replace("T", " ");
			// 404 error
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", httpStatus.getReasonPhrase());
				model.addAttribute("timestamp", timestamp);
				return ERROR_404_PAGE_PATH;
			}
			// 500 error
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", httpStatus.getReasonPhrase());
				model.addAttribute("timestamp", timestamp);
				return ERROR_500_PAGE_PATH;
			}
		}
		return ERROR_ETC_PAGE_PATH;
	}
}
