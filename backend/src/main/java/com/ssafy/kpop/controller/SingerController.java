package com.ssafy.kpop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SingerlikeDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.service.SingerService;
import com.ssafy.kpop.util.Pagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("SingerController V1")
@RestController
@RequestMapping(value = "/singer")
public class SingerController {
	public static final Logger logger = LoggerFactory.getLogger(SingerController.class);

	@Autowired
	SingerService singerservice;

	// 가수 개인별페이지
	@ApiOperation(value = "Singer Main Page", notes = "가수 메인 페이지")
	@GetMapping("/{singer_name}")
	public ResponseEntity<Map<String, Object>> getboard(@PathVariable String singer_name, @RequestParam String uid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 해당 가수 정보가져오기");
			// 가수 식별번호 찾아오기
			SingerDto singer = singerservice.find_singer(singer_name);
			int singer_id = singer.getSinger_id();
//			int singer_id = singerservice.find_singer(singer_name);

			// 노래 5개 리스트가져오고 최신순으로 가져와
			List<SongDto> songList = singerservice.songlist(singer_name);

			// 댓글리스트 가져와
			List<SingerchatDto> chatList = singerservice.chatlist(singer_id);
			

			// 좋아하는 가수인지 확인합시다
			int like = singerservice.ami_like(uid, singer_id);
			
			resultMap.put("singerInfo", singer);
			resultMap.put("like", like);
			resultMap.put("songList", songList);
			resultMap.put("chatList", chatList);
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

	@ApiOperation(value = "Singer Like Url", notes = "가수좋아요 url")
	@PostMapping("/like")
	public ResponseEntity<Map<String, Object>> do_like(@RequestBody SingerlikeDto singerlike) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		logger.info("=====> 가수 좋아요 등록");

		try {
			logger.info("=====> 좋아요 중복 체크 ");
			SingerlikeDto like = singerservice.find_like(singerlike);
			if(like==null) {
				int result = singerservice.do_like(singerlike);
				if (result == 1) {
					resultMap.put("like", result);
					resultMap.put("message", "가수를 좋아요하셨습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "가수를 좋아요에 실패하셨습니다.");
					status = HttpStatus.ACCEPTED;
				}				
			}else {
				resultMap.put("message", "이미 좋아요를 누르셨습니다.");
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
	
	@ApiOperation(value="Singer DisLike Url", notes="가수좋아요 취소 url")
	@DeleteMapping("/dislike")
	public ResponseEntity<Map<String, Object>> do_dislike(@RequestBody SingerlikeDto singerlike) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		logger.info("=====> 가수 싫어요 시작");
		
		try {
			logger.info("=====> 싫어요 취소 중복 체크 ");
			SingerlikeDto like = singerservice.find_like(singerlike);
			if(like!=null) {
				int result = singerservice.do_dislike(singerlike);
				
				if(result == 1) {
					logger.info("=====> 삭제 성공");
					resultMap.put("like", 0);
					resultMap.put("message", "가수 좋아요를 취소하셨습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "가수 좋아요 취소를 실패하였습니다.");
					status = HttpStatus.ACCEPTED;
				}				
			}else {
				resultMap.put("message", "이미 좋아요를 취소하셨습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 가수페이지에서 노래더보기 눌렀을 떄 노래 전체 리스트
	@ApiOperation(value = "Singer's song Page", notes = "가수 노래 페이지")
	@GetMapping("/{singer_name}/song/{page}")
	public ResponseEntity<Map<String, Object>> getsong(@PathVariable String singer_name, @PathVariable int page) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int range = (page / 10) + 1;
		int listCnt = 0;

		try {

			listCnt = singerservice.searchSong(singer_name);
			logger.info("=====> 해당 가수 전체 노래 정보가져오기");
			Pagination pagination = new Pagination();
			pagination.pageInfo(page, range, listCnt);

			int startList = pagination.getStartList();
			int listSize = pagination.getListSize();
			List<SongDto> songList = singerservice.all_song(singer_name, startList, listSize);

			resultMap.put("pagination", pagination);
			resultMap.put("songList", songList);
			resultMap.put("message", "노래 페이지별 가져오기 성공하였습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 가수페이지의 댓글 등록
	@ApiOperation(value = "Chat regist Page", notes = "가수에 대한 댓글 등록 페이지")
	@PostMapping("/{singer_name}/regist")
	public ResponseEntity<Map<String, Object>> registComm(@PathVariable String singer_name,
			@RequestBody SingerchatDto singerchat) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		logger.info("=====> 가수 댓글 등록 시작");

		try {
			// 가수 식별번호 찾아오기
			SingerDto singer = singerservice.find_singer(singer_name);
			int singer_id = singer.getSinger_id();
			SingerchatDto chat = singerchat;
			chat.setSinger_id(singer_id);

			int result = singerservice.chat_regist(chat);
			if (result >= 1) {
				resultMap.put("message", "코멘트 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;

			} else {
				resultMap.put("message", "코멘트 등록에 실패하였습니다.");
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

	// 가수페이지의 댓글 신고
	@ApiOperation(value = "Chat Report Page", notes = "댓글 신고 페이지")
	@PostMapping("/{singer_name}/report")
	public ResponseEntity<Map<String, Object>> registComm(@PathVariable String singer_name) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			resultMap.put("message", "댓글이 신고되었습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", "댓글 신고에 실패하였습니다.");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Chat Delete", notes = "가수 페이지 댓글 삭제")
	@PutMapping("/chat/delete")
	public ResponseEntity<Map<String, Object>> modify(@RequestParam String userid, @RequestParam int singerchat_id) {
		// @RequestBody SingerchatDto chat
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 가수 댓글 삭제 시작");
			// 1. singerchat_id를 통해 dto를 가져와서 writer랑 uid를 비교해줄꺼니?

			SingerchatDto result = singerservice.check_id(singerchat_id);

			if (result.getUid().equals(userid)) {
				int delete = singerservice.do_delete(singerchat_id);
				resultMap.put("message", "글 삭제가 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 삭제가 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("댓글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
