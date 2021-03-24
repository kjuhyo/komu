package com.ssafy.kpop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SearchDao;
import com.ssafy.kpop.dto.SingerDto;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDao searchdao;
	
	@Override
	public List<SingerDto> getSingerByName(String singer_name) {
		return searchdao.getSingerByName(singer_name);
	}

	@Override
	public List<SingerDto> getSongByTitle(String song_name) {
		return searchdao.getSongByTitle(song_name);
	}

	@Override
	public List<SingerDto> getSongBySingerName(String singer_name) {
		return searchdao.getSongBySingerName(singer_name);
	}

}
