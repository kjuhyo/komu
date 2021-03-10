package com.ssafy.kpop.dto;

public class NamuwikiDto {

	//나무위키(전체단어장) //namuwiki
	private int namu_id;
	private String uid;
	private int song_id;
	private int singer_id;
	private String namu_title;
	private String namu_content;
	private String namu_date;
	private int is_delete;
	private String namu_img;
	
	public NamuwikiDto(int namu_id, String uid, int song_id, int singer_id, String namu_title, String namu_content,
			String namu_date, int is_delete, String namu_img) {
		super();
		this.namu_id = namu_id;
		this.uid = uid;
		this.song_id = song_id;
		this.singer_id = singer_id;
		this.namu_title = namu_title;
		this.namu_content = namu_content;
		this.namu_date = namu_date;
		this.is_delete = is_delete;
		this.namu_img = namu_img;
	}

	public int getNamu_id() {
		return namu_id;
	}

	public void setNamu_id(int namu_id) {
		this.namu_id = namu_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getNamu_title() {
		return namu_title;
	}

	public void setNamu_title(String namu_title) {
		this.namu_title = namu_title;
	}

	public String getNamu_content() {
		return namu_content;
	}

	public void setNamu_content(String namu_content) {
		this.namu_content = namu_content;
	}

	public String getNamu_date() {
		return namu_date;
	}

	public void setNamu_date(String namu_date) {
		this.namu_date = namu_date;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public String getNamu_img() {
		return namu_img;
	}

	public void setNamu_img(String namu_img) {
		this.namu_img = namu_img;
	}

	@Override
	public String toString() {
		return "NamuwikiDto [namu_id=" + namu_id + ", uid=" + uid + ", song_id=" + song_id + ", singer_id=" + singer_id
				+ ", namu_title=" + namu_title + ", namu_content=" + namu_content + ", namu_date=" + namu_date
				+ ", is_delete=" + is_delete + ", namu_img=" + namu_img + "]";
	}
	

	
}
