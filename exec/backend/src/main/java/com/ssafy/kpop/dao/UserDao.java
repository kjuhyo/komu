package com.ssafy.kpop.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.UserDto;

@Mapper
public interface UserDao {
	public UserDto findByProvider(HashMap<String, String> map);
	public UserDto findById(String uid);
	public void save(UserDto user);
	public void updateUser(UserDto user);
	public String findNickName(String uid);
}
