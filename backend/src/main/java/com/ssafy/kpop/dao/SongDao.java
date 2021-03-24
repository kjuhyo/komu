package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.SongDto;

@Mapper
public interface SongDao {
	
	List<SongDto> newest_list(Map<String, Object> map);

}
