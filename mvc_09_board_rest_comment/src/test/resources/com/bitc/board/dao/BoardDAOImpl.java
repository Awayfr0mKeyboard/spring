package com.bitc.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.Criteria;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO{
	
	// 필수 인자값 생성자를 통한 의존성 주입
	private final SqlSession session;
	
	private String name = "boardMapper";

	@Override
	public int create(BoardVO vo) throws Exception {
		return session.insert(name+".create",vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return session.selectOne(name+".read", bno);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		int result = session.update(name+".update", vo);
		return result;
	}

	@Override
	public int delete(int bno) throws Exception {
		int result = session.delete(name+".delete",bno);
		return result;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		// 조회수 증가
		session.update(name+".updateCnt",bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = session.selectList(name+".listAll");
		return list;
	}

	@Override
	public int totalCount() throws Exception {
		Integer totalCount = session.selectOne(name+".totalCount");
		return totalCount;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = session.selectList(name+".listCriteria",cri);
		return list;
	}

}

















