package com.bitc.comment.controller;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.comment.service.CommentService;
import com.bitc.comment.vo.CommentVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService cs;
	
	// /comments
	@PostMapping("")
	@ResponseBody // 반환값 그자체를 반환함 view 페이지 안 찾고
	public ResponseEntity<String> addComment(
			// @RequestParam("commentAuth") String cAuth, String commentText, int bno) {
			CommentVO comment) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = new MediaType("application", "json", Charset.forName("UTF-8"));
		// application/json; charset=UTF-8
		headers.setContentType(type);
		
		ResponseEntity<String> entity = null;
		try {
			String message = cs.addComment(comment);
			entity = new ResponseEntity<>(message, headers, HttpStatus.OK); // 200
		} catch (Exception e) {
			entity = new ResponseEntity<>(
					e.getMessage(), 
					headers, 
					// HttpStatus.INTERNAL_SERVER_ERROR
					HttpStatus.BAD_REQUEST
			);
		}
		return entity;
	}
	
}
