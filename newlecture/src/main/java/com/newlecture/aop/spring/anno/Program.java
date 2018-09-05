package com.newlecture.aop.spring.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.aop.spring.anno.Calculator;

public class Program {

	public static void main(String[] args) {
		
		/*
		cal 객체 생성
		proxy 생성
		이 둘의 결합 코드를 기존 방식과 다르게
		이 부분을 스프링이 관할하고 [spring-context를 이용]
		스프링이 원본 객체(target)를 줄지
	    아니면 프락시를 줄지 여기서는 알 수 없음.
		*/
		
		//Calculator cal = ?;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/aop/spring/anno/spring-context.xml");
		
		Calculator cal = (Calculator) context.getBean("cal");
		
		int result = cal.add(0,0);
		
		System.out.println("3+5 :" + result);

	}

}
