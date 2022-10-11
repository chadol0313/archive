package com.ezen.m16.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.m16.dao.IOrderDao;

@Service
public class OrderService {

	@Autowired
	IOrderDao odao;

	public void insertOrder(HashMap<String, Object> paramMap) {
		odao.insertOrder(paramMap);		
	}

	public void listOrderByOseq(HashMap<String, Object> paramMap) {
		odao.listOrderByOseq( paramMap );		
	}

	public void insertOrderOne(HashMap<String, Object> paramMap) {
		odao.insertOrderOne( paramMap );		
	}

	public void myPageList(HashMap<String, Object> paramMap) {
		
		// 최종 mypage.jsp 에 전달될 리스트
		ArrayList<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
					
		paramMap.put("ref_cursor", null);
		
		// 전달된 아이디로 진행중인 주문 번호들을 조회합니다.
		odao.listOrderByIdIng(paramMap);
		ArrayList<HashMap<String, Object>> oseqList 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		// 주문번호별 주문내역을 조회
		for( HashMap<String, Object> result : oseqList ) {
			int oseq = Integer.parseInt( result.get("OSEQ").toString() );
			paramMap.put("oseq", oseq);
			paramMap.put("ref_cursor", null);
			odao.listOrderByOseq( paramMap );			
			ArrayList<HashMap<String, Object>> orderListByOseq 
				= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			HashMap<String, Object> orderFirst = orderListByOseq.get(0);  // 주문 상품들중 첫번째 주문 추출
			orderFirst.put("PNAME" , (String)orderFirst.get("PNAME") + "포함 " + orderListByOseq.size() + "건" );
			// 추출한 첫번째 상품의 상품명을   "XXX 포함 X건" 이라고 수정
			
			int totalPrice = 0;
			for( HashMap<String, Object> order : orderListByOseq ) {
				totalPrice += Integer.parseInt( order.get("QUANTITY").toString() )
									* Integer.parseInt( order.get("PRICE2").toString() ); 
			}
			orderFirst.put("PRICE2", totalPrice);    // 추출한 첫번째 상품의 가격을 총가격으로 수정
			finalList.add(orderFirst);  // 최종 리스트에 추가
		}
		paramMap.put("finalList", finalList);  // 최종 리스트를 paramMap 에 추가
	}

	public void orderAllList(HashMap<String, Object> paramMap) {
		
		ArrayList<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
		paramMap.put("ref_cursor", null);
		odao.listOrderByIdAll(paramMap);
		ArrayList<HashMap<String, Object>> oseqList 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		for( HashMap<String, Object> result : oseqList ) {
			int oseq = Integer.parseInt( result.get("OSEQ").toString() );
			paramMap.put("oseq", oseq);
			paramMap.put("ref_cursor", null);
			odao.listOrderByOseq( paramMap );			
			ArrayList<HashMap<String, Object>> orderListByOseq 
				= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
			HashMap<String, Object> orderFirst = orderListByOseq.get(0);  // 주문 상품들중 첫번째 주문 추출
			orderFirst.put("PNAME" , (String)orderFirst.get("PNAME") + "포함 " + orderListByOseq.size() + "건" );
			int totalPrice = 0;
			for( HashMap<String, Object> order : orderListByOseq ) {
				totalPrice += Integer.parseInt( order.get("QUANTITY").toString() )
									* Integer.parseInt( order.get("PRICE2").toString() ); 
			}
			orderFirst.put("PRICE2", totalPrice);   
			finalList.add(orderFirst); 
		}
		paramMap.put("finalList", finalList);  		
	}
}


















