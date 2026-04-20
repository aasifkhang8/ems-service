//package com.org.ems_service.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
////@ComponentScan("com.org.ems_service")
//@PropertySource("classpath:application.properties")
//public class AppConfig {
//	@Autowired
//	private Environment env;
//
//	private static final String URL = "dbUrl";
//	private static final String USER = "dbUserName";
//	private static final String PASSWORD = "dbPassWord";
//	private static final String DRIVER = "driverClassName";
//
//	@Bean
//	DataSource datasource() {
//		DriverManagerDataSource dmds = new DriverManagerDataSource();
//		dmds.setUrl(env.getProperty(URL));
//		dmds.setUsername(env.getProperty(USER));
//		dmds.setPassword(env.getProperty(PASSWORD));
//		dmds.setDriverClassName(env.getProperty(DRIVER));
//		return dmds;
//	}
//
//}
