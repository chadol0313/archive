package com.ezen.m16.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.m16.dao.IAdminDao;
import com.ezen.m16.dto.Paging;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public void getAdmin(HashMap<String, Object> paramMap) {
		adao.getAdmin(paramMap);		
	}

	public void getProductList(HashMap<String, Object> paramMap, int page, String key) {
		Paging paging = new Paging();
		paging.setPage(page);
		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 0);
		cntMap.put("key", key);
		cntMap.put("tableName", "product");

		adao.adminGetAllCount(cntMap);
		int count = Integer.parseInt(cntMap.get("cnt").toString());
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum",paging.getStartNum() );
		paramMap.put("endNum", paging.getEndNum());
		
		adao.getProductList(paramMap);
		paramMap.put("paging", paging);
		
	}

	public void insertProduct(HashMap<String, Object> paramMap) {
		adao.insertProduct(paramMap);
		
	}
	
}
