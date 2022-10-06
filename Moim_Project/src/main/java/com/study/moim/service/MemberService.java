package com.study.moim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.moim.dao.IMemberDao;
import com.study.moim.dto.MemberDto;

@Service
public class MemberService {
	
	@Autowired
	IMemberDao mdao;

	//회원 조회
	public MemberDto getMember(String id) {
		return mdao.getMember(id);
	}
	
	//회원가입
	public int insertMember(MemberDto mdto) {
		return mdao.insertMember(mdto);
	}
	
	//회원정보 수정
	public int updateMember(MemberDto mdto) {
		return mdao.updateMember(mdto);
	}

	

	

}
