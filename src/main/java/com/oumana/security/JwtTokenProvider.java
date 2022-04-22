package com.oumana.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
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
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app.jwt-expiration-milliseconds}")
	private Integer jwtExpirationInMs;
	
	//generate token
	public String generateToken(long id) {
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder()
				.setId(String.valueOf(id))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		return token;
	}
	
	//get id from token
	public Long getIdFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getId());
	}
	
	//validate jwt token
	public boolean validateToken(String token) {
		try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
        	throw new RuntimeException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	throw new RuntimeException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	throw new RuntimeException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	throw new RuntimeException("JWT claims string is empty");
        }
	}
}
