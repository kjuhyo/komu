package com.ssafy.kpop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.UserDto;
import com.ssafy.kpop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired 
	UserService userService;
		
	@GetMapping("/")
	public UserDto login() {
		return userService.login();
	}
	
}
