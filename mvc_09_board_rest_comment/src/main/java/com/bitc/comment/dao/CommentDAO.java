package com.bitc.comment.dao;

import org.apache.ibatis.annotations.Insert;

import com.bitc.comment.vo.CommentVO;

public interface CommentDAO {

	@Insert("INSERT INTO tbl_comment(bno, commentAuth, commentText) "
			+ " VALUES(#{bno}, #{commentAuth}, #{commentText})")
	int add(CommentVO vo)throws Exception;
	
}
