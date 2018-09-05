package com.newlecture.web.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlecture.web.entity.Question;

//나는 컨트롤러입니다.라는 문패를 지정해주자
@Controller
@RequestMapping("/teacher/question/")
public class QuestionController {

	/*
	 1. Dispatcher를 담당하는 Front Controller를 Spring으로부터 제공받아서 사용하고 있음
	    - MVC model2 방식으로 /teacher/question/type URL을 구현해보았다.
	 2. view를 구현해서 type.jsp를 요청해 보았다.
	 3. RequestMapping과 return 뷰 문자열을 줄이는 방법을 알아보았다.
	    return 뷰 문자열을 줄이기 위해서 ViewResolver를 설정하였다.
	4. model을 사용하는 방법을 이해하기
	 */
	@RequestMapping("type")
	public String type(Model model) {
		model.addAttribute("test", "Hello");
		return "teacher.question.type";
	}
	
	//@RequestMapping(value="reg",method=RequestMethod.GET) 4중반 전에 
	@GetMapping("choice-reg") //최신버전
	public String choiceReg() {
		return "teacher.question.choice-reg";
	}	
	
	//@RequestMapping(value="reg",method=RequestMethod.POST)
	@PostMapping("choice-rg")
	public String reg(Question question) {
		
		return "redirect:type";
	}
}
