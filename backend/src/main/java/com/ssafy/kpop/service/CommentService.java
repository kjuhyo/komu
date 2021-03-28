package com.ssafy.kpop.service;

import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_comment_likeDto;

public interface CommentService {
	
	int chat_regist(Comm_commentDto comment);
	Comm_commentDto check_id(Comm_commentDto comment);
	int do_delete(Comm_commentDto comment);

}
