package com.ssafy.kpop.dto;

public class Song_like_countDto {
	
	//노래 좋아요 카운트 DTO
	private int song_id;
	private int song_like_cnt;
	
	public Song_like_countDto(int song_id, int song_like_cnt) {
		super();
		this.song_id = song_id;
		this.song_like_cnt = song_like_cnt;
	}

	public Song_like_countDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public int getSong_like_cnt() {
		return song_like_cnt;
	}

	public void setSong_like_cnt(int song_like_cnt) {
		this.song_like_cnt = song_like_cnt;
	}

	@Override
	public String toString() {
		return "Song_like_countDto [song_id=" + song_id + ", song_like_cnt=" + song_like_cnt + "]";
	}

	
}
