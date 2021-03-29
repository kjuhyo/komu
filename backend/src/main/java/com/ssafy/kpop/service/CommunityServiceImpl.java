package com.ssafy.kpop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.CommunityDao;
import com.ssafy.kpop.dto.CommunityDto;

@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	CommunityDao cdao;

	@Override
	public int insert_post(CommunityDto community) {
		// TODO Auto-generated method stub
		return cdao.insert_post(community);
	}
}
