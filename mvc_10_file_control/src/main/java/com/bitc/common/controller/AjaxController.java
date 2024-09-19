package com.bitc.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.common.utils.FileUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AjaxController {

	private final String uploadDir;
	
	
	@PostConstruct
	public void initDir() {}
	
	
	@PostMapping("uploadFiles")
	public ResponseEntity<List<String>> uploadFiles(
				List<MultipartFile> files
			) throws Exception {
	
		List<String> names = new ArrayList<>();
		
		for (MultipartFile f : files) {
			String name = FileUtils.uploadFile(uploadDir, f);
			names.add(name);
		}
		
		return new ResponseEntity<>(names, HttpStatus.OK);
		
	}
}
