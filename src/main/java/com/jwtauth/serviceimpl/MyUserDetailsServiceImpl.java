package com.jwtauth.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jwtauth.model.MyUserDetails;
import com.jwtauth.model.UserMaster;
import com.jwtauth.repository.UserMasterRepository;
import com.jwtauth.service.MyUserDetailsService;

@Component
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	@Autowired
	UserMasterRepository userMasterRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMaster user = userMasterRepository.findByUserEmailId(username);
		return new MyUserDetails(user);
	}
}
