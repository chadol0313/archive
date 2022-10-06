<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link  rel="stylesheet" href="resources/css/main.css">
<script type="text/javascript" src="resources/script/member.js"></script>
</head>
<body>

<div class="login-page">
  <div class="form">
  <div class="img">MOIM(모임)</div><br/>
    <form class="login-form" action="main" name="loginFrm" method="post">
      <input type="text" name="id" placeholder="id"/>
      <input type="password" name="pwd" placeholder="password"/>
      <input class="input" type="submit" value="LOGIN" class="submit" onClick="return logincheck();">
       <p class="message" style="color:red; font-weight:bold;">${message}</p>
      <p class="message">Not registered? <a href="joinForm">Create an account</a></p>
    </form>
  </div>
</div>

</body>
</html>