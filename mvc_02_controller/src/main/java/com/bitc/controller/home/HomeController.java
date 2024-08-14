package com.bitc.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * component-scan 으로 Spring Bean 으로 등록되는 annotation
 * 
 * @Controller - Presentation Layer 에서 Controller 를 명시하기 위해 사용
 * @Service - Business Layer Service 를 명시
 * @Repository - 저장소 처리 class DAO 를 명시하기 위해 사용
 * 
 * @Component - Bean 
 * Component 란 재사용이 가능한 각각의 독립된 모듈을 의미하며 Spring 에서 Spring 관리하에
 * 지정된 범위 내에서 재사용 가능한 Bean class 를 의미
 */
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
