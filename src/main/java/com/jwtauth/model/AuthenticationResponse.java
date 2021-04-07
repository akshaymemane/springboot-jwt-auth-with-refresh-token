package com.jwtauth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

	private String token;
	private String role;
	private Integer userId;
	private String userName;
}
