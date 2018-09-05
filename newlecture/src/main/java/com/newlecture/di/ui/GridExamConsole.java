package com.newlecture.di.ui;

import java.util.Scanner;

import com.newlecture.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

   private Exam exam;

   public GridExamConsole() {
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
      System.out.println("├────────┬───────┬───────┬───────┬─────────┤");
      System.out.println("│   kor  │  eng  │  math │ total │   avg   │");
      System.out.println("├────────┼───────┼───────┼───────┼─────────┤");
      System.out.printf("│   %3d  │  %3d  │  %3d  │  %3d  │  %6.2f │\n", kor, eng, math, total, avg);
      System.out.println("└────────┴───────┴───────┴───────┴─────────┘");
   }

	@Override
	public void setExam(Exam exam) {
		// TODO Auto-generated method stub
		
	}
}