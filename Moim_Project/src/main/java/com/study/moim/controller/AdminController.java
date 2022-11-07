package com.study.moim.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.moim.dto.AdminDto;
import com.study.moim.dto.MemberDto;
import com.study.moim.dto.Paging;
import com.study.moim.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	
	@RequestMapping("/adminLoginForm")
	public String adminLoginForm() {
		return "admin/adminLoginForm";
	}
	
	@RequestMapping("/adminMain")
	public String adminMain(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		AdminDto adto = as.getAdmin(id);
		
		String url ="admin/adminLoginForm";
		
		if(adto==null)
			model.addAttribute("message","관리자 계정이 없습니다");
		else if(!adto.getPwd().equals(pwd))
			model.addAttribute("message","비밀번호가 틀렸습니다");
		else if(adto.getPwd().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("adminUser", adto);
			
			url = "admin/adminMain";
			}
		return url;
	}
	
	@RequestMapping("/admin_Member")
	public String admin_Member(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminUser")==null) return "index";
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page",page);
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		HashMap<String,Object> result = as.getMemberList(page);
		model.addAttribute("memberList",(List<MemberDto>)result.get("memberList"));
		model.addAttribute("paging", (Paging)result.get("paging") );
		model.addAttribute("adminUser", session);
		
		return"admin/admin_Member";
	}
	
	
	@RequestMapping("/withdrawal")
	public String withdrawal(HttpServletRequest request, Model model) {
		
		String[]checkBox = request.getParameterValues("select");
		
		for(int i=0; i<checkBox.length; i++) {
			as.deleteMember(checkBox[i]);
		}
		model.addAttribute("message", "해당 회원이 탈퇴처리되었습니다");
		
		
		HttpSession session = request.getSession();
		if(session.getAttribute("adminUser")==null) return "index";
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page",page);
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		HashMap<String,Object> result = as.getMemberList(page);
		model.addAttribute("memberList",(List<MemberDto>)result.get("memberList"));
		model.addAttribute("paging", (Paging)result.get("paging") );
		model.addAttribute("adminUser", session);
		
		return "admin/admin_Member";
	}
	

}
