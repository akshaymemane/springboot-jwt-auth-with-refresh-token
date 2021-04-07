package com.jwtauth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.model.AuthenticationRequest;
import com.jwtauth.model.AuthenticationResponse;
import com.jwtauth.model.UserMaster;
import com.jwtauth.model.UserRoleMaster;
import com.jwtauth.service.CustomUserDetailsService;
import com.jwtauth.service.UserMasterService;
import com.jwtauth.service.UserRoleMasterService;
import com.jwtauth.util.JwtUtil;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserMasterService userMasterService;
	
	@Autowired
	private UserRoleMasterService userRoleMasterService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		UserMaster user = userMasterService.findByEmailId(userdetails.getUsername());
		String role = "";
		for(GrantedAuthority auth:  userdetails.getAuthorities()) {
			role = auth.getAuthority();
		}
		String token = jwtUtil.generateToken(userdetails);
		return ResponseEntity.ok(AuthenticationResponse.builder()
				.token(token)
				.role(role)
				.userId(user.getUserId())
				.userName(user.getUserName())
				.build());
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserRoleMaster userRoleMaster) throws Exception {
		return ResponseEntity.ok(userRoleMasterService.save(userRoleMaster));
	}
	
	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(AuthenticationResponse.builder()
				.token(token)
				.build());
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}
