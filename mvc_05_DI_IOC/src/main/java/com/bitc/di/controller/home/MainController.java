package com.bitc.di.controller.home;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc.di.dao.TestDAO;
import com.bitc.di.service.TestService;
import com.bitc.di.vo.TestBoardVO;

import lombok.RequiredArgsConstructor;

/***
 * 	DI(Dependency Injection) - 의존성 주입
 * 	spring 에서 관리되는 bean(instance) 객체를 우리가 원하는 필드에 주입받아
 * 	사용하게 해주는 annotation
 * 
 * 				@Autowired			@Qualifier						@Inject					@Resource
 * 범용성			스프링 전용			스프링 전용						자바에서 지원				자바에서 지원
 * 연결성			타입에 맞춰 주입		Bean의 name을 이용하여 주입			타입에 맞추서 주입			이름으로 검색 후
 * 									@Autowired 와 함께 사용 			@Named를 이용해서			타입으로 검색
 * 									독립적 사용 X						이름으로 제한
 *	
 *		@Inject @Named @Resource - 자바 1.8까지만 기본 포함
 */
@Controller
@RequiredArgsConstructor
public class MainController {

	@Autowired
	private TestService ts;
	
	private final TestDAO td;
	
	@Inject
	@Named("board")
	private TestBoardVO testBoard;
	
	@Inject
	private TestBoardVO testBoardVO;
	
	@Autowired
	private String uploadPath;
	
	/*
	@Autowired
	@Qualifier(value="path")
	*/
	@Resource(name="path")
	private String imgPath;
	
	// Bean 생성 후 의존성 주입이 완료되고 사용할 준비가 되었을 때 최초에 한 번 호출
	@PostConstruct
	public void init() {
		System.out.println("MainController PostConstruct ------");
		System.out.println("imgPath : " + imgPath);
		System.out.println("testBoardVO : " + testBoardVO);
		System.out.println("board : " + testBoard);
		System.out.println("MainController PostConstruct ------");
	}
	
	@GetMapping("main")
	public String main() {
		System.out.println("uploadPath : " + uploadPath);
		System.out.println("imgPath : " + imgPath);
		System.out.println("MainController : " + ts);
		System.out.println("MainController : " + td);
		System.out.println("MainController : " + testBoard);
		return "home";
	}
	
}
