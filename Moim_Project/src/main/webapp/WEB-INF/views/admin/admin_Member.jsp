<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../headerfooter/admin_header.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin_Member</title>
<link  rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
	<div id="wrap" align="center">
	<br/>
	<h1>📚회원 관리</h1><br/><br/>
	${message}
	<table class="list">
	<form id="memberfrm" method="get" action="/withdrawal">
		<!------------ 회원 목록 ------------>
		<tr><th>아이디</th><th>이름</th><th>이메일</th><th>선택</th></tr>
		<c:forEach var="member" items="${memberList}">
			<tr class="record">	
				<td align="center">${member.id }</td>
				<td align="center">${member.name }</td>
				<td align="center">${member.email }</td>
				<td align="center">
				<input type="checkbox" name="select" value=${member.id } id="check">
				</td></tr>
		</c:forEach>
		<input type="submit" value="회원탈퇴" onclick="return deleteMember()">
	</form>
	</table><br>
	<!------------ 페이징 ------------>
	<div id="paging">
		<c:if test="${paging.prev}">
			<a href="studyBoard?page=${paging.beginPage-1}">◀</a></c:if> 
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
		    <c:choose>
		        <c:when test="${paging.page==index}"> ${index} </c:when>
		        <c:otherwise><a href="studyBoard?page=${index}">${index}</a></c:otherwise>
		    </c:choose>
		</c:forEach>
		<c:if test="${paging.next}">
			<a href="studyBoard?page=${paging.endPage+1}">▶</a></c:if>
	</div>
</div>


<script>
 /*back키 사용 시 페이지 재로딩*/
 window.onpageshow = function(event){
	 if(event.persisted){
		 document.location.reload();
	 }
 };
 
/*검색기능*/ 
 function Search(region,topic,keyword){
		if( document.searchfrm.keyword.value==""){
			alert('검색어를 입력해주세요');
			document.searchfrm.keyword.focus();
		}else{
			document.searchfrm.action = "searchPage";
			document.searchfrm.submit();
		}
	}
	
/*체크박스*/	
 function deleteMember(){
	var checkbox = document.getElementById('check').checked;
	if(checkbox==false) alert('회원을 선택하세요'); return false;
	else return true;
}
	
	
 
 
 </script>
</body>
</html>