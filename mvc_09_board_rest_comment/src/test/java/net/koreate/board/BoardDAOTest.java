package net.koreate.board;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class BoardDAOTest {

	@Autowired
	BoardDAO dao;
	
	@Before
	public void before() throws Exception {
		System.out.println("before List");
		List<BoardVO> list = dao.listAll();
		System.out.println(list);
	}
	
	@Test
	public void insert() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("ㄱㄷㅎ");
		board.setContent("누구게?");
		board.setWriter("이진우");
		int result = dao.create(board);
		System.out.println("insert result : " + result);
	}
	
	@Test
	public void update() throws Exception{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("ㄱㄷㅎ 수정");
		board.setContent("누가 수정 했게?");
		board.setWriter("이진오");
		
		int result = dao.update(board);
		System.out.println("수정된 게시글 개수 : " + result);
		
		// 게시글 조회수 증가
		dao.updateCnt(1);
		
		BoardVO boardVO = dao.read(1);
		System.out.println("수정된 게시글 정보 : " + boardVO);
		
		result = dao.delete(2);
		System.out.println("삭제된 게시글 개수 : " + result);
		
		int totalCount = dao.totalCount();
		System.out.println("남아 있는 전체 게시글 개수 : " + totalCount);
		
	}
	
	@After
	public void after() throws Exception {
		Criteria cri = new Criteria();
		List<BoardVO> list = dao.listCriteria(cri);
		System.out.println("after listCriteria : " + list);
	}
	
}















