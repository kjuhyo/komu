package com.ssafy.kpop.dto;

public class Comm_likeDto {
	
	private String uid;
	private int cid;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	@Override
	public String toString() {
		return "Comm_likeDto [uid=" + uid + ", cid=" + cid + "]";
	}
	
	
	
}
