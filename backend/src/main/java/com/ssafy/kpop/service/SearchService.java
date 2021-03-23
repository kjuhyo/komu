package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.SingerDto;

public interface SearchService {
	public List<SingerDto> getSingerByName(String singer_name);
}
