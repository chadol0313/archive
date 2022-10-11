package com.ezen.m16.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IProductDao {

	public void getBestNewProduct(HashMap<String, Object> paramMap);
	public void getKindList(HashMap<String, Object> paramMap);
	public void getProduct(HashMap<String, Object> paramMap);

}
