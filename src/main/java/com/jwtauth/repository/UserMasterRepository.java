package com.jwtauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jwtauth.model.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Integer>{
	
	UserMaster findByUserEmailId(String userName);

	Optional<UserMaster> findByUserContactNumber(Long contactNumber);
	
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true,value = "UPDATE master_user SET  user_password=?2 WHERE user_email_id =?1")
Integer UpdatePasswordByEmailId(String emailId, String password);

	@Query(nativeQuery=true,value ="select * from master_user where user_is_active=1")
	List<UserMaster> findActiveUsers();

	@Query(nativeQuery=true,value ="select * from master_user where user_is_active=0")
	List<UserMaster> findInactiveUsers();

	@Query(nativeQuery=true,value="select count(*) from master_user ")
	Integer findCandidateCount();

	@Query(nativeQuery=true,value="select count(*) from recruiter")
	Integer findRecruiterCount();

	@Query(nativeQuery=true,value="select count(*) from master_user where user_is_active=1")
	Integer findActiveCandidateCount();

	@Query(nativeQuery=true,value="select count(*) from recruiter where recr_is_active=1")
	Integer findActiveRecruiterCount();
}
