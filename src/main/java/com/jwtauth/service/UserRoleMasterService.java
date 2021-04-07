package com.jwtauth.service;

import java.util.List;
import java.util.Optional;

import com.jwtauth.model.UserRoleMaster;

public interface UserRoleMasterService {

	UserRoleMaster save(UserRoleMaster masterUserRole);

	List<UserRoleMaster> findAll();

	Optional<UserRoleMaster> findById(Integer urId);

}
