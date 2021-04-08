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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_comment_likeDto;
import com.ssafy.kpop.dto.Comm_comment_nickDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CommentController V1")
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommentService commentservice;

	// 커뮤니티댓글
	@ApiOperation(value = "Chat regist for Community Page", notes = "커뮤니티에 대한 댓글 등록 페이지")
	@PostMapping("/community/regist")
	public ResponseEntity<Map<String, Object>> registComm(@RequestBody Comm_commentDto comment) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		logger.info("=====> 커뮤니티 댓글 등록 시작");
		System.out.println(comment);

		try {
			// 댓글등록 시작하기
			System.out.println(comment);
			int result = commentservice.chat_regist(comment);

			if (result >= 1) {
				resultMap.put("message", "댓글 등록에 성공하였습니다.");
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

	// 커뮤니티페이지 댓글 신고
	@ApiOperation(value = "Chat Report Page", notes = "댓글 신고 페이지")
	@PostMapping("/community/report")
	public ResponseEntity<Map<String, Object>> reportComm(@RequestBody Comm_commentDto comment) {
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
	public ResponseEntity<Map<String, Object>> deleteComm(@RequestBody Comm_commentDto comm) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 가수 댓글 삭제 시작");
			// 1. singerchat_id를 통해 dto를 가져와서 writer랑 uid를 비교해줄꺼니?
			Comm_commentDto check_comm = commentservice.check_id(comm);

			if (check_comm.getUid().equals(comm.getUid())) {
				int delete = commentservice.do_delete(comm);
				if (delete == 1) {
					resultMap.put("message", "댓글을 성공적으로 삭제하였습니다.");
					status = HttpStatus.ACCEPTED;
				}
			} else {
				resultMap.put("message", "댓글 삭제에 실패하였습니다.");
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
	
	@ApiOperation(value = "Chat List Page for community", notes = "커뮤니티 등록 댓글 가져오기")
	@GetMapping("/{cid}/chatlist")
	public ResponseEntity<Map<String, Object>> getlistforcommunity(@PathVariable int cid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 해당 가수 정보가져오기");
			// 가수 식별번호 찾아오기
			List<Comm_comment_nickDto> list = commentservice.commentList(cid);
			
			System.out.println(list);

			resultMap.put("ChatList", list);
			resultMap.put("message", "커뮤니티 댓글 가져오기 성공하였습니다.");
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
