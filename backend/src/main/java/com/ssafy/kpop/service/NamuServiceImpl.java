package com.ssafy.kpop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.NamuDao;
import com.ssafy.kpop.dto.NamuwikiDto;

@Service
public class NamuServiceImpl implements NamuService{
	
	@Autowired
	NamuDao ndao;
	@Override
	public int insert(NamuwikiDto namu) {
		// TODO Auto-generated method stub
		return ndao.insert(namu);
	}

}
