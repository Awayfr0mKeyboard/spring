package com.bitc.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.bitc.user.vo.UserVO;

public interface UserService {

	/**
	 * @param user - 등록할 사용자 정보
	 * @param profileImage - 사용자가 선택한 프로필 이미지 정보
	 * @return - 회원 가입 성공 여부 메세지
	 */
	String userJoin(UserVO user, MultipartFile profileImage) throws Exception;
	
	/**
	 * 로그인
	 * @param u_id, u_pw - 검증에 필요한 사용자 아이디, 비밀번호
	 * @return - 검증이 완료된 사용자 정보 (null은 실패)  
	 */
	UserVO login(String u_id, String u_pw) throws Exception;
	
	/**
	 * u_id 사용자 아이디로 사용자 정보 검색
	 * @param u_id - 검색할 사용자 아이디
	 * @return - 아이디로 검색된 사용자 정보 (null은 없음)
	 */
	UserVO getUserById(String u_id) throws Exception;
	
	/**
	 * @param u_id - 아이디로 검색된 사용자의 최종 방문 시간 수정
	 */
	void updateVisitDate(String u_id) throws Exception;
	
	/**
	 * @param u_id - 회원 탈퇴 여부 (y, n)
	 */
	void deleteYN(String u_id) throws Exception;
	
}
