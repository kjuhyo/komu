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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.dto.SongListDto;
import com.ssafy.kpop.dto.SongwordDto;
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
	@GetMapping("/newestlist/{page}")
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
			List<SongListDto> songList = songservice.newest_list(startList, listSize);

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

	// 좋아요 작성하고 만져야햅
	@ApiOperation(value = "Song (popular) List Page", notes = "노래 리스트 페이지 (인기순정렬)")
	@GetMapping("/default/{page}")
	public ResponseEntity<Map<String, Object>> getlist_pop(@PathVariable int page) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int range = (page / 10) + 1;
		int listCnt = 0;

		try {
			logger.info("=====> 인기순 노래 리스트 가져오기");
			Pagination pagi = new Pagination();
			pagi.pageInfo(page, range, listCnt);

			int startList = pagi.getStartList();
			int listSize = pagi.getListSize();
			
			List<SongListDto> songList = songservice.default_list(startList, listSize);

			resultMap.put("songList", songList);
			resultMap.put("message", "인기순 노래 가져오기 성공하였습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Song Page", notes = "가사 페이지")
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> get_song(@PathVariable int id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 노래 정보 가져오기");
			SongDto song = songservice.get_song(id);
			List<SongwordDto> wordList = songservice.get_word(id);

			resultMap.put("song", song);
			resultMap.put("wordList", wordList);

			resultMap.put("message", "노래, 단어 정보를 가져오기를 성공하였습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Word Search Page", notes = "단어 검색 페이지")
	@GetMapping("/search/{word}")
	public ResponseEntity<Map<String, Object>> search_word(@PathVariable String word) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 단어 정보 가져오기");
			NamuwikiDto namu = songservice.search_word(word);

			boolean check = false;
			if (namu != null && namu.getNamu_title().equals(word)) {
				check = true;
				resultMap.put("namu", namu);
				resultMap.put("message", "나무 위키에 해당 단어가 존재합니다.");
			} else {
				resultMap.put("message", "나무 위키에 해당 단어가 존재하지않습니다.");
			}
			resultMap.put("check", check);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	//단어를 등록하면 namu_title이지만 나무위키에 들어가있어야지 그럼 수정하기로 들어가겠지?
	@ApiOperation(value = "Songword Regist Page", notes = "노래 단어 등록 페이지")
	@PostMapping("/{id}/regist")
	public ResponseEntity<Map<String, Object>> registComm(@PathVariable int id, @RequestParam String word) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		boolean check = false;

		try {
			logger.info("=====> 노래 단어 중복 확인");
			SongwordDto zero = songservice.check_word(id, word);
			if(zero !=null) {
				resultMap.put("message", "단어 중복은 등록이 불가합니다");
			}else {
				logger.info("=====> 노래 단어 등록 시작");
				
				int result = songservice.regist_word(id, word);
				if (result >= 1) {
					check = true;
					resultMap.put("message", "단어 등록에 성공하였습니다.");
				} else {
					resultMap.put("message", "단어 등록에 실패하였습니다.");
				}
			}
			resultMap.put("check", check);				
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
