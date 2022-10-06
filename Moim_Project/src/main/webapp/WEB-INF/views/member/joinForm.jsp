<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinForm</title>
<link  rel="stylesheet" href="resources/css/main.css">
<script type="text/javascript" src="resources/script/member.js"></script>
</head>
<body>

<div class="login-page">
  <div class="form">
    <form class="login-form" method="post" name="joinFrm">
    
      <input type="text" name="id" placeholder="id"/>
      <input class="input" type="button" value="중복확인" onClick="idCheck();">
      <input type="hidden" name="re_id" value="${re_id}">
      
      <input type="password" name="pwd" placeholder="password"/>
      <input type="text" name="name" placeholder="name"/>
      <input type="text" name="email" placeholder="email address"/>
      <p style="color:red; font-weight:bold;">${message}</p>
      
      <!------------- 버튼 ------------->
     <input class="input" type="button" value="Sign In" class="submit" onclick="go_save()">
    </form>
  </div>
</div>






<!---------------------- 회원가입 function ---------------------->
<script type="text/javascript">
function go_save(){
	if (document.joinFrm.id.value == "") {
		alert("아이디를 입력하여 주세요."); 	    
	    document.joinFrm.id.focus();
	} else if(document.joinFrm.re_id.value != document.joinFrm.id.value){
		alert("아이디 중복확인을 하지 않았습니다");		
		document.joinFrm.id.focus();
	} else if(document.joinFrm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");	    
	    document.joinFrm.pwd.focus();
	} else if(document.joinFrm.name.value == "") {
	    alert("이름을 입력해 주세요.");	    
	    document.joinFrm.name.focus();
	} else if(document.joinFrm.email.value == "") {
	    alert("이메일을 입력해 주세요.");	   
	    document.joinFrm.email.focus();
	} else{
		document.joinFrm.action = "join";
	    document.joinFrm.submit();
	}
}
</script>



</body>
</html>