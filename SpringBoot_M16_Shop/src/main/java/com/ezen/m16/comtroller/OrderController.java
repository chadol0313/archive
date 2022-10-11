package com.ezen.m16.comtroller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.m16.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService os;
	
	@RequestMapping(value="/orderInsert")
	public String orderInsert( HttpServletRequest request ) {
		String oseq = "";
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			return "member/login";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID") );
			paramMap.put("oseq", 0 );
			
			os.insertOrder( paramMap );
			// 아이디로 카트검색
			// 검색내용으로 orders 와 order_detail 테이블에 레코드 추가
			// 카트 테이블 result를 2로 또는 레코드 삭제 처리
			// oseq 에 주문번호를 넣어 갖고 되돌아옵니다
			
			oseq = paramMap.get("oseq").toString();
		}
		
		return "redirect:/orderList?oseq="+oseq;
	}
	
	
	@RequestMapping(value="/orderList")
	public ModelAndView orderList( 
			HttpServletRequest request, 
			Model model,
			@RequestParam("oseq") int oseq) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {	
			
			// oseq 로 주문 리스트를 조회하고, 총금액을 계산해주세요
			HashMap<String , Object> paramMap = new HashMap<String , Object>();
			paramMap.put("oseq", oseq);
			paramMap.put("ref_cursor", null);
			
			os.listOrderByOseq( paramMap );
			
			ArrayList<HashMap<String, Object>> list 
				= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			int totalPrice = 0;
			for( HashMap<String, Object> ovo : list ) {
				totalPrice += Integer.parseInt( ovo.get("QUANTITY").toString() )
										* Integer.parseInt( ovo.get("PRICE2").toString() );
			}
			
			mav.addObject("orderList", list);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/orderList");
		}
		return mav;
	}
	
	
	
	
	
	@RequestMapping(value="/orderInsertOne")
	public String orderInsertOne( HttpServletRequest request,
			@RequestParam("pseq")int pseq,
			@RequestParam("quantity")int quantity) {
		int oseq = 0;
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			return "member/login";
		}else {
			// 상품하나를 주문하고 되돌아 와서  orderList 로 이동하여 주문 내역을 확인하세요
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID") );
			paramMap.put("pseq", pseq);
			paramMap.put("quantity", quantity);
			paramMap.put("oseq", 0);
			
			os.insertOrderOne( paramMap );
			
			oseq = Integer.parseInt( paramMap.get("oseq").toString() );
		}
		
		return "redirect:/orderList?oseq="+oseq;
	}
	
	
	@RequestMapping(value="/myPage")  
	public ModelAndView mypage( HttpServletRequest request, Model model ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID"));
			
			os.myPageList( paramMap );
			
			ArrayList<HashMap<String, Object>> finalList  
				= (ArrayList<HashMap<String, Object>>) paramMap.get("finalList");
			
			mav.addObject("orderList", finalList);
			mav.addObject("title", " 진행중인 주문내역");
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/orderDetail")  
	public ModelAndView orderDetail( HttpServletRequest request, Model model,
			@RequestParam("oseq") int oseq ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String , Object> paramMap = new HashMap<String , Object>();
			paramMap.put("oseq", oseq);
			paramMap.put("ref_cursor", null);
			os.listOrderByOseq(paramMap);
			
			ArrayList<HashMap<String,Object>> orderListByOseq 
				= (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			
			int totalPrice = 0;
			for( HashMap<String, Object> order : orderListByOseq ) 
				totalPrice += Integer.parseInt( order.get("QUANTITY").toString() )
									* Integer.parseInt( order.get("PRICE2").toString() ); 
			
			mav.addObject("totalPrice", totalPrice);
			mav.addObject("orderList", orderListByOseq);
			mav.addObject("orderDetail", orderListByOseq.get(0) );
			mav.setViewName("mypage/orderDetail");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/orderAll")  
	public ModelAndView orderAll( HttpServletRequest request, Model model ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID"));
			
			os.orderAllList( paramMap );
			
			ArrayList<HashMap<String, Object>> finalList  
				= (ArrayList<HashMap<String, Object>>) paramMap.get("finalList");
			
			mav.addObject("orderList", finalList);
			mav.addObject("title", "총 주문내역");
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
}



















