<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../headerfooter/admin_header.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admini_Board</title>
<link  rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
	<div id="wrap" align="center">
	<br/>
	<h1>๐๊ฒ์๊ธ ๊ด๋ฆฌ</h1><br/>
	<table class="list">
		<tr><td colspan="7" style="border: white; text-align: right">
			<div style="float:right;"><a href="boardWriteForm">๊ฒ์๊ธ ๋ฑ๋ก</a></div></td></tr>
		
		<!------------ ๊ฒ์๊ธ ๋ชฉ๋ก ------------>
		<tr><th>NO</th><th>์ง์ญ</th><th>์ฃผ์ </th><th>์ํ</th><th>์ ๋ชฉ</th><th>์์ฑ์</th><th>์์ฑ์ผ</th><th>์กฐํ์</th></tr>
		<c:forEach var="board" items="${boardList}">
			<tr class="record">	
				<td align="center">${board.b_num }</td>
				<td align="center">
				<c:choose>
					<c:when test="${board.region == 1}">์์ธ</c:when>
					<c:when test="${board.region == 2}">์ธ์ฒ</c:when>
					<c:when test="${board.region == 3}">๊ฒฝ๊ธฐ</c:when>
					<c:when test="${board.region == 4}">์ถฉ๋ถ</c:when>
					<c:when test="${board.region == 5}">์ถฉ๋จ</c:when>
					<c:when test="${board.region == 6}">์ ๋ถ</c:when>
					<c:when test="${board.region == 7}">์ ๋จ</c:when>
					<c:when test="${board.region == 8}">๊ฒฝ๋ถ</c:when>
					<c:when test="${board.region == 9}">๊ฒฝ๋จ</c:when>
					<c:when test="${board.region == 10}">์ ์ฃผ</c:when>
					<c:otherwise>๊ธฐํ</c:otherwise>
				</c:choose>
				</td>
				<td align="center">
					<c:if test="${board.topic == 1 }">์คํฐ๋</c:if>
					<c:if test="${board.topic == 2 }">์ทจ๋ฏธ</c:if>
				</td>
				<td align="center">
					<c:if test="${board.state eq 'O' }">๋ชจ์ง์ค</c:if>
					<c:if test="${board.state eq 'X' }">๋ง๊ฐ</c:if>
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
	
	<!------------ ๊ฒ์๊ธ ๊ฒ์ ------------>
	<form name="searchfrm" method="post" action="searchPage">
	๊ฒ์
	 	<select name="region" >
			<option value="1" selected>์์ธ</option>
			<option value="2">์ธ์ฒ</option>
			<option value="3">๊ฒฝ๊ธฐ</option>
			<option value="4">์ถฉ๋ถ</option>
			<option value="5">์ถฉ๋จ</option>
			<option value="6">์ ๋ถ</option>
			<option value="7">์ ๋จ</option>
			<option value="8">๊ฒฝ๋ถ</option>
			<option value="9">๊ฒฝ๋จ</option>
			<option value="10">์ ์ฃผ</option>
			<option value="11">๊ธฐํ</option>
		 </select>
		 <select name="topic" >
			<option value="1" selected>์คํฐ๋</option>
			 <option value="2">์ทจ๋ฏธ</option>
		</select>
		<input type="text" name="keyword" value="">
		<input type="button" value="๊ฒ์" onclick="Search(region,topic,keyword)">
	</form><br>
	
	<!------------ ํ์ด์ง ------------>
	<div id="paging">
		<c:if test="${paging.prev}">
			<a href="studyBoard?page=${paging.beginPage-1}">โ</a></c:if> 
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
		    <c:choose>
		        <c:when test="${paging.page==index}"> ${index} </c:when>
		        <c:otherwise><a href="studyBoard?page=${index}">${index}</a></c:otherwise>
		    </c:choose>
		</c:forEach>
		<c:if test="${paging.next}">
			<a href="studyBoard?page=${paging.endPage+1}">โถ</a></c:if>
	</div>
</div>


<script>
 /*backํค ์ฌ์ฉ ์ ํ์ด์ง ์ฌ๋ก๋ฉ*/
 window.onpageshow = function(event){
	 if(event.persisted){
		 document.location.reload();
	 }
 };
 
/*๊ฒ์๊ธฐ๋ฅ*/ 
 function Search(region,topic,keyword){
		if( document.searchfrm.keyword.value==""){
			alert('๊ฒ์์ด๋ฅผ ์๋ ฅํด์ฃผ์ธ์');
			document.searchfrm.keyword.focus();
		}else{
			document.searchfrm.action = "searchPage";
			document.searchfrm.submit();
		}
	}
 
 
 </script>
</body>
</html>