package com.bitc.di.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitc.di.service.TestService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * context:component-scan 으로 Bean으로 등록되는 @Annotation
 * @Controller, @Service, @Repository
 * @Component 그 외에 의미를 두지 않는 Bean 을 등록하고 관리하기 위한 annotation
 * @Component 의 하위 형태로 @Controller, @Service, @Repository 가 추가되어 있음
 */

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	// @Autowired		// 의존성 주입
	
	private final TestService ts; // = new TestService();
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("HomeController home() 호출");
		System.out.println("testService : " + ts);
		
		if (ts != null) {
			ts.testService("HomeController Call");
		} else {
			System.out.println("HomeController ts is null");
		}
		return "home";
	}
	
}
