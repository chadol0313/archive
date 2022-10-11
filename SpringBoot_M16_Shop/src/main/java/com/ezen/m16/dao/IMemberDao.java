package com.ezen.m16.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {

	public void getMember(HashMap<String, Object> paramMap);
	public void insertMember(HashMap<String, Object> paramMap);
	public void updateMember(HashMap<String, Object> paramMap);

	
}
