package com.hanimum.gateway.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@Value("${jwt.jwtSecret}")
	private String jwtSecret;
	
	@Value("${jwt.jwtExpirationInMs}")
	private int jwtExpirationInMs;
	
	
	public Long getUseridFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
			
		}catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		}catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		}catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		}catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		}catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty");
		}
		
		return false;
	}
	

}
