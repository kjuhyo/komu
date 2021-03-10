package com.ssafy.kpop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.UserDao;
import com.ssafy.kpop.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userdao;
	
	public UserDto login() {
		return userdao.login();
	}
}
