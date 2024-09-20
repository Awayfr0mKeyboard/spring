package com.bitc.user.controller;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.common.utils.FileUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final String uploadDir;
	
	@PostConstruct
	public void init() {
		File file = new File(uploadDir);
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("UserController 초기화 완료");
	}
	
	
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/user/joinPost")
	public String joinPost(
				MultipartFile profileImage
			) throws Exception{
		System.out.println(profileImage);
		boolean isEmpty = profileImage.isEmpty();
		System.out.println(isEmpty);
		if(!isEmpty){
			String u_profile = FileUtils.uploadFile(uploadDir, profileImage);
			System.out.println(u_profile);
		}
		return "redirect:/user/login";
	}

}







