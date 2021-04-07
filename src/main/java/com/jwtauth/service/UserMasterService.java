package com.jwtauth.service;

import java.util.List;
import java.util.Optional;

import com.jwtauth.model.UserMaster;

public interface UserMasterService {

	UserMaster save(UserMaster masterUser);

	List<UserMaster> findAll();

	Optional<UserMaster> findById(Integer userId);

	UserMaster findByEmailId(String emailId);

	Optional<UserMaster> findByContactNumber(Long contactNumber);

	void encodePassword();

	Integer UpdatePasswordByEmailId(String emailId, String password);

	List <UserMaster> findActiveUsers();

	List <UserMaster> findInactiveUsers();
	
	Integer findCandidateCount();
	
	Integer findRecruiterCount();

	Integer findActiveCandidateCount();

	Integer findActiveRecruiterCount();

}
