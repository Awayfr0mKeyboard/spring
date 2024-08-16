package com.bitc.test.model;

import lombok.Data;

/**
 * lombok을 이용하여 getter setter toString 추가
 */
@Data
public class MemberVO {
	
	private int num;					// 회원 번호
	private String id;					// 회원 아이디
	private String pass;				// 회원 비밀번호
	private String name;				// 회원 이름
	
}









