package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;

@Mapper
public interface SingerDao {
	
	List<SongDto> songlist(String singer_name);
	List<SingerchatDto> chatlist();
	List<SongDto> all_song(Map<String, Object> map);
	int search_song(String singer_name);
	
	

}
