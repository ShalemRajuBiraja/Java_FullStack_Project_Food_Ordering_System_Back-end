package com.shalemraju.food_ordering_website.service;

import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import Fullstack.Amazon_backend.entity.UserTableEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	// 1. Import secret key and create security key
	// 2. Generate JWT token by using user details and security key
	
	@Value("${jwt.secret_key}")
	private String jwtsecretKey;
	
	public Key generateSecurityKey() {
		return Keys.hmacShaKeyFor(jwtsecretKey.getBytes());
	}
	
	
	
    public String generateJwtToken(UserTableEntity userData) {
    	
    	Date tokenGeneratedTimeDate = new Date();
    	Date tokenExpirationDate = new Date(tokenGeneratedTimeDate.getTime() + 24 * 60 * 60 * 1000);
    	
    	
    	
    	//jwt key creation
    	String jwtTokenString = Jwts.builder()
    	.claim("role", userData.getRole())
    	.subject(userData.getEmail())
    	.issuedAt(tokenGeneratedTimeDate)
    	.expiration(tokenExpirationDate)
    	.signWith(generateSecurityKey())
    	.compact();
    	
    	return jwtTokenString;
    }
    
    
    //checking validation of token
    /*
     * Try to get claims -> if success ok, else catch exception and return false or throw error
     * check if it not expired -> if expired return false or throw error 
     */
    public Claims getJwtClaims(String token) {
    	
    	SecretKey secretKey = new SecretKeySpec(jwtsecretKey.getBytes(), "HmacSHA256");
    	
    	Claims claims = Jwts.parser()
    				        .verifyWith(secretKey)
    				        .build()
    				        .parseSignedClaims(token)
    				        .getPayload();
    	return claims;
    }
    
    public Boolean verifyJwtToken(String token) {
    	Claims claims = getJwtClaims(token);
    	Boolean isValidBoolean = claims.getExpiration().after(new Date());
    	return isValidBoolean;
    }

}
