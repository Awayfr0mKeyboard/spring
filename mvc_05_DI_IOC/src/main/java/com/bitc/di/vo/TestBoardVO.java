package com.bitc.di.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Service - 의미에 맞지 않는 사용
@Component
public class TestBoardVO {

	private int num;
	private String title;
	private String content;
	private String wirter;
	private Date regdate;
	
}
