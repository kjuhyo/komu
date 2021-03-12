package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.CommunityDto;

public interface MyService {
	public List<CommunityDto> showMyCommunity(String uid); 
}
