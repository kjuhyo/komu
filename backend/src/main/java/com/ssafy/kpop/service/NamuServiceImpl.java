package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.NamuDao;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.util.Pagination;

@Service
public class NamuServiceImpl implements NamuService{
	
	@Autowired
	NamuDao ndao;
	
	@Override
	public int insert(NamuwikiDto namu) {
		// TODO Auto-generated method stub
		return ndao.insert(namu);
	}
	
	@Override
	public int update(NamuwikiDto namu) {
		// TODO Auto-generated method stub
		return ndao.update(namu);
	}
	
	@Override
	public int delete(NamuwikiDto namu) {
		// TODO Auto-generated method stub
		return ndao.delete(namu);
	}
	
	@Override
	public int checkAuth(int namu_id, String uid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("namu_id", namu_id);
		map.put("uid", uid);
		return ndao.checkAuth(map);
	}
	
	@Override
	public NamuwikiDto call_namu(String namu_title) {
		// TODO Auto-generated method stub
		return ndao.call_namu(namu_title);
	}
	
	@Override
	public int get_total() {
		// TODO Auto-generated method stub
		return ndao.get_total();
	}
	
	@Override
	public List<NamuwikiDto> get_list(Pagination pagination) {
		// TODO Auto-generated method stub
		return ndao.get_list(pagination);
	}
	
	@Override
	public int search_total(String word) {
		// TODO Auto-generated method stub
		return ndao.search_total(word);
	}
	
	@Override
	public List<NamuwikiDto> search_list(String word, int startList, int listSize) {
		// TODO Auto-generated method stub
		Map<String , Object> map = new HashMap<>();
		map.put("word", word);
		map.put("startList", startList);
		map.put("listSize", listSize);
		return ndao.search_list(map);
	}
	
	@Override
	public NamuwikiDto check_namu(String title) {
		// TODO Auto-generated method stub
		return ndao.check_namu(title);
	}

}
