package com.ssafy.kpop.dto;

public class SongwordDto {
	
	//노래별단어장 //songword
	
	private int song_id;
	private int singer_id;
	
	public SongwordDto(int song_id, int singer_id) {
		super();
		this.song_id = song_id;
		this.singer_id = singer_id;
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public int getSinger_id() {
		return singer_id;
	}

	public void setSinger_id(int singer_id) {
		this.singer_id = singer_id;
	}

	@Override
	public String toString() {
		return "SongwordDto [song_id=" + song_id + ", singer_id=" + singer_id + "]";
	}
	
	

}
