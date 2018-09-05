package com.newlecture.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//설정이다 말해주기
@Configuration
//컨트롤러 범위 지정
@ComponentScan(basePackages="com.newlecture.web.controller")
@EnableWebMvc
public class ServletContextConfig implements WebMvcConfigurer {
	//spring/servlet-context.xml있는 내용이 여기롱
	
	/*
	 * InternalResourceViewResolver 이놈을 객체화해서 컨테이너에 담아달라고 할 것
	 * 이름은 internalResourceViewResolver ! [get을 붙이면 안댐]
	 * 왼쪽 클래스를 객체화 해서 오른쪽 이름으로 IoC에 담아주세요.
	 * 그래서 Bean어노테이션을 해야 생성한 클래스를 객체화해서 IoC에 담아줄 수 있다.
	 */
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		
		return resolver;
		
	}
	
	//가변인수를 이용한 메소드 String... / 자료형[변수명]
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		//tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
		tilesConfigurer.setCheckRefresh(true);
		
		return tilesConfigurer;
	}
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(1);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<mvc:resources location="/resources/" mapping="/resources/**" />
		//꼬리물기 공법 ? = 언어의 꼬리를 문다..?
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
		
	}
	
}
