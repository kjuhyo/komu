package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.WordlikeDto;
import com.ssafy.kpop.util.Pagination;

public interface NamuService {
	
	int insert(NamuwikiDto namu); //단어 등록
	
	int update(NamuwikiDto namu); //단어 수정 
	int delete(NamuwikiDto namu); //단어 삭제
	int checkAuth(int namu_id, String uid);
	NamuwikiDto call_namu(String namu_title);
	int get_total();
	List<NamuwikiDto> get_list(Pagination pagination);
	int search_total(String word);
	List<NamuwikiDto> search_list(String word, int startList, int listSize);
	
	NamuwikiDto check_namu(String title); //단어 중복
	int ami_like(String uid, int namu_id);
	WordlikeDto find_like(WordlikeDto wordlike);
	int let_like(WordlikeDto wordlike);
	int let_dislike(WordlikeDto wordlike);
	
	int insert_nopic(NamuwikiDto namu); //사진없이 단어 등록 

}
