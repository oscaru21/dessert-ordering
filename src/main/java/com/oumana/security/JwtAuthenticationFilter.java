package com.oumana.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oumana.repository.UserRepo;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepo userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get JWT token from the http Authorization header
		String token = getJWTFromRequestString(request);

		// validate token
		if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			// load user associated with the token
			UserDetails userDetails = userRepo
					.findById(jwtTokenProvider.getIdFromJwt(token))
					.orElse(null);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			// set spring security
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			System.out.println(SecurityContextHolder.getContext());
		}
		filterChain.doFilter(request, response);		
	}

	private String getJWTFromRequestString(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.split(" ")[1];
		}
		return null;
	}

}
