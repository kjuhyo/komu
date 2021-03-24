package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SongDao;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongwordDto;

@Service
public class SongServiceImpl implements SongService {
	
	@Autowired
	SongDao sdao;
	
	@Override
	public List<SongDto> newest_list(int startList, int listSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("startList", startList);
		map.put("listSize", listSize);
		return sdao.newest_list(map);
	}
	
	@Override
	public SongDto get_song(int id) {
		// TODO Auto-generated method stub
		return sdao.get_song(id);
	}
	
	@Override
	public List<SongwordDto> get_word(int id) {
		// TODO Auto-generated method stub
		return sdao.get_word(id);
	}
	
	@Override
	public NamuwikiDto search_word(String word) {
		// TODO Auto-generated method stub
		return sdao.search_word(word);
	}
	

}
