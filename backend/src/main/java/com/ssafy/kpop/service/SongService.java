package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongwordDto;

public interface SongService {
	
	List<SongDto> newest_list(int startList, int listSize);
	
	SongDto get_song(int id);
	
	List<SongwordDto> get_word(int id);
	
	NamuwikiDto search_word(String word);
	
	

}
