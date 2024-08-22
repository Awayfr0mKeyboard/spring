package com.bitc.orm;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.orm.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:/spring/root-context.xml"}
)
public class SqlSessionTest {
	
	@Autowired
	SqlSession session;
	
	@Test
	public void sqlSessionTest() {
		List<MemberVO> memberList = session.selectList("memberMapper.memberList");
		System.out.println(memberList);
	}
	
}
