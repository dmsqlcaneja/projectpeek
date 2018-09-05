package com.newlecture.di.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("exam")
public class Exam {
   @Value("1")
   private int kor;
   @Value("2")
   private int eng;
   @Value("3")
   private int math;

   public int total() {
      return kor + eng + math;
   }

   public float avg() {

      return total() / 3.0f;
   }

   public Exam() {

   }

   public Exam(int kor, int eng, int math) {
      super();
      this.kor = kor;
      this.eng = eng;
      this.math = math;
   }

   public int getKor() {
      return kor;
   }

   public void setKor(int kor) {
      this.kor = kor;
   }

   public int getEng() {
      return eng;
   }

   public void setEng(int eng) {
      this.eng = eng;
   }

   public int getMath() {
      return math;
   }

   public void setMath(int math) {
      this.math = math;
   }

}