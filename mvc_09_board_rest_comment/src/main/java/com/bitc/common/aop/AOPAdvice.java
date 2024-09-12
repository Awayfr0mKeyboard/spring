package com.bitc.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AOPAdvice {
	
	@Around("execution(* com.bitc.*.controller.*.*(..))")
	public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("------------------------------------");
		log.info("---- Around Controller START -------");
		log.info("Around Controller Target : " + pjp.getTarget());
		log.info("Around method name : " + pjp.getSignature().getName());
		log.info("Around Arguments : " + Arrays.toString(pjp.getArgs()));
		// target class의 pointcut method 호출
		Object obj = pjp.proceed();
		
		if(obj != null) {
			log.info("Arround return Object : " + obj);
		}
		log.info("---- Around Controller END ---------");
		return obj; // 결과 반환 후 후처리 종료
	}
	
	
	@Around("execution(* com.bitc.*.service.*.*(..))")
	public Object serviceLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("------------------------------------");
		log.info("---- Around service START -------");
		log.info("Around service Target : " + pjp.getTarget());
		log.info("Around method name : " + pjp.getSignature().getName());
		log.info("Around Arguments : " + Arrays.toString(pjp.getArgs()));
		// target class의 pointcut method 호출
		Object obj = pjp.proceed();
		
		if(obj != null) {
			log.info("Arround return Object : " + obj);
		}
		log.info("---- Around service END ---------");
		return obj; // 결과 반환 후 후처리 종료
	}
	

}










