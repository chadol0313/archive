package com.study.moim.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.moim.dao.IBoardDao;
import com.study.moim.dto.BoardDto;
import com.study.moim.dto.Paging;
import com.study.moim.dto.ReplyDto;

@Service
public class BoardService {
	
	@Autowired
	IBoardDao bdao;
	
	//게시물 목록
	public HashMap<String, Object> getBoardList(int page) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//1.페이징 객체 처리
		Paging paging = new Paging();
		paging.setPage(page);
		int count = bdao.getAllCount(); 	//총 게시물 갯수 구하기
		paging.setTotalCount(count);
		paging.paging();
		
		//2. 계산된 Paging 객체에 의한 게시물 조회
		List<BoardDto> list = bdao.getBoardList(paging);
		
		
		result.put("paging", paging);
		result.put("boardList", list);
		
		return result;
	}
	
	
	//게시물 상세
	public BoardDto getBoardView(int b_num) {
		return bdao.getBoardView(b_num);
	}

	//게시물 작성
	public int insertBoard(BoardDto bdto) {
		return bdao.insertBoard(bdto);
	}

	//게시물 수정
	public int updateBoard(BoardDto bdto) {
		return bdao.updateBoard(bdto);
	}
	
	
	//게시물 삭제
	public void deleteBoard(int b_num) {
		bdao.deleteBoard(b_num);
	}

	//내가 작성한 게시물
	public HashMap<String, Object> myBoardList(int page, String id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//1.페이징 객체 처리
		Paging paging = new Paging();
		paging.setPage(page);
		int count = bdao.getMyCount(id); 	//총 게시물 갯수 구하기
		paging.setTotalCount(count);
		paging.paging();
				
		//2. 계산된 Paging 객체에 의한 게시물 조회
		List<BoardDto> list = 
				bdao.getMyBoardList(paging.getStartNum(),paging.getEndNum(),id);
		
		//3. 페이징, 리스트 등을 해쉬맵에 저장
		result.put("paging", paging);
		result.put("myBoardList", list);
		
		return result;
	}

	
	//조회수 증가
	public void plusCount(int b_num) {
		bdao.plusCount(b_num);
		
	}
	
	
	//게시물 검색
	public HashMap<String, Object> searchList(int page, int region, int topic, String title) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		Paging paging = new Paging();
		paging.setPage(page);
		int count = bdao.getCount(region,topic,title); 	//검색에 걸린 총 게시물 갯수
		paging.setTotalCount(count);
		paging.paging();
		
		//페이지 시작번호, 끝번호, 위치, 주제, 검색어
		List<BoardDto> list = 
				bdao.searchList(paging.getStartNum(),paging.getEndNum(),region,topic,title);
		
		result.put("paging", paging);
		result.put("searchList", list);
		
		return result;
	}
	
	//댓글 목록
	public List<ReplyDto> getReplyList(int b_num) {
		return bdao.getReplyList(b_num);
	}
	
	//댓글 갯수 업데이트
	public void updateReplyCount(int b_num) {
		bdao.updateReplyCount(b_num);
		
	}

	

	




	

	

}
