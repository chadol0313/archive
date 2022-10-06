<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../headerfooter/header.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>identifyPwd</title>
<link  rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
<form name="frm" method="post" action="boardEditForm">
	<div class="identify">
		<br/><br/><br/>
		<h3 style="text-align:center;">게시글 작성시 사용했던 비밀번호를 입력해주세요</h3><br/>
		비밀번호: <input type="password" name="pwd" value=""><br/><br/>
		<input type="hidden" value="${b_num}" name="b_num"> 
		<input type="button" value="수정" onclick="boardEdit()">
		<input type="button" value="삭제" onclick="boardDelete()">
		<br/><br/>
		<p style="color:red; font-weight:bold; font-size:12px;">${message}</p>
	</div>
</form>


<script type="text/javascript">
function boardEdit(){
	if(document.frm.pwd.value==""){
		alert('비밀번호를 입력하세요')
		document.frm.pwd.focus();
	}else{
		document.frm.action = "boardEditForm";
		document.frm.submit();
	}
}

function boardDelete(){
	if(document.frm.pwd.value==""){
		alert('비밀번호를 입력하세요')
		document.frm.pwd.focus();
	}else{
		document.frm.action = "boardDelete";
		document.frm.submit();
	}
}




</script>

</body>
</html>