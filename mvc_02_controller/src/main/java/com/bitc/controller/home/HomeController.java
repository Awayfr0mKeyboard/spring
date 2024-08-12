package com.bitc.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="main.home", method=RequestMethod.GET)
	public String home(HttpSession session) {
		session.setAttribute("a", "Home Controller");
		// /WEB-INF/views/home.jsp
		// forward 방식으로 view 화면 처리
		return "home";
	}
	
}
