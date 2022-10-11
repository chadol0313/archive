package com.ezen.m16.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminDao {

	public void getAdmin(HashMap<String, Object> paramMap);
	public void getProductList(HashMap<String, Object> paramMap);
	public void adminGetAllCount(HashMap<String, Object> cntMap);
	public void insertProduct(HashMap<String, Object> paramMap);

}
