package com.ssafy.kpop.dto;

public class ReportDto {
	//신고 //report
	
	private int report_id;
	private String uid;
	private String report_date;
	private String report_content;
	private int report_type;
	
	public ReportDto(int report_id, String uid, String report_date, String report_content, int report_type) {
		super();
		this.report_id = report_id;
		this.uid = uid;
		this.report_date = report_date;
		this.report_content = report_content;
		this.report_type = report_type;
	}

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public int getReport_type() {
		return report_type;
	}

	public void setReport_type(int report_type) {
		this.report_type = report_type;
	}

	@Override
	public String toString() {
		return "ReportDto [report_id=" + report_id + ", uid=" + uid + ", report_date=" + report_date
				+ ", report_content=" + report_content + ", report_type=" + report_type + "]";
	}
	
	
	
	
	

}
