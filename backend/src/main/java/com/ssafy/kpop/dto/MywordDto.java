package com.ssafy.kpop.dto;

public class MywordDto {
//나의 단어장 //myword
	private String uid;

	public MywordDto(String uid) {
		super();
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "MywordDto [uid=" + uid + "]";
	}
	
}
