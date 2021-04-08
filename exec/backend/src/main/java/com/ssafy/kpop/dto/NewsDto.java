package com.ssafy.kpop.dto;

public class NewsDto {
	
	private int nid;
	private String n_title;
	private String n_writer;
	private String n_content;
	private String n_date;
	private String n_img;
	private int n_view;
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_writer() {
		return n_writer;
	}
	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public String getN_date() {
		return n_date;
	}
	public void setN_date(String n_date) {
		this.n_date = n_date;
	}
	public String getN_img() {
		return n_img;
	}
	public void setN_img(String n_img) {
		this.n_img = n_img;
	}
	public int getN_view() {
		return n_view;
	}
	public void setN_view(int n_view) {
		this.n_view = n_view;
	}
	
	public NewsDto(int nid, String n_title, String n_writer, String n_content, String n_date, String n_img,
			int n_view) {
		super();
		this.nid = nid;
		this.n_title = n_title;
		this.n_writer = n_writer;
		this.n_content = n_content;
		this.n_date = n_date;
		this.n_img = n_img;
		this.n_view = n_view;
	}
	
	@Override
	public String toString() {
		return "NewsDto [nid=" + nid + ", n_title=" + n_title + ", n_writer=" + n_writer + ", n_content=" + n_content
				+ ", n_date=" + n_date + ", n_img=" + n_img + ", n_view=" + n_view + "]";
	}
	
	
	
}
