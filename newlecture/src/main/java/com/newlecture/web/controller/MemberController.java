package com.newlecture.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.mybatis.MybatisMemberDao;
import com.newlecture.web.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("join")
	public String join(Model model) {
		Member member = memberDao.get("newlect");
		model.addAttribute("member", member);
		return "member.join";
	}
	
	@GetMapping("login")
	public String login() {
		return "member.login";
	}	
}
