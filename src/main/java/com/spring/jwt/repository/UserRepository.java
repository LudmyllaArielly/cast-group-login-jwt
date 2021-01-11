package com.spring.jwt.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jwt.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
}
