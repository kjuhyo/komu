package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.util.Pagination;

public interface NamuService {
	
	int insert(NamuwikiDto namu); //단어 등록
	
	int update(NamuwikiDto namu); //단어 수정 
	int delete(NamuwikiDto namu); //단어 삭제
	int checkAuth(int namu_id, String uid);
	NamuwikiDto callnamu(int namu_id);
	int get_total();
	List<NamuwikiDto> get_list(Pagination pagination);
	int search_total(String word);
	List<NamuwikiDto> search_list(String word, int startList, int listSize);

}
