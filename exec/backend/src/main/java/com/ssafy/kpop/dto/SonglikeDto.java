package com.ssafy.kpop.dto;

public class SonglikeDto {
	
	//노래좋아요 //songlike
	
	private String uid;
	private int song_id;

	public SonglikeDto(String uid, int song_id) {
		super();
		this.uid = uid;
		this.song_id = song_id;
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
