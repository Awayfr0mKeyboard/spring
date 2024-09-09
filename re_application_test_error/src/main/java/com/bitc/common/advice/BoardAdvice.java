package com.bitc.common.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Aspect		// AOP annotation 기반의 advice class 임을 명시
// @Component
public class BoardAdvice {

	public BoardAdvice() {
		log.info("logger Board Advice");
	}
	
	// 문자열에 지정된 패턴에 따라서 Target, JoinPoints 지정
	@Before("execution(* com.bitc.*.service.*.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("=================================");
		log.info("=========== Start Log ===========");
		// com.bitc.board.service.BoardSerivceImpl
		log.info("target : " + jp.getTarget());
		// method constructor
		log.info("type : " + jp.getKind());
		// 실행된 method 이름
		log.info("name : " + jp.getSignature().getName());
		// 매개변수에 전달되는 인자값
		Object[] args = jp.getArgs();
		log.info("arguments : " + Arrays.toString(args));
		log.info("=========== End Start Log ===========");
	}
	
	// BoardVO read(int bno)
	@After("execution(com.bitc.board.vo.BoardVO com.bitc.board.service.BoardServiceImpl.read(int))")
	public void endReadLog(JoinPoint jp) {
		log.info("=========== Start After Log ===========");
		log.info("name : " + jp.getTarget() + jp.getSignature().getName());
		log.info("=========== End After Log ===========");
	}
	
}
