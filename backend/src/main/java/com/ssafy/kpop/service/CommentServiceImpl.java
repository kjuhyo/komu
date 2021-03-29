package com.ssafy.kpop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.CommentDao;
import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_comment_likeDto;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao cdao;
	
	@Override
	public int chat_regist(Comm_commentDto comment) {
		// TODO Auto-generated method stub
		return cdao.chat_regist(comment);
	}
	
	@Override
	public Comm_commentDto check_id(Comm_commentDto comment) {
		// TODO Auto-generated method stub
		return cdao.check_id(comment);
	}
	
	@Override
	public int do_delete(Comm_commentDto comment) {
		// TODO Auto-generated method stub
		return cdao.do_delete(comment);
	}
	
	@Override
	public List<Comm_commentDto> get_list(int cid) {
		// TODO Auto-generated method stub
		return cdao.get_list(cid);
	}

}
