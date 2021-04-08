package com.ssafy.kpop.dto;

public class LogDto {
//댓글로그 // log
	
	private String uid;
	private String datetime;
	private int cid;
	
	public LogDto(String uid, String datetime, int cid) {
		super();
		this.uid = uid;
		this.datetime = datetime;
		this.cid = cid;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	@Override
	public String toString() {
		return "LogDto [uid=" + uid + ", datetime=" + datetime + ", cid=" + cid + "]";
	}
	
	
}
