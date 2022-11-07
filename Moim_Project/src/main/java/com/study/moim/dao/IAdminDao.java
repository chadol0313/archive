package com.study.moim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.moim.dto.AdminDto;
import com.study.moim.dto.MemberDto;
import com.study.moim.dto.Paging;

@Mapper
public interface IAdminDao {

	AdminDto getAdmin(String id);

	int getAllCount();	//총 회원수

	List<MemberDto> getMemberList(Paging paging);	//회원리스트

	void deleteMember(String string);
	

	

	
}
