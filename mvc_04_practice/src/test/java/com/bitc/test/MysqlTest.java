package com.bitc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MysqlTest {

	@Test
	public void testConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/digital_jsp",
				"digital",
				"1234"
			);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class를 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("연결 정보 오류 : " + e.getMessage());
		}	
	}
	
	@Test
	public void dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/digital_jsp");
		ds.setUsername("digital");
		ds.setPassword("1234");
		
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
