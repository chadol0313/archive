<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../headerfooter/header.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList</title>
<link  rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
	<div id="wrap" align="center">
	<br/>
	<h1>📚Study Board</h1><br/>
	<table class="list">
		<tr><td colspan="7" style="border: white; text-align: right">
			<div style="float:right;"><a href="boardWriteForm">게시글 등록</a></div></td></tr>
		
		<!------------ 게시글 목록 ------------>
		<tr><th>NO</th><th>지역</th><th>주제</th><th>상태</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="board" items="${boardList}">
			<tr class="record">	
				<td align="center">${board.b_num }</td>
				<td align="center">
				<c:choose>
					<c:when test="${board.region == 1}">서울</c:when>
					<c:when test="${board.region == 2}">인천</c:when>
					<c:when test="${board.region == 3}">경기</c:when>
					<c:when test="${board.region == 4}">충북</c:when>
					<c:when test="${board.region == 5}">충남</c:when>
					<c:when test="${board.region == 6}">전북</c:when>
					<c:when test="${board.region == 7}">전남</c:when>
					<c:when test="${board.region == 8}">경북</c:when>
					<c:when test="${board.region == 9}">경남</c:when>
					<c:when test="${board.region == 10}">제주</c:when>
					<c:otherwise>기타</c:otherwise>
				</c:choose>
				</td>
				<td align="center">
					<c:if test="${board.topic == 1 }">스터디</c:if>
					<c:if test="${board.topic == 2 }">취미</c:if>
				</td>
				<td align="center">
					<c:if test="${board.state eq 'O' }">모집중</c:if>
					<c:if test="${board.state eq 'X' }">마감</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${board.reply_count==0}">	
							<a href="boardView?b_num=${board.b_num}">${board.title}</c:when>
						<c:otherwise>	
						<a href="boardView?b_num=${board.b_num}">${board.title} (${board.reply_count})</a></c:otherwise>
					</c:choose>
					
				</td>
				<td align="center">${board.id}</td>
				<td align="center"><fmt:formatDate value="${board.b_date}" /></td>
				<td align="center">${board.b_count}</td></tr>
		</c:forEach>
	</table><br>
	
	<!------------ 게시글 검색 ------------>
	<form name="searchfrm" method="post" action="searchPage">
	검색
	 	<select name="region" >
			<option value="1" selected>서울</option>
			<option value="2">인천</option>
			<option value="3">경기</option>
			<option value="4">충북</option>
			<option value="5">충남</option>
			<option value="6">전북</option>
			<option value="7">전남</option>
			<option value="8">경북</option>
			<option value="9">경남</option>
			<option value="10">제주</option>
			<option value="11">기타</option>
		 </select>
		 <select name="topic" >
			<option value="1" selected>스터디</option>
			 <option value="2">취미</option>
		</select>
		<input type="text" name="keyword" value="">
		<input type="button" value="검색" onclick="Search(region,topic,keyword)">
	</form><br>
	
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
 
 
 </script>
</body>
</html>