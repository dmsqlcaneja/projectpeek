<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<header id="header">
   <h1>
      <a href="/index">
         <picture>            
             <source srcset="/resources/academy/sist/images/logo-xl.jpg" media="(min-width: 700px)">
            <img src="/resources/academy/sist/images/logo.png" alt="쌍용강북교육센터" />
         </picture>
      </a>
   </h1>
   <section id="header-buttons">
      <h1 class="hidden">헤더버튼</h1>
      <input class="button find-button" type="button" value="검색" />
      <input class="button hamburger-button" type="button" value="메뉴보기" />
   </section>
   <aside id="login-info">
      <h1 class="hidden">로그인 정보</h1>
      <section id="profile">
         <h1 class="hidden">프로필</h1>
       	<div>
            <div class="photo" style="background:url('https://interactive-examples.mdn.mozilla.net/media/cc0-images/Painted_Hand--298x332.jpg') no-repeat center;	
   	  background-size:cover"></div>
            <div class="uid"><span>newlec</span></div>
            
            <!-- request.getUserPrincipal().getName() -->
            <!-- 4대 저장소 / param, header, pageContext[sesction,reqeust...등등] -->
            
           <%--${pageContext.request.userPrincipal } --%> 
            <%-- <c:if test="${empty pageContext.request.userPrincipal}">
            <div class="auth-status"><a href="/member/login">로그인</a></div>
            </c:if> --%>
            
            <!-- https://www.mkyong.com/spring3/spring-el-operators-example/ -->
            
            <security:authorize access="!isAuthenticated()">
            	<div class="auth-status"><a href="/member/login">로그인</a></div>
            </security:authorize>
            
            <%-- <c:if test="${not empty pageContext.request.userPrincipal != null}">
            <div class="auth-status"><a href="/member/logout">로그아웃</a></div>
            </c:if> --%>
            
            <security:authorize access="isAuthenticated()">
            <div class="auth-status"><a href="/member/logout">
            	<security:authentication property="name" />님 로그아웃</a></div>
            </security:authorize>
            
            <a href="/member/join">회원가입</a>
            
            
		</div>
            
      	    <security:authorize access="hasRole('TEACHER')">  
            <div class="notice"><span>강사공지 : </span><a href="">3</a></div>
            </security:authorize>
            <!-- <div class="notice"><span>강사공지 : </span><a href="">3</a></div> -->
         
         
      </section>
      
         
      <section id="teacher-menu" >
         <h1 class="hidden">강사메뉴</h1>
         <ul>
            <li><a href="/teacher/question/type">문제관리</a></li>
            <li><a href="">시험관리</a></li>
            <li><a href="">일정관리</a></li>
            <li><a href="">수업관리</a></li>
         </ul>
      </section>
   </aside>
</header>
<script>
   window.addEventListener("load", function(event){
      var header = document.querySelector("#header");
      var hamburgerButton = header.querySelector(".hamburger-button");
      
  	// header.onclick = function(e){
  	// 	var el = e.target;
  	// 	if(el.nodeName != "HEADER") for(; el.nodeName != "SECTION"; el = el.parentElement);
  		
  	// 	if(!el.classList.contains("hamburger-button") && el.id == "main-menu")
  	// 		return;
  		
  	// 	header.classList.toggle("menu-show");
  	// };
  	
      header.onclick = function(e){
         if(e.target.nodeName == "HEADER")
            header.classList.remove("menu-show");
      };
      
      hamburgerButton.onclick = function(e){
         header.classList.add("menu-show");
         
         e.stopPropagation();
      };
   });
</script>