package com.ezen.m16.comtroller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.m16.dto.MemberVO;
import com.ezen.m16.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(
			@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result,
			HttpServletRequest request,
			Model model
	) {
		String url="member/login.jsp";
		
		if( result.getFieldError("id") != null )
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage() );
		else if( result.getFieldError("pwd") != null )
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", membervo.getId() );
			paramMap.put("ref_cursor", null);
			ms.getMember(paramMap);
			
			ArrayList< HashMap<String,Object> > list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if( list.size() == 0 ) {
				model.addAttribute("message" , "아이디가 없습니다");
				return "member/login";
			}
			
			HashMap<String, Object> mvo = list.get(0);
			
			if( mvo.get("PWD") == null )
				model.addAttribute("message" , "관리자에게 문의하세요");
			else if( !mvo.get("PWD").equals( membervo.getPwd() ) )
				model.addAttribute("message" , "비번이 안맞습니다");
			else  if( mvo.get("PWD").equals( membervo.getPwd() ) ) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				url = "redirect:/";
			}
		}
		return url;
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/contract")
	public String contract() {
		return "member/contract";
	}
	
	
	
	@RequestMapping(value="/joinForm", method=RequestMethod.POST)
	public String join_form() {
		return "member/joinForm";
	}
	
	
	
	@RequestMapping("/idCheckForm")
	public String id_check_form( 
			@RequestParam("id") String id,
			Model model, 
			HttpServletRequest request ) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("ref_cursor", null);
		
		ms.getMember(paramMap);
		
		ArrayList<HashMap<String, Object>> list 
			=(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		
		if( list.size() == 0) model.addAttribute("result", -1);
		else model.addAttribute("result", 1);
		
		model.addAttribute("id" , id);
		
		return "member/idcheck";
	}
	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(
			@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result,
			@RequestParam(value="reid", required=false) String reid,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck,
			HttpServletRequest request,
			Model model
	) {
		String url = "member/login";
		
		if( result.getFieldError("id")!=null)
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage() );
		else if( result.getFieldError("pwd")!=null)
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else if( result.getFieldError("name")!=null)
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage() );
		else if( result.getFieldError("email")!=null)
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage() );
		else if( result.getFieldError("phone")!=null)
			model.addAttribute("message", result.getFieldError("phone").getDefaultMessage() );
		else if( reid == null || (   reid != null && !reid.equals(membervo.getId() ) ) )
				model.addAttribute("message", "아이디 중복체크를 하지 않으셨습니다");
		else if( pwdCheck == null || (  pwdCheck != null && !pwdCheck.equals(membervo.getPwd() ) ) ) 
			model.addAttribute("message", "비밀번호 확인 일치하지 않습니다");
		else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			paramMap.put("id", membervo.getId() );
			paramMap.put("pwd", membervo.getPwd() );
			paramMap.put("name", membervo.getName() );
			paramMap.put("email", membervo.getEmail() );
			paramMap.put("phone", membervo.getPhone() );
			paramMap.put("zip_num", membervo.getZip_num() );
			paramMap.put("address1", membervo.getAddress1() );
			paramMap.put("address2", membervo.getAddress2() );
			paramMap.put("address3", membervo.getAddress3() );
			
			ms.insertMember( paramMap );
			
			model.addAttribute("message", "회원가입이 완료되었어요. 로그인하세요");
		}
		
		return url;
	}
	
	
	@RequestMapping(value = "/memberEditForm")
	public String memberEditForm( HttpServletRequest request, Model model ) {
		
		MemberVO dto = new MemberVO();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		
		dto.setId( (String)loginUser.get("ID") );
		dto.setName( (String)loginUser.get("NAME") );
		dto.setEmail( (String)loginUser.get("EMAIL") );
		dto.setPhone( (String)loginUser.get("PHONE") );
		dto.setZip_num( (String)loginUser.get("ZIP_NUM") );
		dto.setAddress1( (String)loginUser.get("ADDRESS1") );
		dto.setAddress2( (String)loginUser.get("ADDRESS2") );
		dto.setAddress3( (String)loginUser.get("ADDRESS3") );
		
		model.addAttribute("dto" , dto);
		
		return "member/memberUpdateForm";
	}
	
	@RequestMapping(value = "/memberUpdate", method=RequestMethod.POST)
	public String memberUpdate( 
			@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck,
			HttpServletRequest request,
			Model model 
	) {
		String url = "member/memberUpdateForm";
		
		if( result.getFieldError("pwd") != null )
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else if( result.getFieldError("name") != null )
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage() );
		else if( result.getFieldError("email") != null )
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage() );
		else if( result.getFieldError("phone") != null )
			model.addAttribute("message", result.getFieldError("phone").getDefaultMessage() );
		else if( pwdCheck == null || (  pwdCheck != null && !pwdCheck.equals(membervo.getPwd() ) ) ) 
			model.addAttribute("message", "비밀번호 확인 일치하지 않습니다");
		else {
			// paramMap 에 membervo안의 내용을 옮기고 회원을 수정하세요. 수정된 내용을 세션에도 적용해주세요
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			paramMap.put("ID", membervo.getId() );
			paramMap.put("PWD", membervo.getPwd() );
			paramMap.put("NAME", membervo.getName() );
			paramMap.put("EMAIL", membervo.getEmail() );
			paramMap.put("PHONE", membervo.getPhone() );
			paramMap.put("ZIP_NUM", membervo.getZip_num() );
			paramMap.put("ADDRESS1", membervo.getAddress1() );
			paramMap.put("ADDRESS2", membervo.getAddress2() );
			paramMap.put("ADDRESS3", membervo.getAddress3() );
			
			ms.updateMember( paramMap );
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", paramMap);
			
			url = "redirect:/";
		}
		return url;
	}
}
























