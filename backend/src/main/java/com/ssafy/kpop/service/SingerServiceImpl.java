package com.ssafy.kpop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SingerDao;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;

@Service
public class SingerServiceImpl implements SingerService{
	
	@Autowired
	SingerDao sdao;
	
	@Override
	public List<SongDto> songlist(String singer_name) {
		// TODO Auto-generated method stub
		return sdao.songlist(singer_name);
	}
	
	@Override
	public List<SingerchatDto> chatlist() {
		// TODO Auto-generated method stub
		return sdao.chatlist();
	}
	

}
