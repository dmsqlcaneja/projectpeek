package com.newlecture.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calculator cal = new NewlecCalculator();
		
		//Loader, 인터페이스목록, 곁다리 업무 객체 순서
		Calculator calProxy = (Calculator) Proxy.newProxyInstance(
								NewlecCalculator.class.getClassLoader(), 
								new Class[] { Calculator.class },
								new InvocationHandler() {
									
									@Override
									public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

								    	long start = System.currentTimeMillis();
								    	
								    	try {
											Thread.sleep(300);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
								    	
								    	Object result = method.invoke(cal, args);
								    	
								        long end = System.currentTimeMillis();
								        String message = (end-start)+"ms가 걸림";
								        System.out.println(message);
								        
										return result;
									}
								});
		
		int result = cal.sub(3, 5);
		
		System.out.println(result);
		
	}

}
