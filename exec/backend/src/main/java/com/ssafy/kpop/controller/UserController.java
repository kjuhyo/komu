package com.ssafy.kpop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.LoginCallBackDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.UserDto;
import com.ssafy.kpop.service.JwtServiceImpl;
import com.ssafy.kpop.service.UserService;
import com.ssafy.kpop.service.oauth.GoogleOauth;
import com.ssafy.kpop.service.oauth.SocialOauth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("UserController V1")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired 
	private UserService userService;
	
	@Autowired
	private GoogleOauth googleOauthService;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	/**
	 * 소셜로그인 access_token을 전달 받아 정보 전달 
	 * 
	 * @param -
	 * @return List<BlogPostDto>
	 */
	@ApiOperation(value = "access_token", notes ="@param : access_token  </br> @return : access_token, message, user")
	@PostMapping("/login/{socialLoginType}")
	public ResponseEntity<Map<String, Object>> access(@PathVariable("socialLoginType") String socialLoginType,
			@RequestBody String accessTocken) {
		System.out.println("#" + socialLoginType + "로그인 요청됨!!");
		System.out.println("# accessTocken: " + accessTocken);
		SocialOauth socialOauth = getSocialOauth(socialLoginType);

		HashMap<String, Object> userInfo = socialOauth.getUserInfoFromOauth(accessTocken);
		logger.info("#Get userInfo: {}", userInfo);

		UserDto user = null;
		Map<String, Object> resultMap = new HashMap<>();
		LoginCallBackDto loginCallBackDto = new LoginCallBackDto();

		try {
			user = userService.findByProvider(userInfo);
			if (user == null) {
				logger.info("#최초 로그인입니다.");
				loginCallBackDto = userService.save(userInfo);
			} else {
				logger.info("#기존회원입니다.");
				loginCallBackDto = getLoginCallBackByUid(user.getUid());
			}
			
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
		}

		//System.out.println("엑세스로그인콜백dto"+loginCallBackDto.toString());
		String token = jwtService.create("uid", loginCallBackDto.getUid(), "access_token");
		//System.out.println(token);
		logger.debug("#토큰정보: " + token);
		resultMap.put("access_token", token);
		resultMap.put("user", loginCallBackDto);
		//System.out.println("resultMap "+resultMap);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	/**
	 * Frontend에 리턴할 유저정보 객체 생성
	 * 
	 * @param uid
	 * @return LoginCallBackDto
	 */
	public LoginCallBackDto getLoginCallBackByUid(String uid) {
		LoginCallBackDto loginCallBackDto = null;
		try {
			//System.out.println("로그인콜백"+uid);
			UserDto user = userService.findById(uid); 
			//System.out.println("컨트롤러"+user);
			
			loginCallBackDto = new LoginCallBackDto(uid, user.getNickname(), user.getProfile(), user.getProvider());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("로그인콜백dto"+loginCallBackDto); 
		return loginCallBackDto;
	}
	
	/**
	 * JWT 토큰을 이용한 유저 정보 반환
	 * 
	 * @param -
	 * @return 
	 */
	@ApiOperation(value = "헤더에 담긴 JWT 토큰 값을 확인, <br> 이를 이용한 유저 정보 검색 및 반환", notes ="@param :  </br> @return : message, user")
	@GetMapping("/getUserInfo")
	public ResponseEntity<Map<String, Object>> getUserInfo(
			HttpServletRequest request ) {
		final String jwt = request.getHeader("authorization");
		String uid = null;
		uid = jwtService.getUserId(jwt);
		System.out.println("uid "+uid);
		
		Map<String, Object> resultMap = new HashMap<>();
		LoginCallBackDto loginCallBackDto = null;
		try {
			loginCallBackDto = getLoginCallBackByUid(uid);
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
		}
		
		logger.info("#Get userInfo: {}", loginCallBackDto);
//		String token = jwtService.create("uid", uid, "access_token");
//		logger.debug("#토큰정보: " + token);
//		resultMap.put("access_token", token);
		resultMap.put("user", loginCallBackDto);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@GetMapping("/getRefreshToken")
	public ResponseEntity<Map<String, Object>> getRefreshToken(	HttpServletRequest request ) {

		final String jwt = request.getHeader("access_token");
		Map<String, Object> resultMap = new HashMap<>();
		String uid = jwtService.getUserId(jwt);
		
		if(uid == null ) {
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		
		String token = jwtService.create("uid", uid, "access_token");
		logger.debug("#토큰정보: " + token);
		resultMap.put("access_token", token);
		resultMap.put("message", SUCCESS);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	/**
	 * 프로필 정보 모달창
	 * uid를 이용한 유저 정보 반환
	 * 
	 * @param -
	 * @return 
	 */
	@ApiOperation(value = "유저 프로필 정보", notes ="@param : uid  </br> @return : UserDto")
	@GetMapping("/profile/{uid}")
	public ResponseEntity<Map<String, Object>> profileByUid( 
			@PathVariable String uid ) {

		System.out.println("#"+ uid +" 프로필 정보 호출");
		Map<String, Object> resultMap = new HashMap<>();
		UserDto user;
		try {
			user = userService.findById(uid);
			if(user == null ) {
				resultMap.put("message", "잘못된 회원정보 입니다.");
			}else {
				resultMap.put("message", SUCCESS);
				resultMap.put("info", user);
				logger.info("#Get userInfo: {}", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "잘못된 회원정보 입니다.");
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param UserDto
	 * @return 
	 */
	@ApiOperation(value = "회원 정보 수정", notes ="@param : UserInfoDto </br> @return : ")
	@PostMapping("/updateInfo")
	public ResponseEntity<Map<String, Object>> updateUser(
			@RequestBody UserDto user) {
		System.out.println("#회원정보 수정 호출!!!");
		logger.info("#Get userInfo: {}", user);
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			userService.updateUser(user);
			resultMap.put("message", "정보 수정이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "정보 수정에 실패하였습니다.");
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	public SocialOauth getSocialOauth(String socialLoginType) {
		switch(socialLoginType) {
		case "google":
			return googleOauthService;
		default:
			return null;
		}
	}
	
	/*
	 * uid로 닉네임검색 
	 * 
	 * @param uid
	 * @return String nickname
	 * */
	@ApiOperation(value="uid로 닉네임검색", notes = "@param uid </br> @return String nickname")
	@GetMapping("/nickname/{uid}")
	public ResponseEntity<String> getSingerName(@PathVariable String uid){
		HttpStatus status=HttpStatus.ACCEPTED;
		String result="";
		try {
			result=userService.findNickName(uid);
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(result, status);
	}
}
