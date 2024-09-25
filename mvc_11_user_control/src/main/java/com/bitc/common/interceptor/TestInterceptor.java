package com.bitc.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor{

	/**
	 * DispatcherServlet이 Controller Mapping Method를 호출 하기 전 실행
	 * @return mapping method 호출 여부 true - 호출 , false 미호출
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("== TestInterceptor preHandle START ==");
		
		HandlerMethod method = (HandlerMethod)handler;
		System.out.println("controller : " + method.getBean());
		System.out.println("mapping method : " + method.getMethod());
		System.out.println("요청 전송방식 : " + request.getMethod());
		
		System.out.println("== TestInterceptor preHandle END ==");
		// return HandlerInterceptor.super.preHandle(request, response, handler);
		return true;
	}

	/**
	 * Controller에 Mapping Method가 실행 완료 되고 난 후
	 * DispatcherServlet으로 반환 값이 전달 되기 전에 호출
	 */
	@Override
	public void postHandle(HttpServletRequest request, 
				HttpServletResponse response, 
				Object handler,
				ModelAndView modelAndView) throws Exception {
		System.out.println("== TestInterceptor postHandle START ==");
		
		if(modelAndView == null) {
			System.out.println("response body");
			return;
		}
		
		Map<String, Object> map = modelAndView.getModel();
		for(String key : map.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		System.out.println("view : " + modelAndView.getViewName());
		
		System.out.println("== TestInterceptor postHandle END ==");
	}

	/**
	 * response 응답 정보가 filter로 전달되기 전 호출
	 * DispatcherServlet의 응답(출력)이 완료되고 난 후 호출
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("== TestInterceptor afterCompletion START ==");
		
		
		System.out.println("== TestInterceptor afterCompletion END ==");
	}

	
	
}




