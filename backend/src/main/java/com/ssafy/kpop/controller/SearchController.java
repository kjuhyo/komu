package com.ssafy.kpop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.SingerDto;
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
	@GetMapping("/{name}")
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
	
}
