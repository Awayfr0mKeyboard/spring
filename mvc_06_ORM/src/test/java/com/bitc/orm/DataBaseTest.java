package com.bitc.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataBaseTest {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/digital_jsp";
	String user = "digital";
	String pass = "1234";
	
	@Test
	public void connectorTest() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println(conn + " : 연결 완료 ");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver를 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("DB 연결 정보 오류 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {}
		}
	} // end connectorTest()
	
	@Test
	public void springJDBC() throws SQLException {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(driver);
		dmds.setUrl(url);
		dmds.setUsername(user);
		dmds.setPassword(pass);
		
		Connection conn = dmds.getConnection();
		System.out.println(conn + " 연결 완료");
		conn.close();
	}
	
}
