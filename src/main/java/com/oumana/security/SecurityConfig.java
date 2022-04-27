package com.oumana.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oumana.repository.UserRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepo userRepo;
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
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
			.antMatchers(HttpMethod.GET, "/api/menu-items").permitAll()
			.antMatchers(HttpMethod.GET, "/api/menu-items/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/menu-items").hasRole("ADMIN")
			.antMatchers("/api/menu-items/**/item-reviews").permitAll()
			.antMatchers(HttpMethod.GET, "/api/order").hasRole("ADMIN")
			.antMatchers("/oauth2/**")
            .permitAll()
			.anyRequest()
			.authenticated();
		
		//JWT token filter
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(
				username -> userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")))
				.passwordEncoder(passwordEncoder());
	}
	
}
