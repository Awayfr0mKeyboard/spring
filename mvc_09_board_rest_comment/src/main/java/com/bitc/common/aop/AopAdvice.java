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
public class AopAdvice {
	
	@Around("execution(* com.bitc.*.controller.*.*(..))")
	public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("-------------------------------------");
		log.info("------ Around Controller START ------");
		log.info("Around Controller Target : " + pjp.getTarget());
		log.info("Around method name : " + pjp.getSignature().getName());
		log.info("Around Arguments : " + Arrays.toString(pjp.getArgs()));
		// target class의 pointcut method 호출
		Object obj = pjp.proceed();
		if (obj != null) {
			log.info("Arround return Object :" + obj);
		}
		log.info("------ Around Controller END ------");
		return obj;
	}
	
	@Around("execution(* com.bitc.*.service.*.*(..))")
	public Object serviceLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("----------------------------------");
		log.info("------ Around Service START ------");
		log.info("Around Service Target : " + pjp.getTarget());
		log.info("Around method name : " + pjp.getSignature().getName());
		log.info("Around Arguments : " + Arrays.toString(pjp.getArgs()));
		// target class의 pointcut method 호출
		Object obj = pjp.proceed();
		if (obj != null) {
			log.info("Arround return Object :" + obj);
		}
		log.info("------ Around Service END ------");
		return obj;
	}
	
}
