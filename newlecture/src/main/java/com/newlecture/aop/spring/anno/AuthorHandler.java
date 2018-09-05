package com.newlecture.aop.spring.anno;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class AuthorHandler implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("권한 확인");
		
	}
	

}
