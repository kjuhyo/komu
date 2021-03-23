package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SingerDao;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;

@Service
public class SingerServiceImpl implements SingerService{
	
	@Autowired
	SingerDao sdao;
	
	@Override
	public List<SongDto> songlist(String singer_name) {
		// TODO Auto-generated method stub
		return sdao.songlist(singer_name);
	}
	
	@Override
	public List<SingerchatDto> chatlist() {
		// TODO Auto-generated method stub
		return sdao.chatlist();
	}
	
	@Override
	public List<SongDto> all_song(String singer_name, int startList, int listSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("singer_name", singer_name);
		map.put("startList", startList);
		map.put("listSize", listSize);
		return sdao.all_song(map);
	}
	
	@Override
	public int searchSong(String singer_name) {
		// TODO Auto-generated method stub
		return sdao.search_song(singer_name);
	}
	

}
