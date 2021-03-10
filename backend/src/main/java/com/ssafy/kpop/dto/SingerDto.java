package com.ssafy.kpop.dto;

public class SingerDto {
	
	private int singer_id;
	private String singer_img;
	private String singer_name;
	
	public int getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(int singer_id) {
		this.singer_id = singer_id;
	}
	public String getSinger_img() {
		return singer_img;
	}
	public void setSinger_img(String singer_img) {
		this.singer_img = singer_img;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	
	public SingerDto(int singer_id, String singer_img, String singer_name) {
		super();
		this.singer_id = singer_id;
		this.singer_img = singer_img;
		this.singer_name = singer_name;
	}
	
	@Override
	public String toString() {
		return "SingerDto [singer_id=" + singer_id + ", singer_img=" + singer_img + ", singer_name=" + singer_name
				+ "]";
	}
	
	
}
