package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongwordDto;

@Mapper
public interface SongDao {
	
	List<SongDto> newest_list(Map<String, Object> map);
	
	SongDto get_song(int id);
	
	List<SongwordDto> get_word(int id);
	
	NamuwikiDto search_word(String word);

}
