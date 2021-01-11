package com.spring.jwt.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.model.UserLoginDTO;
import com.spring.jwt.security.JWTTokenUtil;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class UserResource {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenUtil jwtTokenUtil;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authentication(@RequestBody UserLoginDTO userLoginDTO) {
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),
															userLoginDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenUtil.generateToken(authentication);
			 authentication.getPrincipal();
			return ResponseEntity.ok(jwt);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" Failed to Login: " + e.getMessage());
		}
	}

}
