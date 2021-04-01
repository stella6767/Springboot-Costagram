package com.example.costagram.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.costagram.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private User user;
	
	private Map<String,Object> attributes;
	private boolean oAuth = false;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String,Object> attributes) {
		this.user = user;
		this.attributes = attributes;
		this.oAuth = true;  //어차피 attributes 들어오는 게 Oauth 로그인이라 true 설정  
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("ROLE_"+user.getRole());
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->"ROLE_"+user.getRole());
		return collectors;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "몰라";
	}
	
	

}
