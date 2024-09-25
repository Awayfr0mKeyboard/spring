package com.bitc.user.service;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.common.utils.FileUtils;
import com.bitc.user.dao.UserDAO;
import com.bitc.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDAO dao;
	
	private final String uploadDir;
	
	@PostConstruct
	public void init() {
		File file = new File(uploadDir);
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("UserServiceImpl 초기화 완료");
	}
	
	@Override
	public String userJoin(UserVO user, MultipartFile profileImage) throws Exception {
		if(profileImage != null && !profileImage.isEmpty()) {
			// profile 이미지 존재
			String u_profile = FileUtils.uploadFile(uploadDir, profileImage);
			user.setU_profile(u_profile);
		}
		int result = dao.userJoin(user);
		
		return result == 1 ? "SUCCESS" : "FAILED";
	}

	@Override
	public UserVO login(String u_id, String u_pw) throws Exception {
		
		return dao.login(u_id, u_pw);
	}

	@Override
	public UserVO getUserById(String u_id) throws Exception {
		return dao.getUserById(u_id);
	}

	@Override
	public void updateVisitDate(String u_id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteYN(String u_id) throws Exception {
		// TODO Auto-generated method stub

	}

}
