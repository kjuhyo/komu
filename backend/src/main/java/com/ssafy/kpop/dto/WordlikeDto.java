package com.ssafy.kpop.dto;

public class WordlikeDto {
	
	//단어좋아요 //wordlike
	
	private String uid;
	private int namuid;
	
	public WordlikeDto(String uid, int namuid) {
		super();
		this.uid = uid;
		this.namuid = namuid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getNamuid() {
		return namuid;
	}
	public void setNamuid(int namuid) {
		this.namuid = namuid;
	}
	
	@Override
	public String toString() {
		return "WordlikeDto [uid=" + uid + ", namuid=" + namuid + "]";
	}
	
	

}
