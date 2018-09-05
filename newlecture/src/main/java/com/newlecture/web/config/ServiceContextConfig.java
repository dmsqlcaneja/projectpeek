package com.newlecture.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@ComponentScan(basePackages= {"com.newlecture.web.dao.mybatis", "com.newlecture.web.service"})
public class ServiceContextConfig{
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		/* MS DB 설정 */
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://211.238.142.251:1433;databaseName=lecture");
		dataSource.setUsername("sist");
		dataSource.setPassword("dclass");
		
		
		/* Mysql or mariaDB
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/peekdb?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("root");
		dataSource.setPassword("Dmsqlc79**");
		*/
		
		/* PULL 관리*/
		dataSource.setRemoveAbandoned(true);
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		
		//dataSource.setRemove
		
		
		
		return dataSource;
	}
	//sqlSessionFactory(BasicDataSource dataSource) IOC 보따리에서 찾앗!
	@Bean
	public SqlSessionFactory sqlSessionFactory(BasicDataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver()
					.getResources("classpath:com/newlecture/web/dao/mybatis/mapper/*.xml"));
		
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		//sqlSessionFactoryBean은 맞지만 팩토리 객체를 반환하기 때문에.
		//SqlSessionFactoryBean->SqlSessionFactory로 변경하고 SqlSessionFactory return은 .getObject를 해야함
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
}
