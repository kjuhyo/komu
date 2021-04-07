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
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.kpop.dto.KomuWikiDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SingerlikeDto;
import com.ssafy.kpop.dto.WordlikeDto;
import com.ssafy.kpop.service.NamuService;
import com.ssafy.kpop.util.Pagination;
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

	// 게시물 등록하기
	@ApiOperation(value = "Namu Insert", notes = "나무위키 단어 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestPart MultipartFile file, @RequestPart NamuwikiDto namu) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 나무위키 단어 중복 시작");
			NamuwikiDto check = namuservice.check_namu(namu.getNamu_title());

			if (check != null) {
				logger.info("=====> 나무위키 단어 중복 !!");
				resultMap.put("message", "이미 등록되어 있는 단어입니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("=====> 나무위키 글 등록 시작");
				String originName = file.getOriginalFilename();

				String ext = originName.substring(originName.lastIndexOf('.'));
				String saveFileName = UUID.randomUUID().toString() + ext;
				String path = System.getProperty("user.dir");

				File tempfile = new File(path, saveFileName);

				String line = "namu/";

				saveFileName = line + saveFileName;

				file.transferTo(tempfile);
				s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, tempfile)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
				tempfile.delete();

				namu.setNamu_img(url);

				int result = namuservice.insert(namu);

				if (result == 1) {
					logger.info("=====> 나무위키 글 등록 성공");
					resultMap.put("message", "단어 등록에 성공하였습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					logger.info("=====> 나무위키 글 등록 실패");
					resultMap.put("message", "단어 등록에 실패하였습니다.");
					status = HttpStatus.NOT_FOUND;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 게시물 등록하기
	@ApiOperation(value = "Namu Insert(NO FILE) ", notes = "나무위키 단어만 등록! 사진없이!")
	@PostMapping("/insert/np")
	public ResponseEntity<Map<String, Object>> insert_nopic(@RequestBody NamuwikiDto namu) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 나무위키 단어 중복 시작");
			NamuwikiDto check = namuservice.check_namu(namu.getNamu_title());

			if (check != null) {
				logger.info("=====> 나무위키 단어 중복 !!");
				resultMap.put("message", "이미 등록되어 있는 단어입니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("=====> 나무위키 글 등록 시작! 사진없이~");
				int result = namuservice.insert_nopic(namu);

				if (result == 1) {
					logger.info("=====> 나무위키 글 등록 성공");
					resultMap.put("message", "단어 등록에 성공하였습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					logger.info("=====> 나무위키 글 등록 실패");
					resultMap.put("message", "단어 등록에 실패하였습니다.");
					status = HttpStatus.NOT_FOUND;
				}
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
	@ApiOperation(value = "Namu Update", notes = "나무위키 등록 단어 수정")
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody NamuwikiDto namu) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			logger.info("=====> 내용 수정");
			int result = namuservice.update(namu);

			if (result == 1) {
				System.out.println("=====> 수정 성공");
				resultMap.put("message", "글 수정에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 수정에 실패하였습니다.");
				status = HttpStatus.NOT_FOUND;
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
	@ApiOperation(value = "Namu Delete", notes = "나무위키 등록 단어 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody NamuwikiDto namu) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 단어 삭제");
			int result = namuservice.delete(namu);

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

	// 게시물 확인하기
	@ApiOperation(value = "Namu single word", notes = "나무 단어 하나 보기")
	@GetMapping("/word/{namu_id}")
	public ResponseEntity<Map<String, Object>> getboard(@PathVariable int namu_id, @RequestParam String loginid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 단어 부르기");
			NamuwikiDto dto = namuservice.call_namu(namu_id);

			if (dto != null) {
				int like = namuservice.ami_like(loginid, dto.getNamu_id());
				logger.info("=====> 글 부르기 성공");

				resultMap.put("namuwiki", dto);
				resultMap.put("LIKE", like);
				resultMap.put("message", "단어 가져오기 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "단어 가져오기 실패하였습니다.");
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

	@ApiOperation(value = "Namu Like Url", notes = "나무위키좋아요/취소  url")
	@PostMapping("/like")
	public ResponseEntity<Map<String, Object>> do_like(@RequestBody WordlikeDto wordlike) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		logger.info("=====> 단어 좋아요 체크");

		try {
			logger.info("=====> 좋아요 중복 체크 ");
			WordlikeDto like = namuservice.find_like(wordlike);
			if (like == null) { // 좋아요 누른적이 없네요? 누르러 갑시다
				int result = namuservice.let_like(wordlike); // insert
				if (result == 1) {
					logger.info("=====> 단어 좋아요 성공");
					resultMap.put("LIKE", result);
					resultMap.put("message", "단어를 좋아요 하셨습니다.");
				} else {
					resultMap.put("message", "단어 좋아요에 실패하였습니다.");
				}
				status = HttpStatus.ACCEPTED;
			} else { // like안에 좋아요 값이 있는 거야 이미 좋아요를 누른거지
				resultMap.put("message", "이미 좋아요를 누르셨습니다.");
				// 삭제버튼 구현시켜야죠
				int result = namuservice.let_dislike(wordlike);
				if (result == 1) {
					logger.info("=====> 단어 좋아요 취소");
					resultMap.put("LIKE", 0);
					resultMap.put("message", "단어를 좋아요를 취소하셨습니다.");
				} else {
					resultMap.put("message", "단어를 좋아요를 취소에 실패하셨습니다.");
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

	// 나무위키 단어 전체다 가져오기
	@ApiOperation(value = "NamuWiki page list", notes = "나무위키 페이지 단어 목록")
	@GetMapping("/list/{page}")
	public ResponseEntity<Map<String, Object>> allList(@PathVariable int page) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int range = (page / 10) + 1;
		int listCnt = 0;
		List<KomuWikiDto> list = null;

		try {
			logger.info("=====> 단어목록 확인");

			// 총 게시물 개수
			listCnt = namuservice.get_total();

			// 페이지 처리
			logger.info("=====> 페이징");
			Pagination pagination = new Pagination();
			pagination.pageInfo(page, range, listCnt);

			logger.info("=====> 글 목록 받기");
			list = namuservice.KomuInfo(pagination);

			System.out.println(list);

			resultMap.put("pagination", pagination);
			resultMap.put("list", list);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("리스트업 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 검색에 해당하는 리스트만 가져오기
	@ApiOperation(value = "NamuWiki Search page list", notes = "나무위키 페이지 검색 단어 목록")
	@GetMapping("/search/{page}")
	public ResponseEntity<Map<String, Object>> allList(@PathVariable int page, @RequestParam String word) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int range = (page / 10) + 1;
		int listCnt = 0;
		List<KomuWikiDto> list = null;

		try {
			logger.info("=====> 단어목록 확인");

			// 총 게시물 개수
			listCnt = namuservice.search_total(word);

			// 페이지 처리
			logger.info("=====> 페이징");
			Pagination pagination = new Pagination();
			pagination.pageInfo(page, range, listCnt);

			int startList = pagination.getStartList();
			int listSize = pagination.getListSize();

			logger.info("=====> 단어 목록 받기");
//			list = namuservice.search_list(word, startList, listSize);
			list = namuservice.searchkomu_list(word, startList, listSize);
			System.out.println(list);

			resultMap.put("pagination", pagination);
			resultMap.put("list", list);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("리스트업 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
