package com.google.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		CustOAuth2User custOAuth2User = (CustOAuth2User)authentication.getPrincipal();
		String name = custOAuth2User.getName();
		String email = custOAuth2User.getEmail();
		String country = request.getLocale().getCountry();
		String clientName = custOAuth2User.getClientName();
		
		
		System.out.println("********** NAME ********* :" + name);
		System.out.println("********** EMAIL ********* :" + email);
		System.out.println("********** COUNTRY ********* :" + country);
		System.out.println("********** CLIENT NAME ********* :" + clientName);
		
	}

	
}
