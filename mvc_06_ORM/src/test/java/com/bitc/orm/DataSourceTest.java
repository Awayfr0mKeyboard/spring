package com.bitc.orm;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 *	context를 통해 Spring Bean 으로 등록된
 *	DriverManagerDataSource 객체의 의존성 주입 테스트
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
		"classpath:/spring/root-context.xml"
		// "classpath:test-context.xml"
	}	
)
public class DataSourceTest {
	
	@Autowired
	DriverManagerDataSource dmds;
	
	@Before
	public void beforeTest() {
		System.out.println(dmds);
	}
	
	Connection conn;
	
	@Test
	public void dataSourceTest() throws SQLException {
		conn = dmds.getConnection();
		System.out.println(conn);
	}
	
	@After
	public void testAfter() throws SQLException {
		conn.close();
	}
	
}
