package com.newlecture.aop.spring.anno;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterHandler implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		//조건검사 / 반환값 확인
		//반환 값을 가지고 조건을 처리하거나 할 때 사용되는 로직 : After
		int result = (Integer) returnValue;
		if(result < 0)
			System.out.println("음수를 반환하였습니다.");
		else
			System.out.println("양수를 반환하였습니다");
		
			
	}

}
