package com.jwtauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailsService extends UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username);
}
