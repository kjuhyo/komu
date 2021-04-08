package com.ssafy.kpop.dto;

public class KomuWikiDto {

	private int namu_id;
	private String uid;
	private String namu_title;
	private String namu_content;
	private String namu_date;
	private String namu_img;
	private String nickname;
	private String profile;
	
	public KomuWikiDto(int namu_id, String uid, String namu_title, String namu_content, String namu_date,
			String namu_img, String nickname, String profile) {
		super();
		this.namu_id = namu_id;
		this.uid = uid;
		this.namu_title = namu_title;
		this.namu_content = namu_content;
		this.namu_date = namu_date;
		this.namu_img = namu_img;
		this.nickname = nickname;
		this.profile = profile;
	}

	public KomuWikiDto() {
		
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

	public String getNamu_img() {
		return namu_img;
	}

	public void setNamu_img(String namu_img) {
		this.namu_img = namu_img;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "KomuWikiDto [namu_id=" + namu_id + ", uid=" + uid + ", namu_title=" + namu_title + ", namu_content="
				+ namu_content + ", namu_date=" + namu_date + ", namu_img=" + namu_img + ", nickname=" + nickname
				+ ", profile=" + profile + "]";
	}
	

}
