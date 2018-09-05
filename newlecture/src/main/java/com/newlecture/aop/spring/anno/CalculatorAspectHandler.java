package com.newlecture.aop.spring.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;

@Aspect
public class CalculatorAspectHandler {
	
	//포인트컷팅을 써주기
	@Before("execution(* *..NewlecCalculator.add(..))")
	public void authorHandler() {
		System.out.println("권한 확인");
	}
	
	@AfterReturning(pointcut="execution(* *..NewlecCalculator.add(..))",
					returning="returnValue")
	public void after(JoinPoint joinPoint, Object returnValue) {
		
		int result = (Integer) returnValue;
		
		if(result < 0)
			System.out.println("음수를 반환하였습니다.");
		else
			System.out.println("양수를 반환하였습니다");
		
	}
	
	/*
	 * add(..)
	 * (..)는 인자인데 
	 * ..은 모든을 의미
	 * (int, int) = 매개 int int하면 다른 인자매개는 포함 X
	 *
	 *[int]반환
	 *execution(* int NewlecCalculator.add(..))
	 *
	 *변환 상관없다 
	 *execution(* *..NewlecCalculator.add(..))
	 *
	 *[public/..]
	 *execution(public * *..NewlecCalculator.add(..))
	 * 
	 */
	
	@AfterThrowing(pointcut="execution(* *..NewlecCalculator.add(..))",
					throwing="exx")
	public void errorHandler(JoinPoint joinPoint, Throwable exx) {
		System.out.println("ㅈㅅ. 예기치 않은 오류가 "+exx.getMessage()+"발생하였습니다.");
	}
	
	@Around("execution(* *..NewlecCalculator.add(..))")
	public Object logHandler(ProceedingJoinPoint joinPoint) {
		
		StopWatch watch = new StopWatch();
		watch.start();
		
		
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		watch.stop();
		long duration = watch.getTotalTimeMillis();
		
        String message = duration+"ms가 걸림";
        System.out.println(message);
        
        return result;
	}
	
	
	
}
