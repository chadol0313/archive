package com.study.moim.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class BoardDto {
	
	
	private int b_num;
	private String id;
	private String pwd;
	private String email;
	private int region;
	private int topic;
	private String title;
	private String b_content;
	private Date b_date;
	private int b_count;
	private int reply_count;
	private String state;
	
	
	public BoardDto() {}
	public BoardDto(int b_num, String id, String pwd, String email, int region, int topic, String title,
			String b_content, Date b_date, int b_count, int reply_count, String state) {
		super();
		this.b_num = b_num;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.region = region;
		this.topic = topic;
		this.title = title;
		this.b_content = b_content;
		this.b_date = b_date;
		this.b_count = b_count;
		this.reply_count = reply_count;
		this.state = state;
	}
	

	
	
	
	

}
