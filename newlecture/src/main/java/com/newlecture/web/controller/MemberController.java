package com.newlecture.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.asm.ByteVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.mybatis.MybatisMemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MybatisHomeService;
import com.sun.mail.iap.Response;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MybatisHomeService service; 
	//service의 정의 
	
//	@Autowired
//	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("join")
	public String join(Model model) {
//		Member member = memberDao.get("newlect");
//		model.addAttribute("member", member);
		return "member.join";
	}
	
	@GetMapping("join-email")
	public String joinemail() {
		
		return "member.join-email";
	
	}
	
	@GetMapping("email-duplicated-error")
	@ResponseBody
	public String emailDuplicatedError() {
		
		return "<script>alert('이미 가입된 이메일 입니다. ');location.href='join-email';</script>";
		
	
	}
	
	//2018.09.10 월요일 
	@GetMapping("is-id-duplicated")
	@ResponseBody
	//@ResponseBody 는 주소를 찾지 않게 해주는 어노테이션 
	public String isIdDuplicated(String id) {
		
		boolean duplicated = service.isIdDuplicated(id);
		
		if(duplicated)
			return "ture";
	
		return "false";
	
	
	}	
	
	//2018.09.10 월요일 
	@PostMapping("join-email")
	public String joinemail(String email, HttpServletResponse response) {
		
		//이 주소 중복조회해봐->(있습니다./없습니다.)/이 주소 있어?/이 주소 유효해?
		boolean duplicated = service.isEmailDuplicated(email);
		
		
		//이메일 중복에대한 에러메세지를 보내자!!
		if(duplicated)
			return"redirect:email-duplicated-error";
		
		/*----------------------------------------------------------*/
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;
		
		
		try {
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes());
				
			byte[] key = salt.digest();
			//바이트열을 문자열로 바꾸기위해서 더하기가 반복되어야 한다. 
			StringBuilder builder = new StringBuilder();
			for(byte b : key)
				builder.append(String.format("%02x", b));
				
			digest = builder.toString();
				
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(uuid);
		System.out.println(digest);
		System.out.println(email);
		
		Cookie cookie = new Cookie("joinId", digest);
		cookie.setPath("/member/"); // 경로는 무슨 용도? 
		cookie.setMaxAge(60*60*24); // 단위!? 
		
		response.addCookie(cookie); 
		
		MimeMessage message =  mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 회원가입을 위한 인증메일");
			helper.setText("<a href=\"http://localhost:8080/member/join-reg?id="+digest+"&em="+email+"\">가입링크</a>", true);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		mailSender.send(message);
		
		return "member.join-email-info";
	
	}
	
	@GetMapping("join-reg")
	public String joinreg(@RequestParam("id") String key
			, @RequestParam(value="em", defaultValue="") String email
			, @CookieValue(value="joinId", defaultValue="") String joinId
			,Model model) {
	
		
		if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "redirect:join-error";
		
		String uid = email.split("@")[0]; // newlec@naver.com 에서 앞에 newlec 만 발췌하는 코드 
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
		
		return "member.join-reg";
	
	}
	
	@GetMapping("login")
	public String login() {
		return "member.login";
	}	
	
	
	
}
