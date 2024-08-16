package com.bitc.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitc.test.model.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	// 자동 연결 Annotation
	@Autowired
	private DataSource ds; // 의존성 주입
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public boolean memberJoin(MemberVO member) {
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(
				"INSERT INTO test_mvc(id,pass,name) VALUES(?,?,?)"	
			);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {}
			
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {}
			
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {}
		}
		
		return false;
	}

	@Override
	public MemberVO memberLogin(String id, String pass) {
		
		return null;
	}

	@Override
	public MemberVO getMemberById(String id) {
		
		return null;
	}

}
