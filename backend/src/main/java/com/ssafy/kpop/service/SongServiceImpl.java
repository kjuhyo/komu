package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.SongDao;
import com.ssafy.kpop.dto.SongDto;

@Service
public class SongServiceImpl implements SongService {
	
	@Autowired
	SongDao sdao;
	
	@Override
	public List<SongDto> newest_list(int startList, int listSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("startList", startList);
		map.put("listSize", listSize);
		return sdao.newest_list(map);
	}
	

}
