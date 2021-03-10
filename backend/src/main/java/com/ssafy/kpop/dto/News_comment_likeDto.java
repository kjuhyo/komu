package com.ssafy.kpop.dto;

public class News_comment_likeDto {
	
	// 뉴스좋아요 // news_comment_like
	private int newscl_id;

	public News_comment_likeDto(int newscl_id) {
		super();
		this.newscl_id = newscl_id;
	}

	public int getNewscl_id() {
		return newscl_id;
	}

	public void setNewscl_id(int newscl_id) {
		this.newscl_id = newscl_id;
	}

	@Override
	public String toString() {
		return "News_comment_likeDto [newscl_id=" + newscl_id + "]";
	}
	
	

}
