package com.bitc.test.model;

import lombok.Data;

/**
 * lombok을 이용하여 getter setter toString 추가
 */
@Data
public class LoginDTO {
	
	private String id;
	private String pass;
	private boolean check;
	
	
}
