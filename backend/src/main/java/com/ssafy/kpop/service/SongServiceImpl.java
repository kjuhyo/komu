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
import com.ssafy.kpop.dto.Song_like_countDto;
import com.ssafy.kpop.dto.SonglikeDto;
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
	
	@Override
	public SonglikeDto find_like(SonglikeDto songlike) {
		// TODO Auto-generated method stub
		return sdao.find_like(songlike);
	}
	
	@Override
	public int let_like(SonglikeDto songlike) {
		// TODO Auto-generated method stub
		return sdao.let_like(songlike);
	}
	
	@Override
	public int let_dislike(SonglikeDto songlike) {
		// TODO Auto-generated method stub
		return sdao.let_dislike(songlike);
	}
	
	@Override
	public Song_like_countDto now_count(int song_id) {
		// TODO Auto-generated method stub
		return sdao.now_count(song_id);
	}
	
	@Override
	public int set_like(int song_id, int song_like_cnt) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("song_id", song_id);
		map.put("song_like_cnt", song_like_cnt);
		return sdao.set_like(map);
	}
	
	@Override
	public int get_cnt(int song_id) {
		// TODO Auto-generated method stub
		return sdao.get_cnt(song_id);
	}
	
	@Override
	public int get_like(String uid, int song_id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("song_id", song_id);
		return sdao.get_like(map);
	}

}
