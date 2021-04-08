package com.ssafy.kpop.dto;

public class Comm_comment_likeDto {
	
	//커뮤니티댓글좋아요 //comm_comment_like
	private String uid;
	private int ccid;
	
	public Comm_comment_likeDto(String uid, int ccid) {
		super();
		this.uid = uid;
		this.ccid = ccid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getCcid() {
		return ccid;
	}

	public void setCcid(int ccid) {
		this.ccid = ccid;
	}

	@Override
	public String toString() {
		return "Comm_comment_likeDto [uid=" + uid + ", ccid=" + ccid + "]";
	}
	
	
	
	
	

}
