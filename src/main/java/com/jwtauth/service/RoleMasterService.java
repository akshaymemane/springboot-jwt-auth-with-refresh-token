package com.jwtauth.service;

import java.util.List;
import java.util.Optional;

import com.jwtauth.model.RoleMaster;

public interface RoleMasterService {

	RoleMaster save(RoleMaster masterRole);

	List<RoleMaster> findAll();

	Optional<RoleMaster> findById(Integer roleId);
}
