package com.study.moim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.moim.dto.BoardDto;
import com.study.moim.dto.Paging;
import com.study.moim.dto.ReplyDto;

@Mapper
public interface IBoardDao {
	
	List<BoardDto> getBoardList(Paging paging);	//게시물 리스트

	int getAllCount();	//총 게시물 갯수
	
	BoardDto getBoardView(int b_num);	//게시물 상세조회
	
	int insertBoard(BoardDto bdto);	//게시물 작성	

	int updateBoard(BoardDto bdto);	//게시물 수정

	void deleteBoard(int b_num);	//게시물 삭제

	int plusCount(int b_num);	//게시물 조회수 증가

	int getCount(@Param("region")int region, @Param("topic")int topic, @Param("title")String title);	//검색결과 게시물 갯수

	List<BoardDto> searchList(
			@Param("startNum")int startNum, @Param("endNum")int endNum, 
			@Param("region")int region, @Param("topic")int topic, @Param("title")String title);	//검색결과

	int getMyCount(String id);	//내가 쓴 게시물 갯수

	List<BoardDto> getMyBoardList(@Param("startNum")int startNum, @Param("endNum")int endNum, @Param("id")String id);	//내가 쓴 게시물

	List<ReplyDto> getReplyList(int b_num);	//댓글리스트

	void updateReplyCount(int b_num);	//댓글 수정
	
	




	


	

}
