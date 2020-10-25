package com.restjwtauth.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.restjwtauth.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	static final String HEADER_STRING = "Authorization";
	static final long EXPIRATION_TIME = 1800000;
	static final String SECRET = "Pitang";

	public String generateToken(User user) {

		String token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setSubject(user.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();

		return token;
	}

	private static Claims getClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	public String getAccessFromToken(String token) {
		
		return getClaimsFromToken(token).getSubject();
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = getClaimsFromToken(token).getExpiration();
		return expiration.before(new Date(System.currentTimeMillis()));
	}
}
