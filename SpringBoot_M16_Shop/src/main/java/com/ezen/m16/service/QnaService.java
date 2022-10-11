package com.ezen.m16.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.m16.dao.IQnaDao;
import com.ezen.m16.dto.Paging;

@Service
public class QnaService {

	@Autowired
	IQnaDao qdao;

	public void listQna(HashMap<String, Object> paramMap, int page) {
		
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setDisplayPage(5);
		paging.setDisplayRow(5);
		paramMap.put("cnt", 0);
		qdao.getAllCount(paramMap);
		int count = Integer.parseInt( paramMap.get("cnt").toString());
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum", paging.getStartNum() );
		paramMap.put("endNum", paging.getEndNum() );
		paramMap.put("paging", paging);
		
		qdao.listQna( paramMap );
		
	}

	public void insertQna(HashMap<String, Object> paramMap) {
		qdao.insertQna(paramMap);		
	}

	public void getQna(HashMap<String, Object> paramMap) {
		qdao.getQna( paramMap );		
	}
}
