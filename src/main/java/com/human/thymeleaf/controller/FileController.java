package com.human.thymeleaf.controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	@GetMapping("/profileDownload/{filename}")
	public ResponseEntity<Resource> profileDownload(@PathVariable String filename) {
		String profilePath = uploadDir + "profileUpload/" + filename;
		Path path = Paths.get(profilePath);
		try {
			String contentType = Files.probeContentType(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(
					ContentDisposition.builder("attachment")
						.filename(filename, StandardCharsets.UTF_8).build()
					);
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);
			Resource resource = new InputStreamResource(Files.newInputStream(path));
			return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@PostMapping("/profileUpload")
	public String profileUpload(MultipartFile file, HttpSession session) {
		String filename = file.getOriginalFilename();
		String sessMid = (String) session.getAttribute("sessMid");
		String profilePath = uploadDir + "profileUpload/" + sessMid + "_" + filename;
		try {
			file.transferTo(new File(profilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessMid + "_" + filename;
	}
}
