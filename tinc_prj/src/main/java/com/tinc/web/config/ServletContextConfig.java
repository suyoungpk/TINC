package com.tinc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com.tinc.web.controller")
public class ServletContextConfig {
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		resolver.setMaxUploadSize(1024*1024*400);
		resolver.setMaxUploadSizePerFile(1024*1024*100);
		
		return resolver;
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() { // 컨트롤러 return의 기본값 설정
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/"); // 앞
		resolver.setSuffix(".jsp"); // 뒤
		
		return resolver;
	}
}
