package com.bitc.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.Criteria;

public interface BoardDAO {

	/**
	 * 게시글 작성
	 * 
	 * @param BoardVO db에 등록할 게시글 정보
	 * @return 등록된 게시글 개수를 수로 반환
	 */
	@Insert("INSERT INTO tbl_board(title,content,writer) "
			+ "	VALUES(#{title}, #{content}, #{writer})")
	int create(BoardVO vo) throws Exception;

	/**
	 * 게시글 상세보기
	 * 
	 * @param bno - 상세보기할 게시글 번호
	 * @return - 조회된 게시글 정보를 BoardVO 타입으로 반환
	 */
	@Select("SELECT * FROM tbl_board WHERE bno = #{bno}")
	BoardVO read(int bno) throws Exception;

	/**
	 * 게시글 수정
	 * 
	 * @param BoardVO 수정할 게시글 정보
	 * @return - 수정된 게시글 개수를 정수로 반환
	 */
	@Update("UPDATE tbl_board SET "
			+ " title = #{title}, content = #{content}, writer = #{writer} "
			+ " WHERE bno = #{bno}")
	int update(BoardVO vo) throws Exception;

	/**
	 * 게시글 삭제
	 * 
	 * @param bno - 삭제할 게시글 번호
	 * @return - 삭제된 게시글 개수를 정수로 반환
	 */
	@Delete("DELETE FROM tbl_board WHERE bno = #{bno}")
	int delete(int bno) throws Exception;

	/**
	 * 조회수 증가
	 * 
	 * @param bno - 조회수 증가 시킬 게시글 번호
	 */
	@Update("UPDATE tbl_board SET viewcnt = viewcnt + 1 WHERE bno = #{bno}")
	void updateCnt(int bno) throws Exception;

	/**
	 * 전체 게시글 목록
	 * 
	 * @return - 조회된 전체 게시글 목록
	 */
	@Select("SELECT * FROM tbl_board ORDER BY bno DESC")
	List<BoardVO> listAll() throws Exception;

	/**
	 * 전체 게시물 개수
	 * 
	 * @return - 전체 게시글 개수
	 */
	@Select("SELECT count(*) FROM tbl_board")
	int totalCount() throws Exception;

	/**
	 * 페이징 처리된 게시물 목록
	 * 
	 * @param cri - 페이징 된 게시글 목록을 조회할 정보
	 * @return - 조회된 게시글 목록
	 */
	@Select("SELECT * FROM tbl_board ORDER BY bno DESC limit #{startRow}, #{perPageNum}")
	List<BoardVO> listCriteria(Criteria cri) throws Exception;

}















