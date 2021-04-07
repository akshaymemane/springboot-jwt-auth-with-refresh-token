package com.jwtauth.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_role_master")
public class UserRoleMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "urm_id")
	private Integer urmId;
	
	@Column(name = "urm_user_id")
	private Integer urmUserId;
	
	@Column(name = "urm_role_id")
	private Integer urmRoleId;

	@CreatedDate
	@CreationTimestamp
	@Column(name = "urm_created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date urmCreatedDate;

	@JoinColumn(name = "urm_user_id", unique = true,insertable = false,updatable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private UserMaster userMaster;

	@JoinColumn(name = "urm_role_id", unique = true,insertable = false,updatable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private RoleMaster roleMaster;

}