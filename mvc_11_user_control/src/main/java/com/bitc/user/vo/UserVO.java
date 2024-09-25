package com.bitc.user.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
	
	private  int u_no;
	private String u_id;
	private String u_pw;
	private String u_name; 
	private String u_profile;
	private String u_phone;
	private String u_birth;
	private String u_addr;
	private String u_addr_detail;
	private String u_addr_post;
	private int u_point;
	private Date u_date;
	private Date u_visit_date;
	private String u_withdraw;
	
}
