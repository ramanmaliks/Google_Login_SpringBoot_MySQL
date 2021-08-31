package com.google.login;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustOAuth2User implements OAuth2User {

	
	private OAuth2User oauth2User;
	private String clientName;
	
	
	
	public String getClientName() {
		return clientName;
	}

	public CustOAuth2User(OAuth2User oauth2User, String clientName) {
		this.oauth2User = oauth2User;
		this.clientName = clientName;
	}

	@Override
	public Map<String, Object> getAttributes() {
		
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		
		return oauth2User.getAttribute("name");
	}

	
	public String getFullName() {
		
		return oauth2User.getAttribute("name");
		
	}
	
	public String getEmail() {
		return oauth2User.getAttribute("email");
	}
}
