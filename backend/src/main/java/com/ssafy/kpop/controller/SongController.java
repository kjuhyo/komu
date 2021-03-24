package com.ssafy.kpop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.service.SongService;
import com.ssafy.kpop.util.Pagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("SongController V1")
@RestController
@RequestMapping(value = "/song")
public class SongController {
	private static final Logger logger = LoggerFactory.getLogger(SingerController.class);

	@Autowired
	SongService songservice;

	@ApiOperation(value = "Song (newest) List Page", notes = "노래 리스트 페이지 (최신순정렬)")
	@GetMapping("/list/{page}")
	public ResponseEntity<Map<String, Object>> getlist_new(@PathVariable int page) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		int range = (page / 10) + 1;
		int listCnt = 0;

		try {
			logger.info("=====> 최신순 노래 리스트 가져오기");
			Pagination pagi = new Pagination();
			pagi.pageInfo(page, range, listCnt);
			
			int startList = pagi.getStartList();
			int listSize = pagi.getListSize();
			List<SongDto> songList= songservice.newest_list(startList, listSize);
			
			resultMap.put("songList", songList);
			resultMap.put("message", "최신순 노래 가져오기 성공하였습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
