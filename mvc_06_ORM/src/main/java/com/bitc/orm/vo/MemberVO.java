package com.bitc.orm.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private int uno;
	private String userid;
	private String userpw;
	private String username;
	private Date regdate;
	private Date updatedate;
	
}
