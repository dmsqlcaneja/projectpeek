package com.newlecture.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.newlecture.web.service.MybatisHomeService;

@Component
public class NewlectureAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
//로그인, 인증에 성공하면 여기서 정보를 처리한다
//로그인 후 어떤 사용자가 어느 페이지로 갈 것인지 여기에 작성해주고
//SecurityContextConfig에서 successHandler 객체를 통해 구현된다.
   
   /*
    * User > UI(MVC) > DB
    * User > UI(MVC) > DAO > DB
    * User > UI(MVC) > Service > DAO > DB 큰 규모일수록 세분화하는게 좋다
    */
	@Autowired
	private MybatisHomeService service;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
   
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
         throws IOException, ServletException {
	   /*
	    * 인터럽트 된 상태에서 로그인 처리 방법 
	    */
	   
	   HttpSession session = request.getSession();
	   SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
	   
	   if(savedRequest != null) {
		   
		   String returnURL = savedRequest.getRedirectUrl();
		   redirectStrategy.sendRedirect(request, response, returnURL);
		   
		   return;
		   
	   }
	   
	   /*
	    *  인터럽트 되지 않은 로그인 처리 방법 
	    */
	   
	   String memberId = authentication.getName();
	   String roleName = service.getDefaultRoleName(memberId);
	   
	   switch(roleName) {
	   
	   case "ROLE_TEACHER":
		   redirectStrategy.sendRedirect(request, response, "/teacher/index");
		   break;
	   case "ROLE_ADMIN":   
		   redirectStrategy.sendRedirect(request, response, "/admin/index");
		   break;
	   default:   
		   redirectStrategy.sendRedirect(request, response, "/student/index");
	   }
	   
	  
   }
   
}
	

