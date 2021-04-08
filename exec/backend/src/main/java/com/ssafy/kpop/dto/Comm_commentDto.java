package com.ssafy.kpop.dto;

public class Comm_commentDto {
	
	//커뮤니티 댓글 //comm_comment
	private int ccid;
	private int cid;
	private String uid;
	private String cc_content;
	private String cc_date;
	private int is_delete;

	public Comm_commentDto(int ccid, int cid, String uid, String cc_content, String cc_date, int is_delete) {
		super();
		this.ccid = ccid;
		this.cid = cid;
		this.uid = uid;
		this.cc_content = cc_content;
		this.cc_date = cc_date;
		this.is_delete = is_delete;
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

	@Override
	public String toString() {
		return "Comm_commentDto [ccid=" + ccid + ", cid=" + cid + ", uid=" + uid + ", cc_content=" + cc_content
				+ ", cc_date=" + cc_date + ", is_delete=" + is_delete + "]";
	}
	
	

}
