package com.ssafy.kpop.dto;

public class SongwordIdDto {

	private int song_id;
	private int namu_id;
	private String namu_title;
	
	public SongwordIdDto(int song_id, int namu_id, String namu_title) {
		super();
		this.song_id = song_id;
		this.namu_id = namu_id;
		this.namu_title = namu_title;
	}

	public SongwordIdDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public int getNamu_id() {
		return namu_id;
	}

	public void setNamu_id(int namu_id) {
		this.namu_id = namu_id;
	}

	public String getNamu_title() {
		return namu_title;
	}

	public void setNamu_title(String namu_title) {
		this.namu_title = namu_title;
	}

	@Override
	public String toString() {
		return "SongwordIdDto [song_id=" + song_id + ", namu_id=" + namu_id + ", namu_title=" + namu_title + "]";
	}
	
	
}
