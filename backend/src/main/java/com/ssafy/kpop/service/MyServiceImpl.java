package com.ssafy.kpop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.MyDao;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;

@Service
public class MyServiceImpl implements MyService{

	@Autowired
	private MyDao myDao;
	
	@Override
	public List<CommunityDto> showMyCommunity(String uid) {
		return myDao.showMyCommunity(uid);
	}

	@Override
	public List<SongDto> showMySinger(String uid) {
		return myDao.showMySinger(uid);
	}

	@Override
	public List<NamuwikiDto> showMyword(String uid) {
		return myDao.showMyword(uid);
	}

}
