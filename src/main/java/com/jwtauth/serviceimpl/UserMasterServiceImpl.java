package com.jwtauth.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jwtauth.model.UserMaster;
import com.jwtauth.repository.UserMasterRepository;
import com.jwtauth.service.UserMasterService;

@Component
public class UserMasterServiceImpl implements UserMasterService {

	@Autowired
	UserMasterRepository masterUserRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	MapSqlParameterSource params = new MapSqlParameterSource() {
	};

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public UserMaster save(UserMaster masterUser) {
		return masterUserRepository.save(masterUser);
	}

	@Override
	public List<UserMaster> findAll() {
		return masterUserRepository.findAll();
	}

	@Override
	public Optional<UserMaster> findById(Integer userId) {
		return masterUserRepository.findById(userId);
	}

	@Override
	public UserMaster findByEmailId(String emailId) {
		return masterUserRepository.findByUserEmailId(emailId);
	}

	@Override
	public Optional<UserMaster> findByContactNumber(Long contactNumber) {
		return masterUserRepository.findByUserContactNumber(contactNumber);
	}

	@Override
	public void encodePassword() {
		String query = "select * from ";
		// List<String> pass = namedJdbcTemplate.query(query,params,new
		// String());

	}

	@Transactional
	@Override
	public Integer UpdatePasswordByEmailId(String emailId, String password) {
		return masterUserRepository.UpdatePasswordByEmailId(emailId, bcryptEncoder.encode(password));
	}

	@Override
	public List<UserMaster> findActiveUsers() {
		return masterUserRepository.findActiveUsers();
	}

	@Override
	public List<UserMaster> findInactiveUsers() {
		return masterUserRepository.findInactiveUsers();
	}

	@Override
	public Integer findCandidateCount() {
		return masterUserRepository.findCandidateCount();
	}

	@Override
	public Integer findRecruiterCount() {
		return masterUserRepository.findRecruiterCount();
	}

	@Override
	public Integer findActiveCandidateCount() {
		return masterUserRepository.findActiveCandidateCount();
	}

	@Override
	public Integer findActiveRecruiterCount() {
		return masterUserRepository.findActiveRecruiterCount();
	}

}
