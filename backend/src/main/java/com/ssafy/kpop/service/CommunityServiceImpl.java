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
	
	@Override
	public CommunityDto get_old(int cid) {
		// TODO Auto-generated method stub
		return cdao.get_old(cid);
	}
	@Override
	public int update_post(CommunityDto community) {
		// TODO Auto-generated method stub
		return cdao.update_post(community);
	}
	
	@Override
	public int update(CommunityDto community) {
		// TODO Auto-generated method stub
		return cdao.update(community);
	}
	@Override
	public int delete(int cid) {
		// TODO Auto-generated method stub
		return cdao.delete(cid);
	}
}
