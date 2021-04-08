package com.ssafy.kpop.dto;

public class UserDto {
	
	private String uid;
	private String nickname;
	private String profile;
	private String provider;
	private String provider_id;
	
	public UserDto() {
	}
	
	public UserDto(String uid, String nickname, String profile, String provider, String provider_id) {
		super();
		this.uid = uid;
		this.nickname = nickname;
		this.profile = profile;
		this.provider = provider;
		this.provider_id = provider_id;
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
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	
	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", nickname=" + nickname + ", profile=" + profile + ", provider=" + provider
				+ ", provider_id=" + provider_id + "]";
	}

	
}
