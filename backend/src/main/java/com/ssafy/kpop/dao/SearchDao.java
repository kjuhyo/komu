package com.ssafy.kpop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;

@Mapper
public interface SearchDao {
	public List<SingerDto> getSingerByName(String singer_name);
	public List<SingerDto> getSongByTitle(String song_name);
	public List<SongDto> getSongBySingerName(String singer_name);
}
