package com.bitc.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "result";
	}
}
