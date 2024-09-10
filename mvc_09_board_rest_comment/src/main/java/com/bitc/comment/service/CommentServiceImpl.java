package com.bitc.comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bitc.comment.dao.CommentDAO;
import com.bitc.comment.vo.CommentVO;
import com.bitc.common.util.Criteria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentDAO dao;

	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = dao.add(vo);
		return result == 1 ? "삽입성공" : "삽입실패";
	}

	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> commentPage(Criteria cri, int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
