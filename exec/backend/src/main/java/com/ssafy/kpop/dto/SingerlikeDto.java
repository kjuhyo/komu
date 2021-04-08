package com.ssafy.kpop.dto;

public class SingerlikeDto {
	
	private String uid;
	private int singer_id;
	
	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public int getSinger_id() {
		return singer_id;
	}


	public void setSinger_id(int singer_id) {
		this.singer_id = singer_id;
	}


	public SingerlikeDto(String uid, int singer_id) {
		super();
		this.uid = uid;
		this.singer_id = singer_id;
	}


	@Override
	public String toString() {
		return "SingerlikeDto [uid=" + uid + ", singer_id=" + singer_id + "]";
	}
	
	
}
