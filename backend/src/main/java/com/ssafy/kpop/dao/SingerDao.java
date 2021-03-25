package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;

@Mapper
public interface SingerDao {
	
	List<SongDto> songlist(String singer_name);
	List<SingerchatDto> chatlist(int singer_id);
	List<SongDto> all_song(Map<String, Object> map);
	int search_song(String singer_name);
	
	SingerDto find_singer(String singer_name);
	int chat_regist(SingerchatDto chat);
	int chat_writer(Map<String, Object> map);
	SingerchatDto chat_id(int singerchat_id);
	int do_delete(int singerchat_id);
	

}
