package com.study.moim.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.moim.dao.IAdminDao;
import com.study.moim.dto.AdminDto;
import com.study.moim.dto.MemberDto;
import com.study.moim.dto.Paging;

@Service
public class AdminService {
	
	@Autowired
	IAdminDao adao;

	public AdminDto getAdmin(String id) {
		return adao.getAdmin(id);
	}

	public HashMap<String, Object> getMemberList(int page) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//1.페이징 객체 처리
		Paging paging = new Paging();
		paging.setPage(page);
		int count = adao.getAllCount(); //총 멤버 수 구하기
		paging.setTotalCount(count);
		paging.paging();
		
		//2. 계산된 Paging 객체에 의한 멤버 조회
		List<MemberDto> list = adao.getMemberList(paging);
		
		result.put("paging",paging);
		result.put("memberList", list);
		
		return result;
	}

	public void deleteMember(String string) {
		adao.deleteMember(string);
		
	}

	

}
