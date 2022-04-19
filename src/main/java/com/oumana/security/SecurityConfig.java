package com.oumana.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oumana.repository.UserRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserRepo userRepo;
	
	public SecurityConfig(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// enable CORS and disable CSRF
		http.cors().and().csrf().disable();

		// Set session management to stateless
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
		// Set unathorized request exception handler
		http.exceptionHandling()
			.authenticationEntryPoint(
				(request, response, ex) -> {
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getMessage()
                    );
                }
			);

		// Set permissions to endpoints
		http.authorizeRequests()
			// login and register endpoints
			.antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
			.antMatchers(HttpMethod.POST, "/api/users/login").permitAll()
			.anyRequest().authenticated();
		
		//JWT token filter
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(
				username -> userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")))
				.passwordEncoder(passwordEncoder());
	}
}
