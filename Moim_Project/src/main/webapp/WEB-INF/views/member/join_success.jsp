<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join_success</title>
</head>
<body>
 <div class="login-page">
  <div class="form">
	<h1>회원가입에 성공했습니다. 로그인해주세요</h1>
    <form class="login-form" action="main" name="loginFrm" method="post">
      <input type="text" name="id" placeholder="id"/>
      <input type="password" name="pwd" placeholder="password"/>
      <input type="submit" value="LOGIN" class="submit" onClick="return logincheck();">
       <p class="message">${message}</p>
    </form>
  </div>
</div>
</body>
</html>