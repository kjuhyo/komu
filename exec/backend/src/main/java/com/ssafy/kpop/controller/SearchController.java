package com.ssafy.kpop.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.ComPostDto;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;
import com.ssafy.kpop.service.SearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("SearchController V1")
@RestController
@RequestMapping("/search")
public class SearchController {
	/*노래검색(노래제목+가수), 커뮤니티 검색 (해시태그+제목), 가수검색(가수명)*/
	
	@Autowired
	private SearchService searchService;
	
	
	/*
	 * 가수검색결과 ( 좋아요 순 ) 
	 * 
	 * @param 가수명
	 * @return List<SingerDto>
	 * */
	@ApiOperation(value="가수검색", notes = "@param 가수명 </br> @return SingerDto", response=List.class)
	@GetMapping("/singer/{name}")
	public ResponseEntity<List<SingerDto>> getSingerName(@PathVariable String name){
		HttpStatus status=HttpStatus.ACCEPTED;
		List<SingerDto> list=null;
		try {
			name= "%"+name+"%";
			list=searchService.getSingerByName(name);
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<SingerDto>>(list, status);
	}
	
	/*
	 * 노래검색결과 ( 인기순 ) - 노래제목+가수명
	 * 
	 * @param 노래제목
	 * @return List<SongDto>
	 * */
	@ApiOperation(value="노래검색(노래제목+가수명),인기순", notes = "제목이나 가수명으로 노래 검색,인기순", response=List.class)
	@GetMapping("/song/{name}")
	public ResponseEntity<List<SongListDto>> getPopularSongName(@PathVariable String name){
		HttpStatus status=HttpStatus.ACCEPTED;
		List<SongListDto> list=null;
		try {
			name= "%"+name+"%";
			list=searchService.getSongLikeSort(name);
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<SongListDto>>(list, status);
	}
	
	/*
	 * 노래검색결과 ( 최신순 ) - 노래제목+가수명
	 * 
	 * @param 가수명
	 * @return List<SongDto>
	 * */
	@ApiOperation(value="노래검색(노래제목+가수명),최신순", notes = "제목이나 가수명으로 노래 검색,인기순", response=List.class)
	@GetMapping("/song/new/{name}")
	public ResponseEntity<List<SongListDto>> getNewSongName(@PathVariable String name){
		HttpStatus status=HttpStatus.ACCEPTED;
		List<SongListDto> list=null;
		try {
			name= "%"+name+"%";
			list=searchService.getSongNewSort(name);
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<SongListDto>>(list, status);
	}
	
	/**
	 * 해쉬태그  검색 - 조회순
	 * 
	 * @param List<Integer>
	 * @return List<BlogPostDto>
	 */
	@ApiOperation(value = "해쉬태그  검색 - 조회순", notes = "@param List<Integer> </br> @return ComPostDto")
	@PostMapping("view")
	public ResponseEntity<List<ComPostDto>> orderByView(@RequestBody List<Integer> keywords) throws Exception{
		try {
			List<ComPostDto> list = searchService.searchByHash(keywords);
			Collections.sort(list, new Comparator<ComPostDto>() {
				@Override
				public int compare(ComPostDto o1, ComPostDto o2) {
					return o2.getC_view() - o1.getC_view();
				}
			});
			return new ResponseEntity<List<ComPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * 커뮤니티 검색결과 (조회순) - 제목으로 
	 * 
	 * @param 제목
	 * @return List<CommunityDto>
	 * */
	@ApiOperation(value="커뮤니티검색(제목으로)", notes = "@param 제목 </br> @return CommunityDto", response=List.class)
	@GetMapping("/community/{c_title}")
	public ResponseEntity<List<CommunityDto>> getCommunityContents(@PathVariable String c_title){
		HttpStatus status=HttpStatus.ACCEPTED;
		List<CommunityDto> list=null;
		try {
			c_title= "%"+c_title+"%";
			list=searchService.searchCommunity(c_title);
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<CommunityDto>>(list, status);
	}
}
