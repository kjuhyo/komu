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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.service.CommunityService;
import com.ssafy.kpop.util.S3Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CommunityController V1")
@RestController
@RequestMapping(value = "/community")
public class CommunityController {

	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommunityService cservice;

	@Autowired
	private S3Util s3util;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	// 게시물 등록하기
	@ApiOperation(value = "Community Post Insert", notes = "커뮤니티 글 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestPart MultipartFile file,
			@RequestPart CommunityDto community) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 커뮤니티 글 등록 시작");
			String originName = file.getOriginalFilename(); // 파일 이름 가져오기

			String ext = originName.substring(originName.lastIndexOf('.')); // 파일 확장명 가져오기
			String saveFileName = UUID.randomUUID().toString() + ext; // 암호화해서 파일확장넣어주기
			String path = System.getProperty("user.dir"); // 경로설정해주고

			File tempfile = new File(path, saveFileName); // 경로에 파일만들어주고

			String line = "community/";

			saveFileName = line + saveFileName;

			file.transferTo(tempfile);
			s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, tempfile)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
			tempfile.delete();

			community.setC_img(url);

			int result = cservice.insert_post(community);

			if (result == 1) {
				logger.info("=====> 커뮤니티 글 등록 성공");
				resultMap.put("message", "게시글 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("=====> 커뮤니티 글 등록 실패");
				resultMap.put("message", "게시글 등록에 실패하였습니다.");
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

	// 게시물 수정하기
	@ApiOperation(value = "Community Post Update", notes = "커뮤니티 글 수정")
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> modify(@RequestPart MultipartFile file,
			@RequestPart CommunityDto community) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		CommunityDto old = cservice.get_old(community.getCid());
		try {
			logger.info("=====> 사진, 내용 수정");
			if (file != null) { // 사진 수정할 파일 있을때
				// 파일 가져와서 삭제하고 db에 있는거 삭제
				String photo = old.getC_img();
				if (photo != null) { // 이미 사진이 저장되어있을때
					s3util.setS3Client().deleteObject(new DeleteObjectRequest(bucket, photo));
				}
				// 그전에 등록한 사진이 없으면 바로 등록하면 됩니다!
				// 자 사진 삭제했으니 다시 사진을 등록해 봅시다! 렛츠꼬우!
				String originName = file.getOriginalFilename(); // 파일 이름 가져오기

				String ext = originName.substring(originName.lastIndexOf('.')); // 파일 확장명 가져오기
				String saveFileName = UUID.randomUUID().toString() + ext; // 암호화해서 파일확장넣어주기
				String path = System.getProperty("user.dir"); // 경로설정해주고

				File tempfile = new File(path, saveFileName); // 경로에 파일만들어주고

				String line = "community/";

				saveFileName = line + saveFileName;

				file.transferTo(tempfile);
				s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, tempfile)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
				tempfile.delete();

				community.setC_img(url); // 사진 등록했꼬

				logger.info("=====> 사진 등록완료! 글 등록 해야합니다!");
				int result = cservice.update_post(community);
				if (result == 1) {
					System.out.println("=====> 수정 성공");
					resultMap.put("message", "글 수정에 성공하였습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "글 수정에 실패하였습니다.");
					status = HttpStatus.ACCEPTED;
				}
				// 아마 키앞에 photo/ 붙여야할꺼야

			} else { // 사진 수정할 필요가 없을 때 글만 수정하면돼 ! 렛츠꼬!
				int content_update = cservice.update(community);
				if (content_update == 1) {
					System.out.println("=====> 수정 성공");
					resultMap.put("message", "글 수정에 성공하였습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "글 수정에 실패하였습니다.");
					status = HttpStatus.ACCEPTED;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 게시물 삭제하기
	@ApiOperation(value = "Community Post Delete", notes = "커뮤니티 글 삭제")
	@PostMapping("/delete/{cid}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable int cid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 커뮤니티 글 삭제");
			int result = cservice.delete(cid);

			if (result == 1) {
				logger.info("=====> 삭제 성공");
				resultMap.put("message", "글 삭제에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 삭제에 실패하였습니다.");
				status = HttpStatus.NOT_FOUND;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
