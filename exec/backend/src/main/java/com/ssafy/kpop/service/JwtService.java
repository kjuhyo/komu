package com.ssafy.kpop.service;

import java.util.Map;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUserId(String jwt);
	boolean isUsable(String jwt);
}
