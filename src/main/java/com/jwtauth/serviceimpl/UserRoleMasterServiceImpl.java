package com.jwtauth.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jwtauth.model.UserMaster;
import com.jwtauth.model.UserRoleMaster;
import com.jwtauth.repository.UserMasterRepository;
import com.jwtauth.repository.UserRoleMasterRepository;
import com.jwtauth.service.UserRoleMasterService;

@Component
public class UserRoleMasterServiceImpl implements UserRoleMasterService {

	@Autowired
	UserRoleMasterRepository userRoleMasterRepository;
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserRoleMaster save(UserRoleMaster userRoleMaster) {
		userRoleMaster.getUserMaster().setUserPassword(bcryptEncoder.encode(userRoleMaster.getUserMaster().getUserPassword()));
		UserMaster user = userMasterRepository.save(userRoleMaster.getUserMaster());
		userRoleMaster.setUrmUserId(user.getUserId());
		return userRoleMasterRepository.save(userRoleMaster);
	}

	@Override
	public List<UserRoleMaster> findAll() {
		return userRoleMasterRepository.findAll();
	}

	@Override
	public Optional<UserRoleMaster> findById(Integer urId) {
		return userRoleMasterRepository.findById(urId);
	}

}
