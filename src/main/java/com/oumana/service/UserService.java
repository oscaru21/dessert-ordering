package com.oumana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oumana.entity.User;
import com.oumana.repository.UserRepo;


@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public User registerUser(User user) {
		//check if email already exists
		if(userRepo.existsByUsername(user.getUsername())) {
			throw new RuntimeException("User already Exists");
		}
		
		//hash password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//save user to db
		return userRepo.save(user);
	}

	public User loginUser(User user) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User authenticatedUser = userRepo.findByUsername(user.getUsername()).get();
		authenticatedUser.getRoles().stream().forEach(role -> System.out.println(role.getName()));
		
		return authenticatedUser;
	}
}
