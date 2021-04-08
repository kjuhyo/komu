package com.ssafy.kpop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.omg.CORBA.portable.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.dto.SongDto;
import com.ssafy.kpop.service.MyService;
import com.ssafy.kpop.service.SongService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Api("RecommendController V1")
@RestController
@RequestMapping(value = "/recommend")
public class RecommendController {
	public static final Logger logger = LoggerFactory.getLogger(RecommendController.class);

	@Autowired
	SongService songservice;
	
	@Autowired
	MyService myService;

	// 가사별 추천 페이지
	@ApiOperation(value = "Lyric Recommend Page", notes = "좋아하는 단어별 추천 페이지")
	@GetMapping("/lyric")
	public ResponseEntity<Map<String, Object>> lyricRecommend(@RequestParam String uid) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 가사 추천 노래 가져오기");

			String program = "python";
//			String url = "C:\\Users\\multicampus\\Desktop\\ssafy\\PJT 2\\Sub PJT 3\\s04p23c104\\python\\Algorithm\\TF-IDF.py";
//		String uid = "prteUBReKZX2";
			
			String path = System.getProperty("user.dir");
			path=path.replace("\\", "\\\\");
			path=path.replace("backend", "python\\\\Algorithm\\\\TF-IDF.py");

			List<String> args = new ArrayList<>();
			args.add(program);
			args.add(path);
			args.add(uid);
			
			List<NamuwikiDto> l = myService.showMyword(uid);
			if(l.size()!=0) {
				System.out.println("하이~");
				System.out.println(l.size());
				resultMap.put("l", l);
				
				ProcessBuilder builder = new ProcessBuilder(args);
				Process process = builder.start();
				InputStreamReader r = new InputStreamReader(process.getInputStream());
				BufferedReader br = new BufferedReader(r);
				
				// json 형태의 string으로 받음
				String songList = br.readLine();
				
				System.out.println(songList);
				songList = songList.substring(1, songList.length() - 1); // 앞뒤 짤랐다!!
				String[] slist = songList.split(", ");// 분해했다!
				int[] ilist = new int[slist.length];
				List<SongDto> dtoList = new ArrayList<>();
				
				for (int i = 0; i < ilist.length; i++) {
					ilist[i] = Integer.parseInt(slist[i]); // int로 바꿔고
					SongDto temp = songservice.get_song(ilist[i]);
					dtoList.add(temp);
				}
				
				resultMap.put("dtoList", dtoList);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Genre Recommend Page", notes = "좋아하는 노래 장르별 추천 페이지")
	@GetMapping("/genre") // @RequestParam String login
	public ResponseEntity<Map<String, Object>> genreRecommend(@RequestParam String uid) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {

			logger.info("=====> 장르 추천 노래 가져오기");

			String program = "python";
//			String url = "C:\\Users\\multicampus\\Desktop\\ssafy\\PJT 2\\Sub PJT 3\\s04p23c104\\python\\Algorithm\\genre.py";
			
			String path = System.getProperty("user.dir");
			path=path.replace("\\", "\\\\");
			path=path.replace("backend", "python\\\\Algorithm\\\\genre.py");

			List<String> args = new ArrayList<>();
			args.add(program);
//			args.add(url);
			args.add(path);
			args.add(uid);

			List<SongDto> l = myService.showMySinger(uid);
			if(l.size()!=0) {
				System.out.println("하이~");
				System.out.println(l.size());
				resultMap.put("l", l);
				
				ProcessBuilder builder = new ProcessBuilder(args);
				Process process = builder.start();
				InputStreamReader r = new InputStreamReader(process.getInputStream());
				BufferedReader br = new BufferedReader(r);
				
				// json 형태의 string으로 받음
				String songList = br.readLine();
				
				System.out.println(songList);
				songList = songList.substring(1, songList.length() - 1); // 앞뒤 짤랐다!!
				String[] slist = songList.split(", ");// 분해했다!
				int[] ilist = new int[slist.length];
				List<SongDto> dtoList = new ArrayList<>();
				
				for (int i = 0; i < ilist.length; i++) {
					ilist[i] = Integer.parseInt(slist[i]); // int로 바꿔고
					SongDto temp = songservice.get_song(ilist[i]);
					dtoList.add(temp);
				}
				
				resultMap.put("dtoList", dtoList);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

			// json 형태의 string으로 받음
//			String songList = br.readLine();
//			System.out.println(songList);
//			songList = songList.substring(1, songList.length() - 1); // 앞뒤 짤랐다!!
//			String[] slist = songList.split(", ");// 분해했다!
//			int[] ilist = new int[slist.length];
//			List<SongDto> dtoList = new ArrayList<>();
//
//			for (int i = 0; i < ilist.length; i++) {
//				ilist[i] = Integer.parseInt(slist[i]); // int로 바꿔고
//				SongDto temp = songservice.get_song(ilist[i]);
//				dtoList.add(temp);
//			}
//
//			resultMap.put("dtoList", dtoList);
//			resultMap.put("message", "SUCCESS");
//			status = HttpStatus.ACCEPTED;
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error("실행 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
