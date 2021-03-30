package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_likeDto;
import com.ssafy.kpop.dto.CommunityDto;

public interface CommunityService {
	
	int insert_post(CommunityDto community);
	CommunityDto get_old(int cid);
	int update_post(CommunityDto community);
	int update(CommunityDto community);
	int delete(int cid);
	
	CommunityDto get_community(int cid);
	
	int up_view(int cid, int now_view);
	List<Comm_commentDto> get_comment(int cid);
	Comm_likeDto find_like(Comm_likeDto commlike);
	int let_like(Comm_likeDto commlike);
	int let_dislike(Comm_likeDto commlike);
	int cnt_like(int cid);
	int set_like(int cid, int c_like_cnt);
}
