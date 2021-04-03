package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.ComPostDto;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;

@Mapper
public interface SearchDao {
	public List<SingerDto> getSingerByName(String singer_name);
	public List<SongListDto> getSongLikeSort(String song_name);
	public List<SongListDto> getSongNewSort(String singer_name);
	public List<CommunityDto> searchCommunity(String c_title);
	
	public List<Integer> searchByHash(Map<String, Object> map);
	public List<ComPostDto> getPostInfo(List<Integer> list);
	public String getUserInfo(int cid);
}
