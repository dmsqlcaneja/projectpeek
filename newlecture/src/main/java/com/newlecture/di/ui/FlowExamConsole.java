package com.newlecture.di.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newlecture.di.entity.Exam;

@Component("console")
public class FlowExamConsole implements ExamConsole{
   
	@Autowired
   private Exam exam;
   
   public FlowExamConsole() {
      exam = new Exam();
   }
   
   public void input() {
      Scanner scan = new Scanner(System.in);
      int kor;
      int eng;
      int math;
      
      System.out.println("┌──────────────────────────────────────────┐");
      System.out.println("│                  Exam Input              │");
      System.out.println("└──────────────────────────────────────────┘");
      
      System.out.print("kor : ");
      kor = scan.nextInt();
      System.out.print("eng : ");
      eng = scan.nextInt();
      System.out.print("math : ");
      math = scan.nextInt();
      
      exam.setKor(kor);
      exam.setEng(eng);
      exam.setMath(math);
   }
   
   public void print() {
      int kor = exam.getKor();
      int eng = exam.getEng();
      int math = exam.getMath();
      int total = exam.total();
      float avg = exam.avg();
      
      System.out.println("┌──────────────────────────────────────────┐");
      System.out.println("│                Exam Print                │");
      System.out.println("└──────────────────────────────────────────┘");
      
      System.out.printf("kor : %3d\n", kor);      
      System.out.printf("eng : %3d\n", eng);
      System.out.printf("math : %3d\n", math);
      System.out.printf("total : %3d\n", total);
      System.out.printf("avg : %6.2f\n", avg);      
   }

   public void setExam(Exam exam) {
	   this.exam = exam;
   }
}