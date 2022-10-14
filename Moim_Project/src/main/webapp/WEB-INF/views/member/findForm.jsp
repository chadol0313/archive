<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findForm</title>
<link  rel="stylesheet" href="resources/css/main.css">
<script type="text/javascript" src="resources/script/member.js"></script>
</head>
<body>

<div class="login-page">
  <div class="form">
  	<h2>계정 유무 확인</h2>
    <form class="login-form" method="post" name="findFrm">
    
      <input type="text" name="id" placeholder="id"/>
      <input type="text" name="name" placeholder="name"/>
      <p class="message" style="color:red; font-weight:bold;">${message}</p>
      <br/>
      <!------------- 버튼 ------------->
     <input class="input" type="button" value="계정 확인" class="submit" onclick="go_save()">
    </form>
  </div>
</div>






<!---------------------- 회원가입 function ---------------------->
<script type="text/javascript">
function go_save(){
	if (document.findFrm.id.value == "") {
		alert("아이디를 입력하여 주세요."); 	    
	    document.findFrm.id.focus();
	} else if(document.findFrm.name.value == "") {
	    alert("이름을 입력해 주세요.");	    
	    document.findFrm.name.focus();
	} else{
		document.findFrm.action = "findPW";
	    document.findFrm.submit();
	}
}
</script>



</body>
</html>