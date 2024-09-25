package com.bitc.user.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.bitc.user.service.UserService;
import com.bitc.user.vo.UserVO;

public class CheckCookieInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService us;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CheckCookie START");
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfo") != null) {
			System.out.println("이미 로그인한 사용자");
			return true;
		}
		
		// Cookie[] cookies = request.getCookies();
		// newJeans
		Cookie cookie = WebUtils.getCookie(request, "newJeans");
		if (cookie != null) {
			String u_id = cookie.getValue();
			System.out.println(u_id);
			UserVO user = us.getUserById(u_id);
			session.setAttribute("userInfo", user);
		}
		
		System.out.println("CheckCookie END");
		return true;
	}

	
	
}
