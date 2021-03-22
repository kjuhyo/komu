package com.ssafy.kpop.service;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService{

	@Override
	public <T> String create(String key, T data, String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsable(String jwt) {
		// TODO Auto-generated method stub
		return false;
	}

}
