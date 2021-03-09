package com.ssafy.kpop.dto;

public class UserDto {
	
	private String uid;
	private String nickname;
	private String user_img;
	private String mbti;
	
	public UserDto(String uid, String nickname, String user_img, String mbti) {
		super();
		this.uid = uid;
		this.nickname = nickname;
		this.user_img = user_img;
		this.mbti = mbti;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	
	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", nickname=" + nickname + ", user_img=" + user_img + ", mbti=" + mbti + "]";
	}
	
	
	
	
}
