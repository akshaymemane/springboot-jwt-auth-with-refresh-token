package com.jwtauth.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtauth.model.UserMaster;
import com.jwtauth.model.UserRoleMaster;
import com.jwtauth.repository.UserMasterRepository;
import com.jwtauth.repository.UserRoleMasterRepository;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRoleMasterRepository userRoleMasterRepository;
	
	@Autowired
	private UserMasterRepository masterUserRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRoleMasterService userRoleMasterService;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		
			
		UserRoleMaster user = userRoleMasterRepository.findByUrmUserId(masterUserRepository.findByUserEmailId(username).getUserId()).get();
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRoleMaster().getRoleName()));
			return new User(user.getUserMaster().getUserEmailId(), user.getUserMaster().getUserPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);	}

	/*
	 * public UserMaster save(SignUpRequest request) {
	 * 
	 * if(request.getRoleId() == 1) {
	 * request.getCandidate().getUserMaster().setUserPassword(bcryptEncoder.encode(
	 * request.getCandidate().getUserMaster().getUserPassword())); UserMaster
	 * masterUser =
	 * masterUserRepository.save(request.getCandidate().getUserMaster());
	 * request.getCandidate().setCandUserId(masterUser.getUserId());
	 * candidateService.save(request.getCandidate());
	 * userRoleMasterService.save(UserRoleMaster.builder().urUserId(masterUser.
	 * getUserId()).urRoleId(request.getRoleId()).build()); return masterUser; }else
	 * if(request.getRoleId() == 2) {
	 * request.getRecruiter().getUserMaster().setUserPassword(bcryptEncoder.encode(
	 * request.getRecruiter().getUserMaster().getUserPassword())); UserMaster
	 * masterUser =
	 * masterUserRepository.save(request.getRecruiter().getUserMaster());
	 * request.getRecruiter().setRecrUserId(masterUser.getUserId());
	 * recruiterService.save(request.getRecruiter());
	 * userRoleMasterService.save(UserRoleMaster.builder().urUserId(masterUser.
	 * getUserId()).urRoleId(request.getRoleId()).build()); return masterUser; }
	 * return null; }
	 */	

	public UserMaster save(UserRoleMaster user) {
		user.getUserMaster().setUserPassword(bcryptEncoder.encode(user.getUserMaster().getUserPassword()));
		UserMaster masterUser =  masterUserRepository.save(user.getUserMaster());
		userRoleMasterService.save(UserRoleMaster.builder().urmUserId(masterUser.getUserId()).urmRoleId(user.getUrmRoleId()).build());
		return masterUser;
	}

}
