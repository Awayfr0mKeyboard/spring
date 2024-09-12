package com.bitc.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bitc.comment.dao.CommentDAO;
import com.bitc.comment.vo.CommentVO;
import com.bitc.common.util.Criteria;
import com.bitc.common.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentDAO dao;

	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = dao.add(vo);
		return result == 1 ? "등록성공" : "등록실패";
	}

	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		return dao.commentList(bno);
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		int result = dao.update(vo);
		return result > 0 ? "SUCCESS" : "FAILED";
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		return dao.delete(cno) == 1 ? "SUCCESS" : "FAILED";
	}

	@Override
	public Map<String, Object> commentPage(Criteria cri, int bno) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		List<CommentVO> list = dao.listPage(paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int totalCount = dao.totalCount(bno);
		pm.setTotalCount(totalCount);
		map.put("pm", pm);
		
		return map;
	}

}









