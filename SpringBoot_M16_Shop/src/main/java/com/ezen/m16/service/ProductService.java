package com.ezen.m16.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.m16.dao.IProductDao;

@Service
public class ProductService {

	@Autowired
	IProductDao pdao;

	public void getBestNewProduct(HashMap<String, Object> paramMap) {
		pdao.getBestNewProduct( paramMap );		
	}

	public void getKindList(HashMap<String, Object> paramMap) {
		pdao.getKindList(paramMap);		
	}

	public void getProduct(HashMap<String, Object> paramMap) {
		pdao.getProduct( paramMap );
	}
	
	
	
}
