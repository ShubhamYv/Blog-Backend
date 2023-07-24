package com.sky.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sky.config.AppConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String secret = AppConstants.SECRET_KEY;

	public String getUserNameFromToken(String token) {

		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {

		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {

		final Date expiration = getExpirationFromToken(token);

		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());

	}

	public String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUserNameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}
}
