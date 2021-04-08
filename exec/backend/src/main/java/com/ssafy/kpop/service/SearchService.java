package com.ssafy.kpop.service;

import java.util.List;
import java.util.Map;

import com.ssafy.kpop.dto.ComPostDto;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;

public interface SearchService {
	public List<SingerDto> getSingerByName(String singer_name);
	public List<SongListDto> getSongLikeSort(String song_name);
	public List<SongListDto> getSongNewSort(String singer_name);
	public List<CommunityDto> searchCommunity(String c_title);
	
	public List<ComPostDto> searchByHash(List<Integer> keywords);
}
