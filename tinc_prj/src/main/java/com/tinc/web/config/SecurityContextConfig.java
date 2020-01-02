package com.tinc.web.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityContextConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/teacher/**").hasRole("TEACHER")
		.antMatchers("/student/**").hasRole("STUDENT")
		.antMatchers("/member/home").authenticated()
		.and()
		.formLogin()
		.loginPage("/member/login")
		.loginProcessingUrl("/member/login")
		.defaultSuccessUrl("/index")
		.and()
		.logout()
		.logoutUrl("/member/logout")
		.logoutSuccessUrl("/index")
		.and()
		.csrf()
		.disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT id, pwd password, 1 disabled FROM MEMBER WHERE id=?")
		.authoritiesByUsernameQuery("SELECT MEMBER_ID id, ROLE_ID roleId FROM MEMBER_ROLE WHERE MEMBER_ID=?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
