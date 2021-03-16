package com.ssafy.kpop.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.service.NamuService;
import com.ssafy.kpop.util.S3Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("NamuController V1")
@RestController
@RequestMapping(value = "/namu")
public class NamuController {
	public static final Logger logger = LoggerFactory.getLogger(NamuController.class);
	
	@Autowired
	NamuService namuservice;
	
	@Autowired
	private S3Util s3util;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket; // 버킷이름
	
	@ApiOperation(value="Namu Insert", notes="나무위키 단어 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestPart MultipartFile file, 
			@RequestPart NamuwikiDto namu
//			@RequestPart String uid, @RequestPart int song_id, @RequestPart int singer_id, @RequestPart String namu_title, @RequestPart String namu_content
			){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
//		NamuwikiDto namu = new NamuwikiDto();
//		namu.setUid(uid);
//		namu.setSong_id(song_id);
//		namu.setSinger_id(singer_id);
//		namu.setNamu_title(namu_title);
//		namu.setNamu_content(namu_content);
	
		try {	
			
			logger.info("=====> 나무위키 글 등록 시작");
			String originName = file.getOriginalFilename();
			
			String ext = originName.substring(originName.lastIndexOf('.'));
			String saveFileName=UUID.randomUUID().toString()+ext;
			String path = System.getProperty("user.dir");
			
			File tempfile = new File(path,saveFileName);
			
			String line = "namu/";
			
			saveFileName = line+saveFileName;
			
			file.transferTo(tempfile);
			s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, tempfile).withCannedAcl(CannedAccessControlList.PublicRead));
			String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
			tempfile.delete();
			
			namu.setNamu_img(url);
			
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
