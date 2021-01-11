package com.spring.jwt.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HomeResource {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/home")
	public String getHome() {
		return "Welcome";
	}

}
