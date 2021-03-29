package com.ssafy.kpop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.CommunityDto;

@Mapper
public interface CommunityDao {
	
	int insert_post(CommunityDto community);
}
