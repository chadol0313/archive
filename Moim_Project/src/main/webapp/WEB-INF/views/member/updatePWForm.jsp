<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updatePWForm</title>
<link  rel="stylesheet" href="resources/css/main.css">
<script type="text/javascript" src="resources/script/member.js"></script>
</head>
<body>

<div class="login-page">
  <div class="form">
  	<h2>비밀번호 수정</h2>
    <form class="login-form" method="post" name="pwdFrm">
    	
      <input type="hidden" name="id" value="${id}"/>
      <input type="password" name="pwd" placeholder="password"/>
      <input type="password" name="re_pwd" placeholder="password 확인"/>
    	 <p class="message" style="color:red; font-weight:bold;">${message}</p>
      <br/>
      <!------------- 버튼 ------------->
     <input class="input" type="button" value="비밀번호 수정" class="submit" onclick="pwd_update()">
    </form>
  </div>
</div>






<!---------------------- 회원가입 function ---------------------->
<script type="text/javascript">
function pwd_update(){
	if (document.pwdFrm.pwd.value == "") {
		alert("비밀번호를 입력해주세요."); 	    
	    document.pwdFrm.pwd.focus();
	} else if(document.pwdFrm.re_pwd.value == "") {
	    alert("비밀번호 확인을 입력해주세요");	    
	    document.pwdFrm.re_pwd.focus();
	} else{
		document.pwdFrm.action = "updatePW";
	    document.pwdFrm.submit();
	}
}
</script>



</body>
</html>