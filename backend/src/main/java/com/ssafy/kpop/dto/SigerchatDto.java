package com.ssafy.kpop.dto;

public class SigerchatDto {
	
	private int singerchat_id;
	private int singer_id;
	private String singerchat_content;
	private String uid;
	private String singerchat_date;
	private int is_delete;
	
	public int getSingerchat_id() {
		return singerchat_id;
	}


	public void setSingerchat_id(int singerchat_id) {
		this.singerchat_id = singerchat_id;
	}


	public int getSinger_id() {
		return singer_id;
	}


	public void setSinger_id(int singer_id) {
		this.singer_id = singer_id;
	}


	public String getSingerchat_content() {
		return singerchat_content;
	}


	public void setSingerchat_content(String singerchat_content) {
		this.singerchat_content = singerchat_content;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getSingerchat_date() {
		return singerchat_date;
	}


	public void setSingerchat_date(String singerchat_date) {
		this.singerchat_date = singerchat_date;
	}


	public int getIs_delete() {
		return is_delete;
	}


	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}


	public SigerchatDto(int singerchat_id, int singer_id, String singerchat_content, String uid, String singerchat_date,
			int is_delete) {
		super();
		this.singerchat_id = singerchat_id;
		this.singer_id = singer_id;
		this.singerchat_content = singerchat_content;
		this.uid = uid;
		this.singerchat_date = singerchat_date;
		this.is_delete = is_delete;
	}


	@Override
	public String toString() {
		return "SigerchatDto [singerchat_id=" + singerchat_id + ", singer_id=" + singer_id + ", singerchat_content="
				+ singerchat_content + ", uid=" + uid + ", singerchat_date=" + singerchat_date + ", is_delete="
				+ is_delete + "]";
	}
	
	
}
