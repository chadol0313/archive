package com.ezen.m16.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOrderDao {

	public void insertOrder(HashMap<String, Object> paramMap);
	public void listOrderByOseq(HashMap<String, Object> paramMap);
	public void insertOrderOne(HashMap<String, Object> paramMap);
	public void listOrderByIdIng(HashMap<String, Object> paramMap);
	public void listOrderByIdAll(HashMap<String, Object> paramMap);

}
