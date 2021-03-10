package com.ssafy.kpop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.UserDto;

@Mapper
public interface UserDao {
	public UserDto login();
}
