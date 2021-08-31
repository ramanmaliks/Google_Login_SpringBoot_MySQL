package com.google.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired CustOAuth2UserService oAuth2UserService;

//	@Autowired OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

	@Autowired
	UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/", "/login", "/oauth/**")
			.authenticated()
			.anyRequest().permitAll()		
			.and()
			.formLogin()
					.loginPage("/login")
					.permitAll()
		  	.and() 
		  	.oauth2Login() 
		  			.loginPage("/login") 
		  			.userInfoEndpoint()
		  			.userService(oAuth2UserService) 
		  			.and() 
		  			.successHandler(new AuthenticationSuccessHandler() {
		  
		  @Override
		  public void onAuthenticationSuccess(HttpServletRequest request,
		  HttpServletResponse response, Authentication authentication) throws
		  IOException, ServletException {
		  
		  CustOAuth2User oauthUser = (CustOAuth2User) authentication.getPrincipal();
		  
		  userService.processOAuthPostLogin(oauthUser.getEmail());
		  System.out.println("EMAIL ADDRESS       :" + oauthUser.getEmail()); 
		  System.out.println("CLIENT NAME         :" + oauthUser.getClientName()); 
		  System.out.println("FULL NAME           :" + oauthUser.getFullName()); 
		  System.out.println("NAME                :" + oauthUser.getName()); 
		  System.out.println("ATTRIBUTE - NAME    :" + oauthUser.getAttribute("name")); 
		  System.out.println("ATTRIBUTE - COUNTRY :" + oauthUser.getAttribute("locale")); 
		  System.out.println("ATTRIBUTE - CITY    :" + oauthUser.getAttribute("city")); 
		  
		  
		  
		  response.sendRedirect("/menu"); }
		  
		  
		  
		  });
		  
		 
	}
}
