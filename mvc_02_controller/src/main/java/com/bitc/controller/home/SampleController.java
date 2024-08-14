package com.bitc.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.vo.ProductVO;

@Controller
public class SampleController {
	
	// GET, POST 전송 방식에 상관없이 doA 요청 처리
	@RequestMapping("doA")
	public String doA() {
		System.out.println("doA Call");
		// /WEB-INF/views/doA.jsp
		return "doA";
	}
	
	// /WEB-INF/views/doB.jsp
	@RequestMapping("doB")
	public void doB() {
		System.out.println("doB Call");
	}
	
	@RequestMapping("doC")
	public String doC(Model model, HttpServletRequest request) {
		// doC(model);
		model.addAttribute("msg", "doC model data");
		request.setAttribute("msgRequest", "doC request data");
		// /WEB-INF/views/result.jsp
		return "result";
	}
	
	/* <a href="doD?msg=doDGetRequest">doD</a> */
	@RequestMapping(value="doD", method = RequestMethod.GET)
	public String doD(HttpServletRequest request) {
		String msg = request.getParameter("msg");
		request.setAttribute("msg", msg);
		return "result";
	}
	
	@RequestMapping(value="doD", method = RequestMethod.POST)
	public String doD(
			@RequestParam (name = "msg", required = true, defaultValue = "default msg") String message, 
			String name, int price, Model model) {
		System.out.println("name : " + name);
		System.out.println("price : " + price);
		model.addAttribute("msg", message);
		ProductVO product = new ProductVO();
		product.setName(name);
		product.setPrice(price);
		model.addAttribute("product", product);
		return "result";
	}
	
	@RequestMapping(value="doH", method = RequestMethod.POST)
	public String doH(Model model, ProductVO product) {
		// key 값이 생략되면 class 이름이 key 값으로 등록
		// class 명과 구분하기 위하여 첫 글자만 소문자로 변경
		model.addAttribute(product); // ("productVO", product)
		return "result";
	}
	
	// Get 방식으로 product 요청 시 처리
	@GetMapping("product")
	public ModelAndView product() {
		ModelAndView mav = new ModelAndView();
		ProductVO product = new ProductVO();
		product.setNum(1);
		product.setName("TV");
		product.setPrice(2500000);
		
		mav.addObject("product", product);
		
		// view 이름 지정
		mav.setViewName("product");
		// /WEB-INF/views/product.jsp
		return mav;
	}
	
	// Post 방식으로 product 요청 처리
	@PostMapping("product")
	public ModelAndView product(int num, String name, int price, ModelAndView mav, ProductVO vo) {
		mav.addObject("product", vo);
		// productVO
		mav.addObject(new ProductVO(num, name, price));
		mav.setViewName("product");
		return mav;
	}
	
	@GetMapping("redirect")
	public String redirect() {
		return "redirect:main.home";
	}
	
}
