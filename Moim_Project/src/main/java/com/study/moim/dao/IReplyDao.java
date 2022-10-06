package com.study.moim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.moim.dto.ReplyDto;

@Mapper
public interface IReplyDao {
	
	void addReply(ReplyDto rdto);	//댓글추가

	void minusCount(int b_num);	//게시물 조회수 감소

	void deleteReply(int r_num, int b_num);	//댓글삭제

	int getMyReplyCount(String r_id);	//내가 작성한 댓글 갯수

	List<ReplyDto> getMyReplyList(int startNum, int endNum, String r_id);	//내가 작성한 댓글 리스트

}
