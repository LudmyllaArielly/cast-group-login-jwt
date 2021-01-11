package com.spring.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.spring.jwt.repository"})
@ComponentScan(basePackages = {"com.spring.jwt.*"})
@EnableAutoConfiguration 
@RestController
@EnableTransactionManagement 
@EnableWebMvc
public class LoginCastGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginCastGroupApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "admin";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println(encodedPassword);

	}

}
