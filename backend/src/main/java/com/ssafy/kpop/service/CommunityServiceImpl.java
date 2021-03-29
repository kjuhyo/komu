package com.ssafy.kpop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.CommunityDao;

@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	CommunityDao cdao;

}
