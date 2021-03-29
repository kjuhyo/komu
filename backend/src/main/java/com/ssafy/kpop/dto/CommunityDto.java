package com.ssafy.kpop.dto;

public class CommunityDto {
	//커뮤니티글 //community
	
	private int cid;
	private String uid;
	private String c_title;
	private String c_content;
	private String c_date;
	private int is_delete;
	private int c_view;
	private int c_like_cnt;
	private String c_img;
	
	public CommunityDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommunityDto(int cid, String uid, String c_title, String c_content, String c_date, int is_delete, int c_view,
			int c_like_cnt, String c_img) {
		super();
		this.cid = cid;
		this.uid = uid;
		this.c_title = c_title;
		this.c_content = c_content;
		this.c_date = c_date;
		this.is_delete = is_delete;
		this.c_view = c_view;
		this.c_like_cnt = c_like_cnt;
		this.c_img = c_img;
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

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public int getC_view() {
		return c_view;
	}

	public void setC_view(int c_view) {
		this.c_view = c_view;
	}

	public int getC_like_cnt() {
		return c_like_cnt;
	}

	public void setC_like_cnt(int c_like_cnt) {
		this.c_like_cnt = c_like_cnt;
	}

	public String getC_img() {
		return c_img;
	}

	public void setC_img(String c_img) {
		this.c_img = c_img;
	}

	@Override
	public String toString() {
		return "CommunityDto [cid=" + cid + ", uid=" + uid + ", c_title=" + c_title + ", c_content=" + c_content
				+ ", c_date=" + c_date + ", is_delete=" + is_delete + ", c_view=" + c_view + ", c_like_cnt="
				+ c_like_cnt + ", c_img=" + c_img + "]";
	}
	 
	
	
	

}
