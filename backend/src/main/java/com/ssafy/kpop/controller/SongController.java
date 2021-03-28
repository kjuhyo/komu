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
import com.ssafy.kpop.dto.Song_like_countDto;
import com.ssafy.kpop.dto.SonglikeDto;
import com.ssafy.kpop.dto.SongwordDto;
import com.ssafy.kpop.dto.WordlikeDto;
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

	@ApiOperation(value = "Word Insert Page", notes = "단어 등록 페이지")
	@PostMapping("/insert/word")
	public ResponseEntity<Map<String, Object>> search_word(@RequestBody SongwordDto sw, @RequestParam String uid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 노래속 단어리스트 속 단어 중복 확인");
			SongwordDto songword = songservice.search_wordlist(sw);
			if (songword == null) {
				// 노래 리스트에 단어가 등록되지않았으니까 ! 등록하쟈!!
				String word = sw.getNamu_title();
				logger.info("=====> 단어 정보 가져오기");
				NamuwikiDto namu = songservice.search_word(word);
				if (namu != null && namu.getNamu_title().equals(word)) { //
					resultMap.put("namu", namu);
					resultMap.put("message", "나무 위키에 해당 단어가 존재합니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					// 나무위키에등록되지않은 단어니까 등록하쟈!
					int result = songservice.insert_namu(word, uid);
					NamuwikiDto temp = songservice.search_word(word);
					resultMap.put("namu", temp);
					resultMap.put("message", "단어를 등록하였습니다.");
					status = HttpStatus.ACCEPTED;
				}
			} else { // 노래리스트에도 없으니까 노래리스트에 단어를 등록하쟈!
				int insert = songservice.insert_list(sw);
				resultMap.put("message", "단어장에 존재하는 단어입니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Song Like Url", notes = "노래 좋아요/취소  url")
	@PostMapping("/like")
	public ResponseEntity<Map<String, Object>> do_like(@RequestBody SonglikeDto songlike) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		logger.info("=====> 노래 좋아요 체크");

		try {
			logger.info("=====> 좋아요 중복 체크 ");
			SonglikeDto like = songservice.find_like(songlike); // 좋아요눌렀는지 확인

			if (like == null) { // 좋아요 누른적이 없네요? 누르러 갑시다
				int result = songservice.let_like(songlike); // insert
				logger.info("=====> 노래 좋아요 성공");
				if (result == 1) {
					int song_id = songlike.getSong_id();
					Song_like_countDto songcount = songservice.now_count(song_id);
					int ress = songcount.getSong_like_cnt() + 1;
					int cnt_result = songservice.set_like(song_id, ress); // +1해줘
					if (cnt_result == 1) {
						logger.info("=====> 노래 좋아요 카운트 성공");
						resultMap.put("LIKE", result);
						resultMap.put("message", "노래 좋아요를 누르셨습니다.");
					} else {
						resultMap.put("message", "노래 좋아요에 실패하였습니다.");
					}
					status = HttpStatus.ACCEPTED;
				}
			} else { // like안에 좋아요 값이 있는 거야 이미 좋아요를 누른거지
				logger.info("=====> 이미 좋아요를 누르셨습니다. 그러니 삭제를 해봅시다");
				// 삭제버튼 구현시켜야죠
				int result = songservice.let_dislike(songlike);
				logger.info("=====> 노래 좋아요 취소");
				if (result == 1) {
					int song_id = songlike.getSong_id();
					Song_like_countDto song_count = songservice.now_count(song_id);
					int songres = song_count.getSong_like_cnt() - 1;
					int dis_result = songservice.set_like(song_id, songres); // -1해줫어
					if (dis_result == 1) {
						logger.info("=====> 노래 좋아요 취소 카운트 성공");
						resultMap.put("LIKE", 0);
						resultMap.put("message", "단어를 좋아요를 취소하셨습니다.");
					} else {
						resultMap.put("message", "단어를 좋아요를 취소에 실패하셨습니다.");
					}
					status = HttpStatus.ACCEPTED;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
