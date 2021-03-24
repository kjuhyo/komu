package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SingerDao;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SingerlikeDto;
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
	public List<SingerchatDto> chatlist(int singer_id) {
		// TODO Auto-generated method stub
		return sdao.chatlist(singer_id);
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
	
	@Override
	public SingerDto find_singer(String singer_name) {
		// TODO Auto-generated method stub
		return sdao.find_singer(singer_name);
	}
	
	@Override
	public int chat_regist(SingerchatDto chat) {
		// TODO Auto-generated method stub
		return sdao.chat_regist(chat);
	}
	
	@Override
	public SingerchatDto check_id(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		return sdao.chat_id(id);
	}
	
	@Override
	public int do_delete(int id) {
		// TODO Auto-generated method stub
		return sdao.do_delete(id);
	}
	
	@Override
	public int ami_like(String uid, int singer_id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("singer_id", singer_id);
		return sdao.ami_like(map);
	}
	
	@Override
	public int do_like(SingerlikeDto singerlike) {
		// TODO Auto-generated method stub
		return sdao.do_like(singerlike);
	}
	
	@Override
	public int do_dislike(SingerlikeDto singerlike) {
		// TODO Auto-generated method stub
		return sdao.do_dislike(singerlike);
	}
	
	@Override
	public SingerlikeDto find_like(SingerlikeDto singerlike) {
		// TODO Auto-generated method stub
		return sdao.find_like(singerlike);
	}
}
