package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;
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
	
	

}
