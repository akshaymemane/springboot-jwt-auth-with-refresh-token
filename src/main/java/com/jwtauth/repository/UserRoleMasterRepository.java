package com.jwtauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtauth.model.UserRoleMaster;

public interface UserRoleMasterRepository extends JpaRepository<UserRoleMaster, Integer>
{

	Optional<UserRoleMaster> findByUrmUserId(Integer userId);
}
