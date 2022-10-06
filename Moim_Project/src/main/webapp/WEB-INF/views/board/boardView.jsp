<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../headerfooter/header.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardView</title>
<link  rel="stylesheet" href="../resources/css/board.css">
<script src="/script/board.js"></script>
</head>
<body>

<div id="wrap" align="center">
<br/><h1>👀View Detail</h1><br/>
	<table>
		<tr><th>작성자</th><td>${board.id}</td>
		<th>지역</th><td>
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
		</td></tr>
		<tr><th>작성일</th><td><fmt:formatDate value="${board.b_date}"/></td>
			<th>조회수</th><td>${board.b_count }</td></tr>
			<th>이메일</th><td colspan="3">${board.email }</td></tr>
		<tr><th>제목</th><td colspan="3">${board.title}</td></tr>
		<tr><th>내용</th><td colspan="3"><pre>${board.b_content}</pre></td>
		</tr>
	</table><br>
	<input type="button" value="리스트" onclick="location.href='/studyBoard'">
	<c:choose>
		<c:when test="${loginUser.id eq board.id}">
			<input type="button" value="수정" onclick="location.href='identifyPwd?b_num=${board.b_num}'">
			<input type="button" value="삭제" onclick="location.href='identifyPwd?b_num=${board.b_num}'">
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div><br><br>


<!----------------------------- 댓글 ----------------------------->
<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
<div class="wrap" align="center">
<form action="addReply" method="post" name="replyFrm">
<input type="hidden" name="boardnum" value="${board.b_num}">
<table>
	<!------------ 댓글 작성 ------------>
	<tr><th>작성자</th><th>작성일</th><th>내용</th><th>&nbsp;</th></tr>
	<tr align="center">
		<td width="100">${loginUser.id}<input type="hidden" name="id" value="${loginUser.id}"></td>
		<td width="100"><fmt:formatDate value="${now}"	pattern="MM/dd"></fmt:formatDate></td>
		<td width="630"><input type="text" name="r_content" size="70"></td>
		<input type="hidden" name="b_num" value="${board.b_num}">
		<td width="100"><input type="submit" value="작성" onclick="return reply_check();"></td></tr>
		
	<!------------ 댓글 목록 ------------>
	<c:forEach var="reply" items="${replyList}">
		<tr><td align="center">${reply.r_id}</td>
			<td align="center">
				<fmt:formatDate value="${reply.r_date}"	pattern="MM/dd"></fmt:formatDate></td>
			<td>${reply.r_content}</td>
			<td align="center">
			<!------------ 댓글 삭제 ------------>
				<c:if test="${reply.r_id==loginUser.id}">
					<input type="button" value="삭제" 
				onclick="location.href='deleteReply?r_num=${reply.r_num}&b_num=${reply.b_num}'">
				</c:if>&nbsp;</td>
		</tr>
	</c:forEach>
</table>
<br /><br /><br />
</form>	
</div>





 <script type="text/javascript">
 function reply_check(){
	 if(document.replyFrm.value=="")
		 alert('내용을 입력해주세요')
		 document.replyFrm.r_content.focus();
	 else{
		 document.replyFrm.action = "addReply";
		 document.replyFrm.submit();
	 }
 }
 </script>
 
 
 


</body>
</html>







