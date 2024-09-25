package com.bitc.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bitc.user.vo.UserVO;

/**
 * User Mapper Interface
 */
public interface UserDAO {

	/**
	 * 회원가입
	 */
	@Insert("INSERT INTO spring_user("
			+ "u_id, u_pw, u_profile, u_name, u_phone, u_birth, u_addr, u_addr_detail, u_addr_post"
			+ ")VALUES("
			+ "#{u_id}, #{u_pw}, #{u_profile}, #{u_name}, #{u_phone}, #{u_birth}, #{u_addr}, #{u_addr_detail}, #{u_addr_post}"
			+ ")")
	int userJoin(UserVO user) throws Exception;

	// Mybatis는 매개변수 하나 밖에 전달 못함
	@Select("SELECT * FROM spring_user WHERE u_id = #{u_id} AND u_pw = #{u_pw}")
	UserVO login(@Param("u_id") String u_id,@Param("u_pw") String u_pw) throws Exception;

	@Select("SELECT * FROM spring_user WHERE u_id = #{u_id}")
	UserVO getUserById(String u_id) throws Exception;
	
}
