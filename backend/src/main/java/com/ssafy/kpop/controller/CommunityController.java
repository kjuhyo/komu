package com.ssafy.kpop.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_likeDto;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.WordlikeDto;
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
	public ResponseEntity<Map<String, Object>> insert_post(@RequestPart MultipartFile file,
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
	public ResponseEntity<Map<String, Object>> modify_post(@RequestPart MultipartFile file,
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
	public ResponseEntity<Map<String, Object>> delete_post(@PathVariable int cid) {
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

	// 커뮤니티 글 신고
	@ApiOperation(value = "Community Post Report", notes = "커뮤니티 글 신고")
	@PostMapping("/report/{cid}")
	public ResponseEntity<Map<String, Object>> report_post(@PathVariable int cid) {
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

	// 게시물 확인하기
	@ApiOperation(value = "Community Post Show", notes = "커뮤니티 글 보기")
	@GetMapping("/{cid}")
	public ResponseEntity<Map<String, Object>> get_post(@PathVariable int cid, @RequestParam String uid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		boolean check = false;

		try {
			logger.info("=====> 조회수 하나 올려주자");
			CommunityDto now_com = cservice.get_community(cid);

			if (now_com.getUid().equals(uid)) {
				check = true;
			}

			int now_view = now_com.getC_view() + 1;

			int view_res = cservice.up_view(cid, now_view);
			// 들어갔으니까 조회수 한번 올려줘야지

			CommunityDto new_com = cservice.get_community(cid);

			List<Comm_commentDto> commentList = cservice.get_comment(cid);

			int cnt_comment = commentList.size();

			resultMap.put("Community", new_com); // 커뮤니티글
			resultMap.put("commentList", commentList); // 댓글 리스트
			resultMap.put("cnt_comment", cnt_comment); // 댓글 총개수
			resultMap.put("check", check); // 작성자인지 아닌지
			resultMap.put("message", "게시글 가져오기 성공하였습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Community Post Like", notes = "커뮤니티 글 좋아요")
	@PostMapping("/like")
	public ResponseEntity<Map<String, Object>> like_post(@RequestBody Comm_likeDto commlike) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		logger.info("=====> 게시글 좋아요 체크");

		try {
			logger.info("=====> 좋아요 중복 체크 ");
			Comm_likeDto like = cservice.find_like(commlike);
			if (like == null) { // 좋아요 누른적이 없네요? 누르러 갑시다
				int result = cservice.let_like(commlike);
				if (result == 1) {
					logger.info("=====> 커뮤니티 글 좋아요 성공");
					resultMap.put("LIKE", result);
					resultMap.put("message", "게시글을 좋아요 누르셨습니다.");
				} else {
					resultMap.put("message", "게시글 좋아요에 실패하셨습니다.");
				}
				status = HttpStatus.ACCEPTED;
			} else { // like안에 좋아요 값이 있는 거야 이미 좋아요를 누른거지
				// 삭제버튼 구현시켜야죠
				int result = cservice.let_dislike(commlike);
				if (result == 1) {
					logger.info("=====>커뮤니티 글 좋아요 취소");
					resultMap.put("LIKE", 0);
					resultMap.put("message", "게시글 좋아요를 취소하셨습니다.");
				} else {
					resultMap.put("message", "게시글 좋아요 취소에 실패하셨습니다.");
				}
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

}
