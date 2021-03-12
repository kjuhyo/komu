package com.ssafy.kpop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.CommunityDto;

@Mapper
public interface MyDao {
	public List<CommunityDto> showMyCommunity(String uid);
}
