package com.bitc.lombok;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LombokTest {

	@Before
	public void before() {
		System.out.println("test 전");
	}
	
	@Test
	public void lombokTest() {
		UserVO user = new UserVO("id001", "pw001", "최기근");
		user.setUno(1);
		user.setUid("id001");
		user.setUpw("pw001");
		// user.setUname("최기근");
		System.out.println(user);
		
		UserVO user2 = new UserVO("id001", "pw001", "이순신");
		System.out.println(user.equals(user2));
		user2.setUid("id001");
		user2.setUpw("pw001");
		System.out.println(user.equals(user2));
		
		// Builder pattern
		UserVO user3 = UserVO.builder()
						.uno(1)
						.uid("id002")
						.upw("pw002")
						.uname("최기근")
//						.friends(new ArrayList<String>())
						.list("보노보노").list("뽀로로").list("김태희")
						.build();
		System.out.println(user3);
		System.out.println(user3.getFriends());
	}
	
	@After
	public void after() {
		System.out.println("test 후");
	}
}
