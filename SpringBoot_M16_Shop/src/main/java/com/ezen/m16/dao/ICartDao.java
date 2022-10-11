package com.ezen.m16.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICartDao {

	public void insertCart(HashMap<String, Object> paramMap);
	public void listCart(HashMap<String, Object> paramMap);
	public void deleteCart(HashMap<String, Object> paramMap);
	
}
