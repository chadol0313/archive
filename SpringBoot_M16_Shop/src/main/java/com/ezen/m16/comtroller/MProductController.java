package com.ezen.m16.comtroller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.m16.service.ProductService;

@Controller
public class MProductController {
	
	@Autowired
	ProductService ps;
	
	@RequestMapping("/webmain")
	publicString webmain(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		ps.getBestNewProduct(paramMap);
		
		ArrayList<HashMap<String,Object>> list1
		=(ArrayList<HashMap<String,Object>>) paramMap.get("ref_cursor1");
		ArrayList<HashMap<String,Object>> list2
		=(ArrayList<HashMap<String,Object>>) paramMap.get("ref_cursor2");
		mav.addObject("newProductList1");
		mav.addObject("newProductList2");
		mav.setviewName("mobile/main");
		return mav
	}

}
