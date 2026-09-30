package com.ecommerce.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	//GET SECRET KEY
	
	private SecretKey getSignKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
		
	}
	
	
	//GENERATE TOKEN
	
	public String generateToken(String username) {
		
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(
						new Date(
								System.currentTimeMillis()+expiration))
				.signWith(getSignKey(),
						SignatureAlgorithm.HS256)
				.compact();
				
		
	}
	
	//EXTRACT USERNAME
	
	public String extractUsername(String token) {
		
		Claims claims =Jwts.parserBuilder()
		               .setSigningKey(getSignKey())
		               .build()
		               .parseClaimsJws(token)
		               .getBody();
				
		return claims.getSubject();
		
	}
			
	
	//VALIDATE TOKEN
	
	public boolean validateToken(String token,String username) {
		
		String extractedUsername=
				       extractUsername(token);
		return extractedUsername.equals(username);
		
	
	}
			

}
