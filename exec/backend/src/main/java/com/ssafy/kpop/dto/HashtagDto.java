package com.ssafy.kpop.dto;

public class HashtagDto {
	
	private int hashtag_id;
	private String hashtag_content;
	
	//해시태그 // hashtag
	public HashtagDto(int hashtag_id, String hashtag_content) {
		super();
		this.hashtag_id = hashtag_id;
		this.hashtag_content = hashtag_content;
	}
	
	public int getHashtag_id() {
		return hashtag_id;
	}
	public void setHashtag_id(int hashtag_id) {
		this.hashtag_id = hashtag_id;
	}
	public String getHashtag_content() {
		return hashtag_content;
	}
	public void setHashtag_content(String hashtag_content) {
		this.hashtag_content = hashtag_content;
	}
	@Override
	public String toString() {
		return "HashtagDto [hashtag_id=" + hashtag_id + ", hashtag_content=" + hashtag_content + "]";
	}
	
	

}
