package com.newlecture.web.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Subject;
import com.newlecture.web.service.TeacherService;

@Controller
@RequestMapping("/*")
public class HomeController {
	
//	@Autowired
//	private MemberDao memberDao;
	
	@GetMapping("index")
	public String index(Model model) {
		
//		Member member = memberDao.get("newlec");
//		model.addAttribute("member", member);
		
		return "home.index";
	}
}
