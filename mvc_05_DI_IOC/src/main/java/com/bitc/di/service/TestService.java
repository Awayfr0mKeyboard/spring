package com.bitc.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.di.dao.TestDAO;

@Service
public class TestService {

	private final TestDAO dao;
	
	// @Autowired
	public TestService(TestDAO dao) {
		this.dao = dao;
	}
	
	/*
	@Autowired
	public void setDao(TestDAO dao) {
		this.dao = dao;
	}
	*/
	
	public void testService(String message) {
		System.out.println("testService : " + message);
		dao.select(message);
	}
	
	
}
