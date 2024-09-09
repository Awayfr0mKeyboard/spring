package com.bitc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitc.vo.SampleVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// @RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String home() {		
		return "home";
	}
	
	@GetMapping("methodTest")
	public void methodTest() {}
	
	@PostMapping("methodTest")
	public void methodTest(String name, int age, Model model) {
		System.out.println("methodTest POST 호출");
		System.out.println("name : " + name);
		System.out.println("name : " + age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
	}
	
	@RequestMapping(value="methodTest", method=RequestMethod.PUT)
	public void methodTest(SampleVO vo, Model model) {
		System.out.println("PUT : " + vo);
		model.addAttribute("sample", vo);
	}
	
	@GetMapping("javascript")
	public void javascript() {}
}
