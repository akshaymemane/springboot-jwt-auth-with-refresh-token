package com.jwtauth.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_master")
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_email_id")
	private String userEmailId;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_contact_number")
	private Long userContactNumber;

	@Column(name = "user_picture_url")
	private String userPictureUrl;

	@Column(name = "user_picture_filepath")
	private String userPictureFilepath;

	@Column(name = "user_is_active")
	private Boolean userIsActive;
	
	@Column(name = "user_last_updated_by")
	private String userLastUpdatedBy;

	@CreatedDate
	@CreationTimestamp
	@Column(name = "user_created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date userCreatedDate;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "user_last_updated_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
	private Date userLastUpdatedDate;

}