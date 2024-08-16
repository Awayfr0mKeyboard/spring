package com.bitc.test.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.test.dao.MemberDAO;
import com.bitc.test.model.LoginDTO;
import com.bitc.test.model.MemberVO;

import lombok.RequiredArgsConstructor;

@Service("ms")
@RequiredArgsConstructor // lombok
public class MemberServiceImpl implements MemberService {

	// @Autowired
	private final MemberDAO dao; // = new MemberDAOImpl();
	
	@Override
	public boolean memberJoin(MemberVO member) {
		return dao.memberJoin(member);
	}

	@Override
	public boolean memberLogin(LoginDTO dto, HttpServletResponse response) {
		
		return false;
	}

	@Override
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		

	}

}
