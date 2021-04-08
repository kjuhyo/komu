package com.ssafy.kpop.dto;

public class Comm_comment_nickDto {

	private int ccid;
	private int cid;
	private String uid;
	private String cc_content;
	private String cc_date;
	private int is_delete;
	private String nickname;
	private String profile;
	
	public Comm_comment_nickDto(int ccid, int cid, String uid, String cc_content, String cc_date, int is_delete,
			String nickname, String profile) {
		super();
		this.ccid = ccid;
		this.cid = cid;
		this.uid = uid;
		this.cc_content = cc_content;
		this.cc_date = cc_date;
		this.is_delete = is_delete;
		this.nickname = nickname;
		this.profile = profile;
	}
	public int getCcid() {
		return ccid;
	}
	public void setCcid(int ccid) {
		this.ccid = ccid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCc_content() {
		return cc_content;
	}
	public void setCc_content(String cc_content) {
		this.cc_content = cc_content;
	}
	public String getCc_date() {
		return cc_date;
	}
	public void setCc_date(String cc_date) {
		this.cc_date = cc_date;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
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
		return "Comm_comment_nickDto [ccid=" + ccid + ", cid=" + cid + ", uid=" + uid + ", cc_content=" + cc_content
				+ ", cc_date=" + cc_date + ", is_delete=" + is_delete + ", nickname=" + nickname + ", profile="
				+ profile + "]";
	}
	
	
}

