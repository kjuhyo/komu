package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerDto;

public interface MyService {
	public List<CommunityDto> showMyCommunity(String uid); 
	public List<SingerDto> showMySinger(String uid);
	public List<NamuwikiDto> showMyword(String uid);
}
