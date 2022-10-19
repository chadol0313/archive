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
import com.study.moim.dto.Paging;
import com.study.moim.dto.ReplyDto;
import com.study.moim.service.BoardService;
import com.study.moim.service.MemberService;
import com.study.moim.service.ReplyService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bs;
	
	@Autowired
	MemberService ms;
	
	@Autowired
	ReplyService rs;
	
	
	
	//(메인)스터디 게시글 목록
	@RequestMapping("/studyBoard")
	public String studyBoard(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) return "index";
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page",page);
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		HashMap<String,Object> result = bs.getBoardList(page);
		

				
		model.addAttribute("boardList", (List<BoardDto>)result.get("boardList"));
		model.addAttribute("paging", (Paging)result.get("paging") );
		model.addAttribute("loginUser", session);
		return"board/studyBoard";
	}
	
	
	
	//게시글 상세페이지
	@RequestMapping("/boardView")
	public String boardView(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	return "index";
		
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		
		//조회수 증가
		bs.plusCount(b_num);
		
		BoardDto bdto = bs.getBoardView(b_num);
		List<ReplyDto> list = bs.getReplyList(b_num);
		
		
		model.addAttribute("board", bdto);
		model.addAttribute("replyList", list);
		
		return "board/boardView";
	}
	
	
	//게시글 작성폼
	@RequestMapping("/boardWriteForm")
	public String boardWriteForm(Model model, HttpServletRequest request) {
		String url = "board/boardWriteForm";
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	return "index";
		
		return url;
	}
	
	
	//게시글 작성
	@RequestMapping("/boardWrite")
	public String boardWrite (Model model, HttpServletRequest request) {
		String url = "board/boardSuccess";
		BoardDto bdto = new BoardDto();
		
		bdto.setId(request.getParameter("id"));
		bdto.setPwd(request.getParameter("pwd"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setRegion(Integer.parseInt(request.getParameter("region")));
		bdto.setTopic(Integer.parseInt(request.getParameter("topic")));
		bdto.setTitle(request.getParameter("title"));
		bdto.setB_content(request.getParameter("b_content"));
		
		int result = bs.insertBoard(bdto);
		if(result != 1) url = "board/boardFail";
		
		return url;
	}
	
	
	//게시글 비밀번호 확인
	@RequestMapping("/identifyPwd")
	public String identifyPwd(Model model, HttpServletRequest request) {
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pwd = request.getParameter("pwd");
		
		model.addAttribute("b_num",b_num);
				
		return "board/identifyPwd";
	}
	
	
	
	//게시글 수정페이지
	@RequestMapping("/boardEditForm")
	public String boardEditForm(Model model, HttpServletRequest request) {
		//입력한 비밀번호와 원래 비밀번호가 다른 경우
		String url = "board/boardEditForm";;
		
		String pwd = request.getParameter("pwd");
		
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		BoardDto bdto = bs.getBoardView(b_num);
		model.addAttribute("b_num", b_num);
		
		if(pwd.equals(bdto.getPwd())) {
			model.addAttribute("bdto", bdto);
		}else {
			model.addAttribute("message", "비밀번호가 틀립니다");
			url = "board/identifyPwd";
		}
		return url;
	}
	
	
	//게시글 수정 업로드
	@RequestMapping("/boardEdit")
	public String boardEdit(Model model,HttpServletRequest request) {
		String url = "board/boardSuccess";
		
		BoardDto bdto = new BoardDto();
		bdto.setB_num(Integer.parseInt(request.getParameter("b_num")));
		bdto.setPwd(request.getParameter("pwd"));
		bdto.setRegion(Integer.parseInt(request.getParameter("region")));
		bdto.setTopic(Integer.parseInt(request.getParameter("topic")));
		bdto.setTitle(request.getParameter("title"));
		bdto.setB_content(request.getParameter("b_content"));
		bdto.setState(request.getParameter("state"));
		
		int result = bs.updateBoard(bdto);
		
		if(result!=1) url="board/boardFail";
		else url ="board/boardSuccess";
		
		return url;
	}
	
	
	
	//게시글 삭제
	@RequestMapping("/boardDelete")
	public String boardDelete(Model model, HttpServletRequest request) {
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		
		bs.deleteBoard(b_num);
		
		return "board/boardSuccess";
	}
	
	
	
	//게시글 검색
	@RequestMapping("/searchPage")
	public String searchPage(Model model, HttpServletRequest request) {
		
		
		int region = Integer.parseInt(request.getParameter("region"));
		int topic = Integer.parseInt(request.getParameter("topic"));
		String title = request.getParameter("keyword");
		
		
		HttpSession session = request.getSession();
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page",page);
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		HashMap<String,Object> result = bs.searchList(page,region,topic,title);
		
		
		model.addAttribute("searchList", (List<BoardDto>)result.get("searchList"));
		model.addAttribute("paging", (Paging)result.get("paging") );
		model.addAttribute("loginUser", session);
		model.addAttribute("keyword", title);
		model.addAttribute("region", region);
		model.addAttribute("topic", topic);
		
		
		return "board/searchPage";
	}
	

}
