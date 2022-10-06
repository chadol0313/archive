package com.study.moim.dto;

import lombok.Data;

@Data
public class MemberDto {
	private String id;
	private String pwd;
	private String name;
	private String email;
	
	
	public MemberDto() {}
	public MemberDto(String id, String pwd, String name, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	

}
