package com.study.moim.dto;

import lombok.Data;

@Data
public class AdminDto {
	
	private String id;
	private String pwd;
	private String name;
	
	public AdminDto() {}
	public AdminDto(String id, String pwd, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	
}
