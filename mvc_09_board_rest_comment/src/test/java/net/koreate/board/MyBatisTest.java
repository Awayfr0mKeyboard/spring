package net.koreate.board;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class MyBatisTest {

	@Autowired
	SqlSession session;
	
	@Before
	public void sessionTest() {
		System.out.println("session : "+session);
	}
	
	@Test
	public void insertBoard() {
		BoardVO board = new BoardVO();
		board.setTitle("김동하 으드득");
		board.setContent("제곧내");
		board.setWriter("김지연");
		
		int result = session.insert("boardMapper.create",board);
		System.out.println("insert result : " +result);
	}
	
}














