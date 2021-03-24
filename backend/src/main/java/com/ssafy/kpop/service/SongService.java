package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.SongDto;

public interface SongService {
	
	List<SongDto> newest_list(int startList, int listSize);
	
	

}
