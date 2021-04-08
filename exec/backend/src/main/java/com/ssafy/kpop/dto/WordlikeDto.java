package com.ssafy.kpop.dto;

public class WordlikeDto {
	
	//단어좋아요 //wordlike
	
	private String uid;
	private int namu_id;
	public WordlikeDto(String uid, int namu_id) {
		super();
		this.uid = uid;
		this.namu_id = namu_id;
	}
	@Override
	public String toString() {
		return "WordlikeDto [uid=" + uid + ", namu_id=" + namu_id + "]";
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getNamu_id() {
		return namu_id;
	}
	public void setNamu_id(int namu_id) {
		this.namu_id = namu_id;
	}

	
	

	
	

}
