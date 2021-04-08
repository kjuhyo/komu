package com.ssafy.kpop.dto;

public class LoginCallBackDto {
	private String uid;
	private String nickname;
	private String profile;
	private String provider;
	
	public LoginCallBackDto() {
	}
	
	public LoginCallBackDto(String uid, String nickname, String profile, String provider) {
		super();
		this.uid = uid;
		this.nickname = nickname;
		this.profile = profile;
		this.provider = provider;
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

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "LoginCallBackDto [uid=" + uid + ", nickname=" + nickname + ", profile=" + profile + ", provider="
				+ provider + "]";
	}

	
}
