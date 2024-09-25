package com.bitc.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitc.common.utils.FileUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

	private final String uploadDir;
	
	@GetMapping("displayFile")
	public ResponseEntity<byte[]> displayfile(
			String fileName
			) throws Exception {
		System.out.println("displayFile : " + fileName);
		return new ResponseEntity<>(
				FileUtils.getBytes(uploadDir, fileName),
				FileUtils.getHeaders(fileName),
				HttpStatus.OK);
	}
}
