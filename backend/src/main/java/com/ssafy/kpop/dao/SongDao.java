package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;
import com.ssafy.kpop.dto.Song_like_countDto;
import com.ssafy.kpop.dto.SonglikeDto;
import com.ssafy.kpop.dto.SongwordDto;

@Mapper
public interface SongDao {
	
	List<SongListDto> newest_list(Map<String, Object> map);
	
	SongDto get_song(int id);
	
	List<SongwordDto> get_word(int id);
	
	NamuwikiDto search_word(String word);
	
	int regist_word(Map<String, Object> map);
	
	SongwordDto check_word(Map<String, Object> map);
	
	List<SongListDto> default_list(Map<String, Object> map);
	
	SongwordDto search_wordlist(SongwordDto songword);
	int insert_namu(Map<String, Object> map);
	int insert_list(SongwordDto songword);
	
	//좋아요
	SonglikeDto find_like(SonglikeDto songlike);
	int let_like(SonglikeDto songlike);
	int let_dislike(SonglikeDto songlike);
	Song_like_countDto now_count(int song_id);
	int set_like(Map<String, Object> map);
	int get_cnt(int song_id);
	int get_like(Map<String, Object> map);
}
