package com.ssafy.kpop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.SingerDto;

@Mapper
public interface SearchDao {
	public List<SingerDto> getSingerByName(String singer_name);
	
}
