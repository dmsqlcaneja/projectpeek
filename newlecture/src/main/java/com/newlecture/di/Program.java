package com.newlecture.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.di.ui.ExamConsole;

public class Program {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/di/spring-context.xml");
		
//		Exame exam = new Exam(1,1,1);
		
		
//		Exam exam = (Exam) context.getBean("exam");
//		
//		ExamConsole console = (ExamConsole) context.getBean("console");
//		
//		console.setExam(exam);
		
		//ExamConsole console = (ExamConsole) context.getBean(ExamConsole.class);
		ExamConsole console = (ExamConsole) context.getBean("console");
		console.print();
		
		
	}

}
