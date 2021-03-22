package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.Map;

import com.ssafy.kpop.dto.LoginCallBackDto;
import com.ssafy.kpop.dto.UserDto;

public interface UserService {
	public UserDto findByProvider(HashMap<String, Object> map);
	public UserDto findById(String uid);
	public LoginCallBackDto save(HashMap<String, Object> user);
	public void updateUser(UserDto user);
}
