package com.ssafy.kpop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.portable.OutputStream;
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

	// 가사별 추천 페이지
	@ApiOperation(value = "Lyric Recommend Page", notes = "좋아하는 단어별 추천 페이지")
	@GetMapping("/lyric")
	public String lyricRecommend() throws IOException, InterruptedException {
		String program = "python";
		String url = "C:\\Users\\multicampus\\Desktop\\new\\s04p23c104\\python\\Algorithm\\TF-IDF.py";
		String uid = "prteUBReKZX2";
		
		List<String> args = new ArrayList<>();
	    args.add(program);
	    args.add(url);
	    args.add(uid);
		
	    ProcessBuilder builder = new ProcessBuilder(args);
	    Process process = builder.start();
	    InputStreamReader r = new InputStreamReader(process.getInputStream());
	    BufferedReader br = new BufferedReader(r);
	    
	    System.out.println(br);
	    // json 형태의 string으로 받음
	    String songList = br.readLine();
	    System.out.println(songList);
		return songList;
	}
	
	@ApiOperation(value = "Genre Recommend Page", notes = "좋아하는 노래 장르별 추천 페이지")
	@GetMapping("/genre")
	public String genreRecommend() throws IOException, InterruptedException {
		String program = "python";
		String url = "C:\\Users\\multicampus\\Desktop\\new\\s04p23c104\\python\\Algorithm\\genre.py";
		String uid = "prteUBReKZX2";
		
		List<String> args = new ArrayList<>();
	    args.add(program);
	    args.add(url);
	    args.add(uid);
	    
	    ProcessBuilder builder = new ProcessBuilder(args);
	    Process process = builder.start();
	    InputStreamReader r = new InputStreamReader(process.getInputStream());
	    BufferedReader br = new BufferedReader(r);
	    
	    // json 형태의 string으로 받음
	    String songList = br.readLine();
	    System.out.println(songList);
		return songList;
	}

}
