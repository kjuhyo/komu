package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.CommunityDao;
import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_likeDto;
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
	@Override
	public CommunityDto get_community(int cid) {
		// TODO Auto-generated method stub
		return cdao.get_community(cid);
	}
	
	@Override
	public int up_view(int cid, int now_view) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("cid", cid);
		map.put("now_view", now_view);
		return cdao.up_view(map);
	}
	
	@Override
	public List<Comm_commentDto> get_comment(int cid) {
		// TODO Auto-generated method stub
		return cdao.get_comment(cid);
	}
	
	@Override
	public Comm_likeDto find_like(Comm_likeDto commlike) {
		// TODO Auto-generated method stub
		return cdao.find_like(commlike);
	}
	
	@Override
	public int let_like(Comm_likeDto commlike) {
		// TODO Auto-generated method stub
		return cdao.let_like(commlike);
	}
	
	@Override
	public int let_dislike(Comm_likeDto commlike) {
		// TODO Auto-generated method stub
		return cdao.let_dislike(commlike);
	}
	
	@Override
	public int cnt_like(int cid) {
		// TODO Auto-generated method stub
		return cdao.cnt_like(cid);
	}
	
	@Override
	public int set_like(int cid, int c_like_cnt) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("cid", cid);
		map.put("c_like_cnt", c_like_cnt);
		return cdao.set_like(map);
	}
	
}
