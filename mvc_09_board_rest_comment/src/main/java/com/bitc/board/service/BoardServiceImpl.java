package com.bitc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.Criteria;
import com.bitc.common.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardDAO dao;

	@Override
	public String regist(BoardVO board) throws Exception {
		int result = dao.create(board);
		String message = (result == 1) ? "SUCCESS" : "FAILED";
		return message;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public String modify(BoardVO board) throws Exception {
		int result = dao.update(board);
		return (result == 1) ? "UPDATE SUCCESS" : "UPDATE FAILED";
	}

	@Override
	public String remove(int bno) throws Exception {
		return dao.delete(bno) == 1 ? "DELETE SUCCESS" : "DELETE FAILED";
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// criteria 기준 정보를 이용해서 페이징 처리된 게시글 목록 반환
		// 사용자가 요청한 페이지 번호에 따라 게시글 목록 검색하여 전달
		return dao.listCriteria(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		int totalCount = dao.totalCount();
		PageMaker pm = new PageMaker(cri, totalCount);
		return pm;
	}

}











