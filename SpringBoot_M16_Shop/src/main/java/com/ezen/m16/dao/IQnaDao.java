package com.ezen.m16.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IQnaDao {

	public void getAllCount(HashMap<String, Object> paramMap);
	public void listQna(HashMap<String, Object> paramMap);
	public void insertQna(HashMap<String, Object> paramMap);
	public void getQna(HashMap<String, Object> paramMap);

}
