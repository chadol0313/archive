package com.study.moim.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.moim.dto.ReplyDto;
import com.study.moim.service.BoardService;
import com.study.moim.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService rs;
	
	@Autowired
	BoardService bs;
	
		//댓글 작성
		@RequestMapping("/addReply")
		public String addReply (Model model, HttpServletRequest request) {
			
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			ReplyDto rdto = new ReplyDto();
			
			rdto.setB_num(b_num);
			rdto.setR_content(request.getParameter("r_content"));
			rdto.setR_id(request.getParameter("id"));
			
			//댓글 추가
			rs.addReply(rdto);
			
			//조회수 증가X
			rs.minusCount(b_num);
			
			//댓글 갯수 업데이트
			bs.updateReplyCount(b_num);
			
			return "redirect:/boardView?b_num="+b_num;
		}
		
		
		
		//댓글 삭제
		@RequestMapping("/deleteReply")
		public String deleteReply(Model model, HttpServletRequest request) {
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			int r_num = Integer.parseInt(request.getParameter("r_num"));
			
			rs.deleteReply(r_num,b_num);
			rs.minusCount(b_num);
			bs.updateReplyCount(b_num);
			
			return "redirect:/boardView?b_num="+b_num;
		}

}
