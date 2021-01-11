package com.spring.jwt.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.spring.jwt.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenUtil {

	private String secret = "15457757";
	private long expiratiom = 86400000L;

	public String generateToken(Authentication authentication) {
		UserDetailsImpl userDetailsPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		String tokenJson = Jwts.builder().setSubject(userDetailsPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + expiratiom)).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		return tokenJson;
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException | MalformedJwtException | IllegalArgumentException e) {
			System.out.println("Error: JWTTokenUtil " + e.getMessage());
		}

		return false;
	}

}
