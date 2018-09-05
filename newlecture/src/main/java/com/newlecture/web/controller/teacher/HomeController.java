package com.newlecture.web.controller.teacher;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Subject;
import com.newlecture.web.service.TeacherService;

//같은 이름의 컨트롤러가 중복으로 있으면 500에러 발생! 충돌을 막으려면 ID를 지정해줘야한다
@Controller("teacherHomeController")
@RequestMapping("/teacher/")
public class HomeController {
   
   @Autowired
   private TeacherService teacherService;
   
   @GetMapping("index")
   public String index(Model model) {
      
      List<Subject> subjects = teacherService.getSubjectList();
      model.addAttribute("subjects", subjects);
      
      return "teacher.index"; //tiles를 쓰면 . 안쓰면 /로 구분해준다.
   }
}