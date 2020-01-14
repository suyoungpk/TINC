package com.tinc.web.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SMTPConfig
{
	@Bean
	public JavaMailSender mailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setHost("smtp.gmail.com");
		//mailSender.setHost("smtp.naver.com");
		mailSender.setPort(587);
		mailSender.setUsername("yupddok@gmail.com");
		mailSender.setPassword("ac6164ac");

		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", true);

		/*
		 * properties.put("mail.smtp.starttls.enable", true);
		 * properties.put("mail.smtp.starttls.required", true);
		 * properties.put("mail.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 * properties.put("mail.socketFactory.fallback", false);
		 */

		mailSender.setJavaMailProperties(properties);

		return mailSender;
	}
}
