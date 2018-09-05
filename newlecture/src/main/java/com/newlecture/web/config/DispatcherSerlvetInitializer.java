package com.newlecture.web.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Web.xml에서 DispatcherServlet 생성한 부분이다.라고 생각하면 댐
//과거 디스패처 서블릿에서는 다음과같은 설정을 별도로 처리하고 있었다.
//서블릿
//서비스
//보안

//그래서 여기서 해야 할 기본 적인 설정은?
/*
 * 1. servlet-mapping
 * 매핑 정보 [URL 매핑 설정 하기]
 * 
 * 2. 다음과 같은 설정들 이용하기
 * [서블릿/서비스/보안] contextConfigLocation
 * 
 * 3. 필터 설정 예) 인코딩 / 보안 등등등
 * 4. 웰컴파일 리스트 등등
 * 5. 기타 등등
 */

/*
 * 
 */

public class DispatcherSerlvetInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		
//		return new Class[] {
//			SecurityContextConfig.class
//		};
		
		return null;
		
		
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		/*
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/servlet-context.xml</param-value>  	
  	    </init-param>
  	    */
		//위의 설정을 대신하기 위한 자바 클래스를 다음으로 한다.
		/* class ServletContextConfig{} 에서 설정하자요~~~ */
		/*
		new Class[] { 
				ServletContextConfig.class,
				TilestConfig.class
		};
		*/
		return new Class[] { 
				ServletContextConfig.class,
				ServiceContextConfig.class,
				SecurityContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		/*
	  <servlet-mapping>
	  	<servlet-name>dispatcher</servlet-name>
	  	<url-pattern>/</url-pattern>
	  </servlet-mapping>
	  */
	  
		return new String[] {"/"};
	}
	
	@Override
    protected Filter[] getServletFilters() {
		return null;
    }

	
}
