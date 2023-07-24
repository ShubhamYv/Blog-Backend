package com.sky.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestToken = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = this.jwtTokenHelper.getUserNameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt Token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt Token has expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid Jwt");
			}
		} else {
			System.out.println("Jwt token does not begin with Bearer");
		}

		// we get the token, now validate
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			System.out.println("Username is null or context is not null");
		}

		filterChain.doFilter(request, response);
	}

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		String requestHeader = request.getHeader("Authorization");
//		logger.info(" Header : {}", requestHeader);
//		String token = null;
//		String username = null;
//		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
//			token = requestHeader.substring(7);
//			try {
//				username = this.jwtTokenHelper.getUserNameFromToken(token);
//			} catch (IllegalArgumentException e) {
//				logger.info("Unable to get Jwt Token");
//				e.printStackTrace();
//			} catch (ExpiredJwtException e) {
//				logger.info("Jwt Token has expired");
//				e.printStackTrace();
//			} catch (MalformedJwtException e) {
//				logger.info("Invalid Jwt");
//				e.printStackTrace();
//			}
//		} else {
//			logger.info("Invalid Header Value");
//		}
//
//		// we get the token, now validate
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//			Boolean validateToken = this.jwtTokenHelper.validateToken(token, userDetails);
//			if (validateToken) {
//				// set the authentication
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//			}
//		} else {
//			logger.info("Validation fails...");
//		}
//
//		filterChain.doFilter(request, response);
//
//	}

}
