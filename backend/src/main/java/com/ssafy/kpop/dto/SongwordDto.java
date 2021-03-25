package com.ssafy.kpop.dto;

public class SongwordDto {
	
	//노래별단어장 //songword
	
	private int song_id; //노래의 식별아이디
	private String namu_title; //노래에 들어가는 단어
	
	public SongwordDto(int song_id, String namu_title) {
		super();
		this.song_id = song_id;
		this.namu_title = namu_title;
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public String getNamu_title() {
		return namu_title;
	}

	public void setNamu_title(String namu_title) {
		this.namu_title = namu_title;
	}

	@Override
	public String toString() {
		return "SongwordDto [song_id=" + song_id + ", namu_title=" + namu_title + "]";
	}
	 
	

}
