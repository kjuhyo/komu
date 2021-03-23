package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;

public interface SingerService {
	List<SongDto> songlist(String singer_name);
	List<SingerchatDto> chatlist();
	
	int searchSong(String singer_name);
	List<SongDto> all_song(String singer_name, int startList, int listSize);

}
