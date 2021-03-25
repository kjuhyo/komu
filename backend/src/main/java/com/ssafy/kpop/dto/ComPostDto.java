package com.ssafy.kpop.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ComPostDto : 커뮤니티 글 목록", description ="커뮤니티 검색결과 글목록")
public class ComPostDto {
	@ApiModelProperty(value = "글식별번호")
	private int cid; 
	@ApiModelProperty(value = "글제목")
	private String c_title;
	@ApiModelProperty(value = "글내용")
	private String c_content;
	@ApiModelProperty(value = "작성일")
	private String c_date;
	@ApiModelProperty(value = "조회수")
	private int c_view;
	@ApiModelProperty(value = "작성자닉네임")
	private String nickname;
	@ApiModelProperty(value = "작성자프로필사진")
	private String profile;
	@ApiModelProperty(value = "해시태그")
	private List<HashtagDto> hashtags;
	
	public ComPostDto(int cid, String c_title, String c_content, String c_date, int c_view, String nickname,
			String profile, List<HashtagDto> hashtags) {
		super();
		this.cid = cid;
		this.c_title = c_title;
		this.c_content = c_content;
		this.c_date = c_date;
		this.c_view = c_view;
		this.nickname = nickname;
		this.profile = profile;
		this.hashtags = hashtags;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public int getC_view() {
		return c_view;
	}

	public void setC_view(int c_view) {
		this.c_view = c_view;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public List<HashtagDto> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<HashtagDto> hashtags) {
		this.hashtags = hashtags;
	}

	@Override
	public String toString() {
		return "ComPostDto [cid=" + cid + ", c_title=" + c_title + ", c_content=" + c_content + ", c_date=" + c_date
				+ ", c_view=" + c_view + ", nickname=" + nickname + ", profile=" + profile + ", hashtags=" + hashtags
				+ "]";
	}
	
	
}
