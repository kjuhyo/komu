package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SongDao;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;
import com.ssafy.kpop.dto.SongwordDto;

@Service
public class SongServiceImpl implements SongService {
	
	@Autowired
	SongDao sdao;
	
	@Override
	public List<SongListDto> newest_list(int startList, int listSize) {
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
	
	@Override
	public int regist_word(int id, String word) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("word", word);
		return sdao.regist_word(map);
	}
	
	@Override
	public SongwordDto check_word(int id, String word) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("word", word);
		return sdao.check_word(map);
	}
	
	@Override
	public List<SongListDto> default_list(int startList, int listSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("startList", startList);
		map.put("listSize", listSize);
		return sdao.default_list(map);
	}
	
	@Override
	public SongwordDto search_wordlist(SongwordDto songword) {
		// TODO Auto-generated method stub
		return sdao.search_wordlist(songword);
	}
	
	@Override
	public int insert_namu(String word, String uid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("word", word);
		map.put("uid", uid);
		return sdao.insert_namu(map);
	}
	
	@Override
	public int insert_list(SongwordDto songword) {
		// TODO Auto-generated method stub
		return sdao.insert_list(songword);
	}

}
