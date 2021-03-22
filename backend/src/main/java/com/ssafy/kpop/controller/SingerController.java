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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.service.SingerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("SingerController V1")
@RestController
@RequestMapping(value = "/singer")
public class SingerController {
	public static final Logger logger = LoggerFactory.getLogger(SingerController.class);

	@Autowired
	SingerService singerservice;

	// 게시물 확인하기
	@ApiOperation(value = "Singer Page", notes = "가수 페이지")
	@GetMapping("/{singer_name}")
	public ResponseEntity<Map<String, Object>> getboard(@PathVariable String singer_name) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 해당 가수 정보가져오기");
			// 노래 5개 리스트가져오고 최신순으로 가져와
			List<SongDto> songlist = singerservice.songlist(singer_name);

			// 댓글리스트 가져와
			List<SingerchatDto> chatlist = singerservice.chatlist();

			resultMap.put("songlist", songlist);
			resultMap.put("chatlist", chatlist);
			resultMap.put("message", "노래, 댓글 가져오기 성공하였습니다.");
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
