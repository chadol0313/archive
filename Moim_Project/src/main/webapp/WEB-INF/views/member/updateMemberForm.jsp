<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../headerfooter/header.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMemberForm</title>
<link  rel="stylesheet" href="resources/css/main.css">
<script type="text/javascript" src="resources/script/member.js"></script>
</head>
<body>
<br/><h1 style="text-align:center; color:white;">😎정보수정</h1>

<div class="login-page">
  <div class="form">
    <form class="login-form" action="updateMember" method="post" name="updateFrm">
    
      <input type="text" name="id" value="${loginUser.id }" readonly>
      <input type="password" name="pwd" placeholder="password"/>
      <input type="text" name="name" value="${loginUser.name}">
      <input type="text" name="email" value="${loginUser.email}">
       <p class="message" style="color:red; font-weight:bold;">${message}</p><br/>
      <!------------- 버튼 ------------->
      <input class="input" type="button" value="정보수정" class="submit" onclick="go_update()">
    </form>
  </div>
</div>


<script type="text/javascript">
function go_update(){
	if(document.updateFrm.pwd.value==""){
		alert("비밀번호를 입력해주세요")
		documnet.updateFrm.pwd.focus();
	}else document.updateFrm.action = "updateMember";
    document.updateFrm.submit();
}

</script>

</body>
</html>