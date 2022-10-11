package com.ezen.m16.comtroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.m16.dto.Paging;
import com.ezen.m16.service.AdminService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminController {

	@Autowired
	AdminService as;
	
	@RequestMapping(value="/admin")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin( HttpServletRequest request, Model model, 
			@RequestParam("workId") String workId, 
			@RequestParam("workPwd") String workPwd) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		paramMap.put("workId", workId);
		as.getAdmin(paramMap);	 // 아이디로 관리자 조회 
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		String url = "admin/adminLoginForm";
		if(list.size() == 0) {  // 입력한 아이디 없다면
			model.addAttribute("message" , "아이디가 없어요");
			return "admin/adminLoginForm";
		}
		HashMap<String, Object> resultMap = list.get(0); 
		if(resultMap.get("PWD")==null) 
			model.addAttribute("message" , "관리자에게 문의하세요");
		else if( workPwd.equals( (String)resultMap.get("PWD") ) ) { 
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", resultMap);
			url = "redirect:/productList";
		}else 
			model.addAttribute("message" , "비번이 안맞아요");
		return url;
	}
	
	
	@RequestMapping(value="/productList")
	public ModelAndView product_list(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			
			int page = 1;
			if(request.getParameter("page"!=null)) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page")!=null) {
				page=(Integer)session.getAttribute("page");
			}else {
				session.removeAttribute("page");
			}
			
			
			String key="";
			if(request.getParameter("key") !=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else if(session.getAttribute("key")!=null) {
				key = (String)session.getAttribute("key");
			}else {
				session.removeAttribute("key");
			}
			
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("key",key);
			paramMap.put( "ref_cursor", null );
			
			as.getProductList( paramMap, page ,key);
			ArrayList< HashMap<String,Object> > list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("paging",(Paging)paramMap.get("paging"));
			mav.addObject("key",key);
			mav.addObject("productList", list);
			mav.setViewName("admin/product/productList");
		}
		return mav;
	}
	
	@RequestMapping(value="/productWirteForm")
	public String product_write_form(HttpServletRequest request, Model model) {
		String kindList[] = {"Heels","Boots","Sandals","Shcakers","Slipers","Sale"	};
		model.addAttribute("kindList", kindList);
		return "admin/product/productWirteFomr";
	}
	
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup")
	@ResponseBody
	public HashMap<String,Object>fileup(Model model, HttpServletRequest request)  {
		HashMap<String,Object> result = new HashMap<String,Object>();
		String path = context.getRealPath("/product_images");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			result.put("STATUS", 1);
			result.put("FILENAME", multi.getFilesystemName("fileimage"));
		} catch (IOException e) {e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value="productWrite", method= RequestMethod.POST)
	public String productWrite(Model model, HttpServletRequest request)  {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("name", request.getParameter("name"));
		paramMap.put("kind", request.getParameter("kind"));
		paramMap.put("price1", Integer.parseInt(request.getParameter("price1")));
		paramMap.put("price2", Integer.parseInt(request.getParameter("price2")));
		paramMap.put("price3", Integer.parseInt(request.getParameter("price3")));
		paramMap.put("content", request.getParameter("cotent"));
		paramMap.put("image", request.getParameter("image"));
		as.insertProduct(paramMap);
		
		return "redirect:/productList";
	}
	
	
	@RequestMapping(value="/adminBannerList")
	public ModelAndView bannerList(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("admin/banner/bannerList");
		return mav;
	}
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
