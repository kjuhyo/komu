package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.kpop.dao.SearchDao;
import com.ssafy.kpop.dto.ComPostDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;

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
	public List<SongDto> getSongBySingerName(String singer_name) {
		return searchdao.getSongBySingerName(singer_name);
	}

	@Override
	@Transactional
	public List<ComPostDto> searchByHash(List<Integer> keywords) {
		Map<String, Object> map=new HashMap<>();
		map.put("num", keywords.size());
		map.put("keywords", keywords);
		
		//검색 결과에 맞는 블로그 글 아이디 리스트
		List<Integer> list=searchdao.searchByHash(map); 
		
		List<ComPostDto> result=searchdao.getPostInfo(list); //Community랑 유저정보, 프로필사진
		String nick="";
		for(ComPostDto post : result) {
			nick=searchdao.getUserInfo(post.getCid()); //글쓴사람 닉네임 반환
			post.setNickname(nick);
		}
		return result;
	}



}
