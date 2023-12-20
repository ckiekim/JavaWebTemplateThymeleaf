package com.human.thymeleaf.controller;

import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.human.thymeleaf.entity.Notification;
import com.human.thymeleaf.service.NotificationService;

@Controller
public class ErrorHandlingController implements ErrorController {
	private final String ERROR_404_PAGE_PATH = "error/error404";
	private final String ERROR_500_PAGE_PATH = "error/error500";
	private final String ERROR_ETC_PAGE_PATH = "error/error";
	@Autowired private NotificationService notificationService;
	private final int ADMIN_SUID = 14;
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest req, Model model) {
		// 에러 코드
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		// 에러 코드에 대한 상태 정보
		int statusCode = Integer.valueOf(status.toString());
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		Notification notification = new Notification(ADMIN_SUID, "에러 발생", status + " 에러가 발생하였습니다.");
		notificationService.insertNotification(notification);
		
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
