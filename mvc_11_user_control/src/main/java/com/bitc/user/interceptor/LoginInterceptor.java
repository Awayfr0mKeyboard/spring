package com.bitc.user.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.user.vo.UserVO;

/**
 * 로그인 요청 성공 시
 * 사용자가 로그인 정보 저장 요청을 하였으면
 * 사용자 브라우저에 쿠키 정보 추가
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("LoginInterceptor postHandle START == ");
		
		if(request.getMethod().equalsIgnoreCase("get")) {
			System.out.println("/user/login GET 요청");
			return;
		}
		
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO) session.getAttribute("userInfo");
		
		if (userInfo != null) {
			// 로그인 성공한 회원
			if (request.getParameter("rememberme") != null) {
				Cookie cookie = new Cookie("newJeans", userInfo.getU_id());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(cookie);
			}
		} else {
			// 로그인 실패
			modelAndView.addObject("msg", "로그인 실패");
			modelAndView.setViewName("/user/login");
		} // end 인증 체크
		
		System.out.println("LoginInterceptor postHandle END == ");
	} // end post handle

}
