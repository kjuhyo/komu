package com.ssafy.kpop.dto;

public class GanreDto {

	private int ganre_id;
	private String ganre_name;
	
	public int getGanre_id() {
		return ganre_id;
	}
	public void setGanre_id(int ganre_id) {
		this.ganre_id = ganre_id;
	}
	public String getGanre_name() {
		return ganre_name;
	}
	public void setGanre_name(String ganre_name) {
		this.ganre_name = ganre_name;
	}
	
	public GanreDto(int ganre_id, String ganre_name) {
		super();
		this.ganre_id = ganre_id;
		this.ganre_name = ganre_name;
	}
	
	@Override
	public String toString() {
		return "GanreDto [ganre_id=" + ganre_id + ", ganre_name=" + ganre_name + "]";
	}
	
	
	
}
