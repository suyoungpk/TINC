package com.tinc.web.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityContextConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;	
	
//	@Bean
//	public AuthenticationSuccessHandler successHandler()
//	{
//		AuthenticationSuccessHandler successHandler = new MyHomeRedirectionHandler();
//		
//		return successHandler;
//	}

	@Override
	   protected void configure(HttpSecurity http) throws Exception
	   {
	      http.authorizeRequests()
	      .antMatchers("/member/friendList").hasRole("MEMBER")
	      .antMatchers("/member/friendSetting").hasRole("MEMBER")
	      .and()
	      .formLogin() // 로그인처리
	      .loginPage("/member/login")
	      .loginProcessingUrl("/member/login")
	      .defaultSuccessUrl("/member/friendList") // 로그인 성공시 기본페이지
	      .and()
	      .logout() // 로그아웃처리
	      .logoutUrl("/member/logout")
	      .logoutSuccessUrl("/member/login") // 로그아웃시 기본페이지
	      .and()
	      .csrf() // 보안키 설정
	      .disable();
	   }
	   
	   
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception
	   {      
	      auth.jdbcAuthentication() // 권한 쿼리
	      .dataSource(dataSource)
	      .usersByUsernameQuery("SELECT id, password, 1 disabled FROM Member WHERE id=?")
	      .authoritiesByUsernameQuery("SELECT memberId id, roleId roleId FROM MemberRole WHERE memberId=?")
	      .passwordEncoder(new BCryptPasswordEncoder()); // 비밀번호 암호화
	      
//		  auth
//	         .inMemoryAuthentication()
//	            .withUser("newlec")
//	               .password("{noop}1111").roles("MEMBER")
//	               
//	               ; // 사용자 정보를 직접 사용.
	   }
	   
	   @Override
	   public void configure(WebSecurity web) throws Exception {
	       web.ignoring().antMatchers("/resource/**");
	   }
	   

}
