package com.ssafy.kpop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.NamuwikiDto;

@Mapper
public interface NamuDao {
	int insert(NamuwikiDto namu);
}
