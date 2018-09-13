package com.newlecture.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@ComponentScan("com.newlecture.web.config")
@EnableWebSecurity
public class SecurityContextConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private BasicDataSource dataSource;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	
	//MVC Framework -> Dispatcher ... 입--->컨 : 프론트컨트롤러 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//이제부터 내가 요청에 대해 체크하겠다 
		/*
		http
		 	.antMatcher("") //매칭 URL
		 	.authorizeRequests() //매칭하는걸 설정해준다 
		 	.anyRequest()
		 	.permitAll() // 다 . 
		 	.requestMatchers(null)
		 	.access("")
		*/
		//기본 골격 
		/*
		http
			.authorizeRequests() //권한을 가지고 요청 수렴 
			.anyRequest() // 어떠한 요청에
			.authenticated() //인증을 요구 
			.and()
			.formLogin() //폼으로 
		*/
		
		
		http
		
		.headers()
			.frameOptions()
			.sameOrigin()
			.and()
		
		.csrf().disable() //CSRF공격을 막아보자
		.authorizeRequests() 
			//.antMatchers("/admin/**").hasRole("ADMIN")
			//.antMatchers("/academy/**").hasAnyRole("ADMIN, ACADEMY")
			.antMatchers("/teacher/**").hasAnyRole("ADMIN, TEACHER")
			.antMatchers("/student/**").hasAnyRole("ADMIN, STUDENT")
			.antMatchers("/customer/question/**").authenticated()
		.anyRequest().permitAll() //위에뺀 나머진 허용
		.and()
	    .formLogin()/*인증은 폼로그인 방식으로 한다*/
	        .loginPage("/member/login")/*로그인할때 보여줄 페이지, get요청*/
	        .loginProcessingUrl("/member/login")/*로그인 페이지와 url은 세트로 있어야 한다. post요청은 이 페이지에서 한다.*/
	        .defaultSuccessUrl("/index")/*그냥 로그인 성공하면 어디로 갈것인가, 보통 사용자의 메인페이지로 간다*/
	        .successHandler(successHandler)/*로그인 성공했어, 너 처리할 거 있니?? 어디로 갈래? / 클래스 만들고, 객체 만들고, 구현해줘야함*/
	        .and()
	    .logout()
	        .logoutUrl("/member/logout")/*로그아웃할때 어느 페이지로 갈 것이냐*/
	        .logoutSuccessUrl("/index")/*로그아웃 성공하면 어느 페이지로 갈 것이냐*/
	    ;
		
		/*
		http
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/academy/**").hasAnyRole("ADMIN, ACADEMY")
			.antMatchers("/teacher/**").hasAnyRole("ADMIN, TEACHER")
			.antMatchers("/student/**").hasAnyRole("ADMIN, STUDENT");
		 */
		 
		 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 *2가지 방법 [jdbc] 
		 *1. 내가 쿼리를 만들어서 제공 [usersByUsernameQuery]
		 *2. 약속된 인터페이스로 구현된 사용자정보 제공 [authoritiesByUsernameQuery]
		 */
		
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select id, pwd password, 1 enabled from Member where id = ?")
			.authoritiesByUsernameQuery("select memberId id, roleName authority from MemberRole where memberId = ?")
			.passwordEncoder(new BCryptPasswordEncoder());
		
		
		/*
		UserBuilder users = User.builder();
		auth
		   .inMemoryAuthentication()
		   .withUser(users
				   		.username("newlec")
				   		.password("111")
				   		.roles("ADMIN"))
		   .withUser(users
			   		.username("dragon")
			   		.password("111")
			   		.roles("TEACHER")
		);
		*/
		   
		   //$2a$10$zpE1ThBwaRlZM2uMMShksurhrRjw/QtUZXB4cfON4.owLTFqkyQx.
	}
}
