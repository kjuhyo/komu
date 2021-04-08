package com.ssafy.kpop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.CommunityNickDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;

@Mapper
public interface MyDao {
	public List<CommunityNickDto> showMyCommunity(String uid);
	public List<SongDto> showMySinger(String uid);
	public List<NamuwikiDto> showMyword(String uid);
	
}
