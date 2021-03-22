package com.ssafy.kpop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;

@Mapper
public interface SingerDao {
	
	List<SongDto> songlist(String singer_name);
	List<SingerchatDto> chatlist();
	
	

}
