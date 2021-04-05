package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.WordlikeDto;
import com.ssafy.kpop.util.Pagination;

@Mapper
public interface NamuDao {
	int insert(NamuwikiDto namu);
	int update(NamuwikiDto namu);
	int delete(NamuwikiDto namu);
	int checkAuth(Map<String , Object> map);
	NamuwikiDto call_namu(String namu_title);
	int get_total();
	List<NamuwikiDto> get_list(Pagination pagination);
	int search_total(String word);
	List<NamuwikiDto> search_list(Map<String , Object> map);
	
	NamuwikiDto check_namu(String title);
	
	int ami_like(Map<String, Object> map);
	WordlikeDto find_like(WordlikeDto wordlike);
	int let_like(WordlikeDto wordlike);
	int let_dislike(WordlikeDto wordlike);
	int insert_nopic(NamuwikiDto namu);
}
