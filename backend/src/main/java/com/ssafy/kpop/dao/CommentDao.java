package com.ssafy.kpop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.Comm_commentDto;

@Mapper
public interface CommentDao {
	
	int chat_regist(Comm_commentDto comment);
	Comm_commentDto check_id(Comm_commentDto comment);
	int do_delete(Comm_commentDto comment);
	
	List<Comm_commentDto> get_list(int cid);

}
