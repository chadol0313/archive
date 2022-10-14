<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../headerfooter/header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardEditForm</title>
<link  rel="stylesheet" href="../resources/css/board.css">
<script src="../resources/script/board.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h1>게시글 수정</h1>
	<form name="frm" method="post" action="boardEdit">
		<table>
			<tr><th>작성자</th><td>${loginUser.name} 
				<input type="hidden" name="id" value="${loginUser.id}"></td></tr>
				<input type="hidden" name="b_num" value="${b_num}">
			<tr><th>비밀번호</th><td><input type="password" name="pwd"> 
							* 필수 (게시물 수정 삭제시 필요합니다.)</td></tr>
			<tr><th>이메일</th><td>${loginUser.email}
				<input type="hidden" name="email"  value="${loginUser.email}"></td></tr>
			<tr>
				<th>지역</th><td>
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
					  </select></td></tr>	
				<tr><th>주제</th><td>
					<select name="topic" >
					    <option value="1" selected>스터디</option>
					    <option value="2">취미</option>
					</select>
					</td></tr>	
			<tr><th>상태</th>
				<td>
				<c:choose>
					<c:when test="${bdto.state eq 'O' }">
						<input type="radio" id="state" name="state" value="O" checked>
	    				<label for="contactChoice1">모집중</label>
	    				<input type="radio" id="state" name="state" value="X">
					    <label for="contactChoice2">마감</label></c:when>
	    			<c:otherwise>
	    				<input type="radio" id="state" name="state" value="O">
	    				<label for="contactChoice1">모집중</label>
					    <input type="radio" id="state" name="state" value="X" checked>
					    <label for="contactChoice2">마감</label></c:otherwise>	
				</c:choose>    
				</td></tr>
			<tr><th>제목</th>
				<td><input type="text" size="50" name="title" value="${bdto.title}"> * 필수</td></tr>
			<tr><th>내용</th>
				<td><textarea cols="70" rows="15" name="b_content" >${bdto.b_content}</textarea></td></tr>
		</table><br><br>
		<input type="button" value="수정" onclick="boardEdit()"> 
		<input type="reset" value="다시 작성"> 
		<input type="button" value="목록" onclick="location.href='studyBoard'">
		</form><br>${message}
</div>





<!---------------------- 게시글 수정 function ---------------------->
<script type="text/javascript">
function boardEdit(){
	if( document.frm.pwd.value==""){
		alert('비밀번호를 입력해주세요');
		document.frm.pwd.focus();
	}else if( document.frm.title.value==""){
		alert('제목을 입력해주세요');
		document.frm.title.focus();
	}else if( document.frm.b_content.value==""){
		alert('내용을 입력해주세요');
		document.frm.b_content.focus();
	}else{
		document.frm.action = "boardEdit";
		document.frm.submit();
	}
}
</script>

</body>
</html>
