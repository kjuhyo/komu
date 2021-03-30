package com.ssafy.kpop.service;

import com.ssafy.kpop.dto.CommunityDto;

public interface CommunityService {
	
	int insert_post(CommunityDto community);
	CommunityDto get_old(int cid);
	int update_post(CommunityDto community);
	int update(CommunityDto community);
	int delete(int cid);

}
