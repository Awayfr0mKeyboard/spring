package com.bitc.di.controller.second;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc.di.dao.TestDAO;
import com.bitc.di.service.TestService;

@Controller
public class SecondController {
	
	@Autowired(required=false)
	TestService ts;
	
	@Autowired
	TestDAO td;
	
	public SecondController() {
		System.out.println("SecondController() ts : " + ts);
		System.out.println("SecondController() td : " + td);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init() ts : " + ts);
		System.out.println("init() td : " + td);
	}
	
	@GetMapping("main")
	public String doMain() {
		System.out.println("SECOND Controller doMain 호출");
		return "main";
	}
	
}
