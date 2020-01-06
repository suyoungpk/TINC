package com.tinc.web.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityContextConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN") // 권한? db에 해당하는 테이블이 없음
		.antMatchers("/teacher/**").hasRole("TEACHER")
		.antMatchers("/student/**").hasRole("STUDENT")
		.antMatchers("/member/home").authenticated()
		.and()
		.formLogin() // 로그인처리
		.loginPage("/member/login")
		.loginProcessingUrl("/member/login")
		.defaultSuccessUrl("/index") // 로그인 성공시 기본페이지
		.and()
		.logout() // 로그아웃처리
		.logoutUrl("/member/logout")
		.logoutSuccessUrl("/index") // 로그아웃시 기본페이지
		.and()
		.csrf() // 보안키 설정
		.disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{		
		auth.jdbcAuthentication() // 권한 쿼리
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT id, pwd password, 1 disabled FROM MEMBER WHERE id=?")
		.authoritiesByUsernameQuery("SELECT MEMBER_ID id, ROLE_ID roleId FROM MEMBER_ROLE WHERE MEMBER_ID=?") // 권한테이블이 없어서 이쪽은 필요없을듯
		.passwordEncoder(new BCryptPasswordEncoder()); // 비밀번호 암호화
	}
}
