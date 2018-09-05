package com.newlecture.aop.spring.anno;

public class NewlecCalculator implements Calculator {
    public int add(int x, int y) {
    	
    	/*-----------------
    	long start = System.currentTimeMillis();
    	-----------------*/
    	/*----
    	try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		------*/
    	
        int result = x + y;
        
        
        /*---------------
        long end = System.currentTimeMillis();
        String message = (end-start)+"ms가 걸림";
        System.out.println(message);
    	-----------------*/
        
        return result;
    }
    public int sub(int x, int y) {
    	
    	/*-----------------
    	long start = System.currentTimeMillis();
    	-----------------*/
    	
        int result = x - y;
        
        /*---------------
        long end = System.currentTimeMillis();
        String message = (end-start)+"ms가 걸림";
        System.out.println(message);
    	-----------------*/    
        return result;
    }
    public int multi(int x, int y) {
    	
        int result = x * y;      
        
        return result;
    }
    public int div(int x, int y) {
        if(y == 0)
            throw new IllegalArgumentException("0으로 나눈 예외");
      
        int result = x / y;
        return result;
    }
}