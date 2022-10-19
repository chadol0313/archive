<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../headerfooter/header.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchPage</title>
<link  rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
	<div id="wrap" align="center">
	<br/><h1>🔍검색결과</h1>
	<table class="list">
		<tr><td colspan="7" style="border: white; text-align: right">
			<div style="float:right;"><a href="boardWriteForm">게시글 등록</a></div></td></tr>
		
		<!------------ 게시글 목록 ------------>
		<tr><th>NO</th><th>지역</th><th>주제</th><th>상태</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="search" items="${searchList}">
			<tr class="record">	
				<td align="center">${search.b_num }</td>
				<td align="center">
				<c:choose>
					<c:when test="${search.region == 1}">서울</c:when>
					<c:when test="${search.region == 2}">인천</c:when>
					<c:when test="${search.region == 3}">경기</c:when>
					<c:when test="${search.region == 4}">충북</c:when>
					<c:when test="${search.region == 5}">충남</c:when>
					<c:when test="${search.region == 6}">전북</c:when>
					<c:when test="${search.region == 7}">전남</c:when>
					<c:when test="${search.region == 8}">경북</c:when>
					<c:when test="${search.region == 9}">경남</c:when>
					<c:when test="${search.region == 10}">제주</c:when>
					<c:otherwise>기타</c:otherwise>
				</c:choose>
				</td>
				<td align="center">
					<c:if test="${search.topic == 1 }">스터디</c:if>
					<c:if test="${search.topic == 2 }">취미</c:if>
				</td>
				<td align="center">
					<c:if test="${search.state eq 'O' }">모집중</c:if>
					<c:if test="${search.state eq 'X' }">마감</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${board.reply_count==0}">	
							<a href="boardView?b_num=${search.b_num}">${search.title}</c:when>
						<c:otherwise>	
						<a href="boardView?b_num=${search.b_num}">${search.title} (${search.reply_count})</a></c:otherwise>
					</c:choose>
				</td>
				<td align="center">${search.id}</td>
				<td align="center"><fmt:formatDate value="${search.b_date}" /></td>
				<td align="center">${search.b_count}</td></tr>
		</c:forEach>
	</table><br>
	<!------------ 게시글 검색 ------------>
	<form>
	검색
	 	<select name="region" >
			<option value="1" <c:if test="${region =='1'}">selected="selected"</c:if>>서울</option>
			<option value="2" <c:if test="${region =='2'}">selected="selected"</c:if>>인천</option>
			<option value="3" <c:if test="${region =='3'}">selected="selected"</c:if>>경기</option>
			<option value="4" <c:if test="${region =='4'}">selected="selected"</c:if>>충북</option>
			<option value="5" <c:if test="${region =='5'}">selected="selected"</c:if>>충남</option>
			<option value="6" <c:if test="${region =='6'}">selected="selected"</c:if>>전북</option>
			<option value="7" <c:if test="${region =='7'}">selected="selected"</c:if>>전남</option>
			<option value="8" <c:if test="${region =='8'}">selected="selected"</c:if>>경북</option>
			<option value="9" <c:if test="${region =='9'}">selected="selected"</c:if>>경남</option>
			<option value="10" <c:if test="${region =='10'}">selected="selected"</c:if>>제주</option>
			<option value="11" <c:if test="${region =='11'}">selected="selected"</c:if>>기타</option>
		 </select>
		 <select name="topic" >
			<option value="1" <c:if test="${topic =='1'}">selected="selected"</c:if>>스터디</option>
			 <option value="2" <c:if test="${topic =='2'}">selected="selected"</c:if>>취미</option>
		</select>
		<input type="text" name="keyword" value="${keyword}">
		<input type="submit" value="검색" onclick="Search()">
	</form><br>
	
	
	<!------------ 페이징 ------------>
	<div id="paging">
		<c:if test="${paging.prev}">
			<a href="searchPage?page=${paging.beginPage-1}">◀</a></c:if> 
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
		    <c:choose>
		        <c:when test="${paging.page==index}"> ${index} </c:when>
		        <c:otherwise>
		        <a href="searchPage?page=${index}&region=${search.region}&topic=${baord.topic}$title=${search.title}">${index}</a></c:otherwise>
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
 </script>
</body>
</html>