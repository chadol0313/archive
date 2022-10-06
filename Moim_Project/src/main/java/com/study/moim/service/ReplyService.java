package com.study.moim.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.moim.dao.IReplyDao;
import com.study.moim.dto.BoardDto;
import com.study.moim.dto.Paging;
import com.study.moim.dto.ReplyDto;

@Service
public class ReplyService {
	
	@Autowired
	IReplyDao rdao;

	//댓글 작성
	public void addReply(ReplyDto rdto) {
		rdao.addReply(rdto);
	}
	
	
	//게시물 조회수 감소
	public void minusCount(int b_num) {
		rdao.minusCount(b_num);
	}

	
	//게시물 삭제
	public void deleteReply(int r_num, int b_num) {
		rdao.deleteReply(r_num,b_num);
	}

	
	//나의 댓글 리스트
	public HashMap<String, Object> myReplyList(int rpage, String r_id) {
		HashMap<String, Object> reply = new HashMap<String, Object>();
		
		Paging paging2 = new Paging();
		paging2.setPage(rpage);
		int rcount = rdao.getMyReplyCount(r_id);
		paging2.setTotalCount(rcount);
		paging2.paging();
		
		List<ReplyDto> rlist = 
				rdao.getMyReplyList(paging2.getStartNum(),paging2.getEndNum(),r_id);
		
		reply.put("paging2",paging2);
		reply.put("myReplyList", rlist);
		
		return reply;
	}



}
