package com.ssafy.kpop.dto;

public class News_commentDto {
	
	private int ncid;
	private int nid;
	private String nc_content;
	private String nc_date;
	private int is_delete;
	
	public int getNcid() {
		return ncid;
	}
	public void setNcid(int ncid) {
		this.ncid = ncid;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNc_content() {
		return nc_content;
	}
	public void setNc_content(String nc_content) {
		this.nc_content = nc_content;
	}
	public String getNc_date() {
		return nc_date;
	}
	public void setNc_date(String nc_date) {
		this.nc_date = nc_date;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	
	public News_commentDto(int ncid, int nid, String nc_content, String nc_date, int is_delete) {
		super();
		this.ncid = ncid;
		this.nid = nid;
		this.nc_content = nc_content;
		this.nc_date = nc_date;
		this.is_delete = is_delete;
	}
	
	@Override
	public String toString() {
		return "News_commentDto [ncid=" + ncid + ", nid=" + nid + ", nc_content=" + nc_content + ", nc_date=" + nc_date
				+ ", is_delete=" + is_delete + "]";
	}
	
	
	
}
