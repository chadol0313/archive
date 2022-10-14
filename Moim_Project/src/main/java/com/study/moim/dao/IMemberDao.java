package com.study.moim.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.moim.dto.MemberDto;

@Mapper
public interface IMemberDao {

	MemberDto getMember(String id);	//회원조회

	int insertMember(MemberDto mdto);	//회원가입		

	int updateMember(MemberDto mdto);	//회원수정

	MemberDto findMember(String id, String name);	//계정찾기

	int updatePW(MemberDto mdto);	//비밀번호 수정

}
