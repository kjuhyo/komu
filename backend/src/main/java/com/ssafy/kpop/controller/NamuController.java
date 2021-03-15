package com.ssafy.kpop.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.service.NamuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("NamuController V1")
@RestController
@RequestMapping(value = "/namu")
public class NamuController {
	public static final Logger logger = LoggerFactory.getLogger(NamuController.class);
	
	@Autowired
	NamuService namuservice;
	
	@ApiOperation(value="Namu Insert", notes="나무위키 단어 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody NamuwikiDto namu){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {	
			logger.info("=====> 나무위키 글 등록 시작");
			int result = namuservice.insert(namu);
			
			if(result==1) {
				logger.info("=====> 나무위키 글 등록 성공");
				resultMap.put("message", "단어 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("=====> 나무위키 글 등록 실패");
				resultMap.put("message", "단어 등록에 실패하였습니다.");
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status); 
	}



}
