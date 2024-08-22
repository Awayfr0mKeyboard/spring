package com.bitc.orm;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.orm.vo.MemberVO;

/**
 *	MyBatis apache 재단에서 만든 ORM Framework
 *	초기 프로젝트 때는 ibatis 였고, 2010년 부터 MyBatis로 변경하여 배포
 *
 *	XML기반의 설정 파일과 SQL 매핑을 통해 데이터 베이스와의 상호작용을 단순하게
 *	처리할 수 있도록 제공
 *
 *	MyBatis로 변경되면서 XML 기반 설정과 namespace와 annotation을 활용하여
 *	보다 간결하고 유연한 설정방식을 제공
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:/spring/root-context.xml"}
)
public class MyBatisTest {

	@Autowired
	DataSource ds;
	
	@Test
	public void testMyBatis() throws Exception {
		
		// SqlSessionFactoryBean 객체를 제공하는 Class
		// 실제 database 와 연결된 모든 작업을 수행하는 SqlSession 객체의 생성 관리를 제공하는 Class
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds);
		// 설정 파일의 경로 등록
		// classpath:mybatis/MyBatisConfig.xml
		Resource rs = new ClassPathResource("mybatis/myBatisConfig.xml");
		factoryBean.setConfigLocation(rs);
		
		SqlSessionFactory factory = factoryBean.getObject();
		System.out.println(factory);
		
		// SqlSession session = factory.openSession();
		SqlSession session = new SqlSessionTemplate(factory);
		/*
		MemberVO member = new MemberVO();
		member.setUserid("id001");
		member.setUserpw("pw001");
		member.setUsername("IRON MAN");
		
		int result = session.insert("memberMapper.insertMember", member);
		System.out.println("삽입된 회원 행 개수 : " + result);
		*/
		
		// userid 가 일치하는 회원의 정보를 MemberVO type으로 반환
		MemberVO member = session.selectOne("memberMapper.readMember","id001");
		System.out.println("readMember : " + member);
		
		List<MemberVO> memberList = session.selectList("memberMapper.memberList");
		System.out.println(memberList);
	}
	
}
