<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../headerfooter/header.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYPage</title>
<link  rel="stylesheet" href="../resources/css/board.css">
<br/><h1 style="text-align:center;">${loginUser.name }님의 MyPage</h1><br/>

<!------------------------ 내가 쓴 글 ------------------------>
<h3 style="text-align:center;">🧐내가 쓴 글</h3>
<div id="wrap" align="center">
	<table class="list">
		<tr><td colspan="7" style="border: white; text-align: right">
		
		<tr><th>NO</th><th>지역</th><th>주제</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="board" items="${myList}">
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
				<td>
					<a href="boardView?b_num=${board.b_num}">${board.title}</a>
				</td>
				<td align="center">${board.id}</td>
				<td align="center"><fmt:formatDate value="${board.b_date}" /></td>
				<td align="center">${board.b_count}</td></tr>
		</c:forEach>
	</table><br>
	<!------------ 내가 쓴 글 페이징 ------------>
	<div id="paging">
		<c:if test="${paging.prev}">
			<a href="myPage?id=${loginUser.id}&page=${paging.beginPage-1}&rpage=${paging2.page}">◀</a></c:if> 
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
		    <c:choose>
		        <c:when test="${paging.page==index}"> ${index} </c:when>
		        <c:otherwise><a href="myPage?id=${loginUser.id}&page=${index}&rpage=${paging2.page}">${index}</a></c:otherwise>
		    </c:choose>
		</c:forEach>
		<c:if test="${paging.next}">
			<a href="myPage?id=${loginUser.id}&page=${paging.endPage+1}&rpage=${paging2.page}">▶</a></c:if>
	</div>
</div>


<!------------------------ 내가 쓴 댓글 ------------------------>
<br/><br/><h3 style="text-align:center;">😋내가 쓴 댓글</h3>
<div id="wrap" align="center">
	<table class="list">
		<tr><td colspan="7" style="border: white; text-align: right">
		
		<tr><th>게시글번호</th><th>댓글내용</th><th>작성자</th><th>작성일</th></tr>
		<c:forEach var="reply" items="${rList}">
			<tr class="record">	
				<td align="center">${reply.b_num }</td>
				<td align="center">
					<a href="boardView?b_num=${reply.b_num}">${reply.r_content }</td>
				<td align="center">${reply.r_id}</td>
				<td align="center"><fmt:formatDate value="${reply.r_date}" /></td>
		</c:forEach>
	</table><br>

	<!------------ 내가 쓴 댓글 페이징 ------------>
	<div id="paging">
		<c:if test="${paging2.prev}">
			<a href="myPage?id=${loginUser.id}&page=${paging.page}&rpage=${paging2.beginPage-1}">◀</a></c:if> 
		<c:forEach begin="${paging2.beginPage}" end="${paging2.endPage}" step="1" var="index">
		    <c:choose>
		        <c:when test="${paging2.page==index}"> ${index} </c:when>
		        <c:otherwise><a href="myPage?id=${loginUser.id}&page=${paging.page}&rpage=${index}">${index}</a></c:otherwise>
		    </c:choose>
		</c:forEach>
		<c:if test="${paging2.next}">
			<a href="myPage?id=${loginUser.id}&page=${paging.page}&rpage=${paging2.endPage+1}">▶</a></c:if>
	</div>
</div><br/>


</head>
<body>

</body>
</html>