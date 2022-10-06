package com.study.moim.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyDto {

	private int r_num;
	private Date r_date;
	private String r_content;
	private String r_id;
	private String r_pwd;
	private int b_num;
	
	
	
	public ReplyDto() {}
	
	public ReplyDto(int r_num, Date r_date, String r_content, String r_id, String r_pwd, int b_num) {
		super();
		this.r_num = r_num;
		this.r_date = r_date;
		this.r_content = r_content;
		this.r_id = r_id;
		this.r_pwd = r_pwd;
		this.b_num = b_num;
	}
	
	
	
}
