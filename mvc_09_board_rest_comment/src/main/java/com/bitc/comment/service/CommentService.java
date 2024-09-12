package com.bitc.comment.service;

import java.util.List;
import java.util.Map;

import com.bitc.comment.vo.CommentVO;
import com.bitc.common.util.Criteria;

public interface CommentService {
	
	/**
	 * @apiNote - 댓글 삽입
	 * @param vo - 댓글 삽입에 필요한 파라미터 정보
	 * @return String - 댓글 삽입 성공 실패 여부 message
	 */
	String addComment(CommentVO vo) throws Exception;
	
	/**
	 * @apiNote - bno 게시글의 전체 댓글 목록
	 * @param bno - 검색할 게시글 번호
	 * @return - 게시글에 작성된 댓글 목록
	 */
	List<CommentVO> commentList(int bno) throws Exception;
	
	
	/**
	 * @apiNote - 댓글 수정
	 * @param vo - 댓글 수정에 필요한 파라미터 정보
	 * @return String - 댓글 수정 성공 실패 여부 message
	 */
	String updateComment(CommentVO vo) throws Exception;
	
	/**
	 * @apiNote - 댓글 삭제
	 * @param - 삭제할 댓글 번호
	 * @return - 댓글 삭제 성공 여부 message
	 */
	String deleteComment(int cno) throws Exception;
	
	/**
	 * @apiNote - 댓글 페이징 처리에 필요한 데이터
	 * @param cri - 요청 page, 한번에보여줄 게시글 개수, 시작인덱스 번호
	 * @param bno - paging 처리된 리스트 목록을 출력할 게시글 번호
	 * @return - List<CommentVO> , PageMaker
	 */
	Map<String, Object> commentPage(Criteria cri, int bno)throws Exception;
}







