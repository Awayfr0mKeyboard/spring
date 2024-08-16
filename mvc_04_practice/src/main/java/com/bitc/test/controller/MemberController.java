package com.bitc.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitc.test.model.MemberVO;
import com.bitc.test.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 요청 처리
 */
@Controller
@RequestMapping("member/*")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService ms; // = new MemberServiceImpl();

	// member/join  회원가입 페이지
	@GetMapping("join")
	public void join() {
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		// /WEB-INF/views/member/join.jsp
		// return "member/join";
	}
	
	@GetMapping("login")
	public void login() {}
	
	// 회원가입
	@PostMapping("join")
	public String join(MemberVO member) {
		boolean isJoin = ms.memberJoin(member);
		if (isJoin) {
			return "redirect:/member/login";
		}
		return "member/join";
	}
	
}