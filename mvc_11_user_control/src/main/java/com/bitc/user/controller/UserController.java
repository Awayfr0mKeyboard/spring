package com.bitc.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitc.user.service.UserService;
import com.bitc.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService us;
	
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	// 로그인 페이지 요청 처리
	@GetMapping("/user/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/user/joinPost")
	public String joinPost(
				UserVO user,
				MultipartFile profileImage,
				RedirectAttributes rttr
			) throws Exception{
		System.out.println(profileImage);
		System.out.println(user);
		
		String message = us.userJoin(user, profileImage);
		
		rttr.addFlashAttribute("msg", message);
		
		return "redirect:/user/login";
	}
	
	@PostMapping("/user/login")
	public String login(
			String u_id, String u_pw, boolean rememberme, HttpSession session
			) throws Exception {
		System.out.println(u_id);
		System.out.println(u_pw);
		System.out.println(rememberme);
		UserVO user = us.login(u_id, u_pw);
		session.setAttribute("userInfo", user);
		return "redirect:/";
	}

	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "redirect:/user/login";
	}
}







