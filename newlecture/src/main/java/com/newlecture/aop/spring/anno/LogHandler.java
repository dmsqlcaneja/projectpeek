package com.newlecture.aop.spring.anno;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class LogHandler implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		//long start = System.currentTimeMillis();
		//스프링이 제공하는 StopWatch
		StopWatch watch = new StopWatch();
		watch.start();
		
		
    	//Object result = method.invoke(cal, args); 인자를 넘겨 줄 필요가 없음.
		//바로 실행해랏
		Object result = method.proceed();
    	
        //long end = System.currentTimeMillis();
		watch.stop();
		long duration = watch.getTotalTimeMillis();
		
        String message = duration+"ms가 걸림";
        //String message = (end-start)+"ms가 걸림";
        System.out.println(message);
        
        
		return result;
	}

}
