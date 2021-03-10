package com.ssafy.kpop.dto;

public class Com_hashtagDto {
	private int hashtag_id;
	private int cid;
	
	//커뮤니티글해시태그 // com_hashtag
	
	public Com_hashtagDto(int hashtag_id, int cid) {
		super();
		this.hashtag_id = hashtag_id;
		this.cid = cid;
	}
	
	public int getHashtag_id() {
		return hashtag_id;
	}
	public void setHashtag_id(int hashtag_id) {
		this.hashtag_id = hashtag_id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Com_hashtagDto [hashtag_id=" + hashtag_id + ", cid=" + cid + "]";
	}
	
	
	

}
