package com.oumana.controller;

import java.util.stream.Collectors;

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
@RequestMapping(path = "/api/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
	
	@Autowired
	private UserService userService;
	
}
