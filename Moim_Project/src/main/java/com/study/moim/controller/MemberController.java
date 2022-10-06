package com.study.moim.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.moim.dto.BoardDto;
import com.study.moim.dto.MemberDto;
import com.study.moim.dto.Paging;
import com.study.moim.dto.ReplyDto;
import com.study.moim.service.BoardService;
import com.study.moim.service.MemberService;
import com.study.moim.service.ReplyService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	@Autowired
	BoardService bs;
	@Autowired
	ReplyService rs;
	
	
	//로그인 페이지
	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		return "index";
	}

	
	//로그인
	@RequestMapping("/main")
	public String login(Model model, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDto mdto = ms.getMember(id);
		
		String url = "index";
		
		if(mdto == null)
			model.addAttribute("message","ID가 없습니다");
		else if(mdto.getPwd()==null)
			model.addAttribute("message", "관리자에게 문의하세요");
		else if(!mdto.getPwd().equals(pwd))
			model.addAttribute("message", "비밀번호가 일치하지 않습니다");
		else if(mdto.getPwd().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
			
			url = "redirect:/studyBoard";
		}
		return url;
	}
	
	
	//회원가입 페이지
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	
	//아이디 중복 체크
	@RequestMapping("/idCheckForm")
	public String idCheckForm(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		MemberDto mdto = ms.getMember(id);
		
		if(mdto==null) model.addAttribute("result",-1);
		else model.addAttribute("result",1);
		model.addAttribute("id",id);
		
		return "member/idcheck";
	}
	
	
	//회원가입
	@RequestMapping("/join")
	public String join(Model model, HttpServletRequest request) {
		MemberDto mdto = new MemberDto();
		mdto.setId(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setName(request.getParameter("name"));
		mdto.setEmail(request.getParameter("email"));
		int result = ms.insertMember(mdto);
		
		if(result== 1) model.addAttribute("message","회원가입 완료. 로그인하세요");
		else model.addAttribute("message" , "회원가입 실패. 관리자에게 문의하세요");
		
		return "index";
	}
	
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return"redirect:/";
	}
	
	
	//마이페이지
	@RequestMapping("/myPage")
	public String myPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		
		String id = mdto.getId();
		String r_id = mdto.getId();
		
		mdto = ms.getMember(id);
		
		//내가 작성한 게시글 페이지
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page",page);
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		//내가 작성한 댓글 페이지
		int rpage = 1;
		if(request.getParameter("rpage")!=null) {
			rpage = Integer.parseInt(request.getParameter("rpage"));
			session.setAttribute("rpage",rpage);
		}else if(session.getAttribute("rpage")!=null) {
			rpage = (Integer)session.getAttribute("rpage");
		}else {
			session.removeAttribute("rpage");
		}
		
		
		HashMap<String,Object> result = bs.myBoardList(page,id);
		HashMap<String,Object> reply = rs.myReplyList(rpage,r_id);
		
		model.addAttribute("myList", (List<BoardDto>)result.get("myBoardList"));
		model.addAttribute("paging", (Paging)result.get("paging") );
		
		model.addAttribute("rList",(List<ReplyDto>)reply.get("myReplyList"));
		model.addAttribute("paging2", (Paging)reply.get("paging2") );
		
		model.addAttribute("loginUser", mdto);
		
		return "member/myPage";
	}
	
	
	//회원정보 수정 페이지
	@RequestMapping("/updateMemberForm")
	public String updateMemberForm(Model model, HttpServletRequest request) {
		return "member/updateMemberForm";
	}
	
	
	//회원정보 수정
	@RequestMapping("/updateMember")
	public String updateMember(Model model, HttpServletRequest request) {
		MemberDto mdto = new MemberDto();
		mdto.setId(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setName(request.getParameter("name"));
		mdto.setEmail(request.getParameter("email"));
		
		int result = ms.updateMember(mdto);
		
		HttpSession session = request.getSession();
		if(result == 1) {
			session.setAttribute("loginUser", mdto);
			model.addAttribute("message","회원정보 수정이 완료되었습니다");
		}else model.addAttribute("message", "회원정보 수정에 실패하였습니다");
		
		
		return "member/updateMemberForm";
	}

}
