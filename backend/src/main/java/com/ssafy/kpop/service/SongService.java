package com.ssafy.kpop.service;

import java.util.List;
import java.util.Map;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;
import com.ssafy.kpop.dto.Song_like_countDto;
import com.ssafy.kpop.dto.SonglikeDto;
import com.ssafy.kpop.dto.SongwordDto;

public interface SongService {
	
	List<SongListDto> newest_list(int startList, int listSize);
	
	SongDto get_song(int id);
	
	List<SongwordDto> get_word(int id);
	
	NamuwikiDto search_word(String word);
	
	int regist_word(int id, String word);
	
	SongwordDto check_word(int id, String word);
	
	List<SongListDto> default_list(int startList, int listSize);
	SongwordDto search_wordlist(SongwordDto songword);
	int insert_namu(String word, String uid);
	int insert_list(SongwordDto songword);
	
	//좋아요
	SonglikeDto find_like(SonglikeDto songlike);
	int let_like(SonglikeDto songlike);
	int let_dislike(SonglikeDto songlike);
	
	Song_like_countDto now_count(int song_id);
	
	int set_like(int song_id, int song_like_cnt);
	
	int get_cnt(int song_id);
	
	int get_like(String uid, int song_id);
	
	int get_listCnt(String genre);
	
	List<SongListDto> genre_list(String genre, int startList, int listSize);

	int get_totalcnt();

	List<SongListDto> popularGenre(String genre, int startList, int listSize);
	
	

}
