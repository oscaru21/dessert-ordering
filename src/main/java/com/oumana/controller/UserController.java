package com.oumana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.entity.User;
import com.oumana.payload.UserDto;
import com.oumana.security.JwtTokenProvider;
import com.oumana.service.UserService;

@RestController
@RequestMapping(path = "/api/users/", consumes = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("register")
	public ResponseEntity<UserDto> register(@RequestBody User user) {
		User registeredUser = userService.registerUser(user);
		UserDto response = new UserDto();
		response.setEmail(registeredUser.getEmail());
		response.setName(registeredUser.getName());
		response.setUsername(registeredUser.getUsername());
		response.setToken(jwtTokenProvider.generateToken(registeredUser.getId()));
		return new ResponseEntity<UserDto>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<UserDto> login(@RequestBody User user) {
		User loggedUser = userService.loginUser(user);
		UserDto response = new UserDto();
		response.setEmail(loggedUser.getEmail());
		response.setName(loggedUser.getName());
		response.setUsername(loggedUser.getUsername());
		response.setToken(jwtTokenProvider.generateToken(loggedUser.getId()));
		return new ResponseEntity<UserDto>(response, HttpStatus.OK);
	}

}
