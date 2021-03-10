package com.ssafy.kpop.dto;

public class SonglikeDto {
	
	//노래좋아요 //songlike
	
	private int song_id;
	private String uid;
	
	public SonglikeDto(int song_id, String uid) {
		super();
		this.song_id = song_id;
		this.uid = uid;
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "SonglikeDto [song_id=" + song_id + ", uid=" + uid + "]";
	}
	
	
	

}
