package com.jwtauth.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jwtauth.model.RoleMaster;
import com.jwtauth.repository.RoleMasterRepository;
import com.jwtauth.service.RoleMasterService;

@Component
public class RoleMasterServiceImpl implements RoleMasterService {

	@Autowired
	RoleMasterRepository masterRoleRepository;

	@Override
	public RoleMaster save(RoleMaster masterRole) {
		return masterRoleRepository.save(masterRole);
	}

	@Override
	public List<RoleMaster> findAll() {
		return masterRoleRepository.findAll();
	}

	@Override
	public Optional<RoleMaster> findById(Integer roleId) {
		return masterRoleRepository.findById(roleId);
	}

}
