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
	<h1>πνμ κ΄λ¦¬</h1><br/><br/>
	${message}
	<table class="list">
	<form id="memberfrm" method="get" action="/withdrawal">
		<!------------ νμ λͺ©λ‘ ------------>
		<tr><th>μμ΄λ</th><th>μ΄λ¦</th><th>μ΄λ©μΌ</th><th>μ ν</th></tr>
		<c:forEach var="member" items="${memberList}">
			<tr class="record">	
				<td align="center">${member.id }</td>
				<td align="center">${member.name }</td>
				<td align="center">${member.email }</td>
				<td align="center">
				<input type="checkbox" name="select" value=${member.id } id="check">
				</td></tr>
		</c:forEach>
		<input type="submit" value="νμνν΄" onclick="return deleteMember()">
	</form>
	</table><br>
	<!------------ νμ΄μ§ ------------>
	<div id="paging">
		<c:if test="${paging.prev}">
			<a href="studyBoard?page=${paging.beginPage-1}">β</a></c:if> 
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
		    <c:choose>
		        <c:when test="${paging.page==index}"> ${index} </c:when>
		        <c:otherwise><a href="studyBoard?page=${index}">${index}</a></c:otherwise>
		    </c:choose>
		</c:forEach>
		<c:if test="${paging.next}">
			<a href="studyBoard?page=${paging.endPage+1}">βΆ</a></c:if>
	</div>
</div>


<script>
 /*backν€ μ¬μ© μ νμ΄μ§ μ¬λ‘λ©*/
 window.onpageshow = function(event){
	 if(event.persisted){
		 document.location.reload();
	 }
 };
 
/*κ²μκΈ°λ₯*/ 
 function Search(region,topic,keyword){
		if( document.searchfrm.keyword.value==""){
			alert('κ²μμ΄λ₯Ό μλ ₯ν΄μ£ΌμΈμ');
			document.searchfrm.keyword.focus();
		}else{
			document.searchfrm.action = "searchPage";
			document.searchfrm.submit();
		}
	}
	
/*μ²΄ν¬λ°μ€*/	
 function deleteMember(){
	var checkbox = document.getElementById('check').checked;
	if(checkbox==false) alert('νμμ μ ννμΈμ'); return false;
	else return true;
}
	
	
 
 
 </script>
</body>
</html>