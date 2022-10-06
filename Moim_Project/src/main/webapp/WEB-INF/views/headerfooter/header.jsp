<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="stylesheet"  href="../resources/css/headerfooter.css">
</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">MOIM BOARD</li>
	    <li><a href="/studyBoard">STUDY</a></li>
	    <c:choose>
	    	<c:when test="${loginUser!=null }">
	   			 <li><a href="/logout">Logout</a></li>
	   			 <li><a href="/updateMemberForm">정보수정</a></li>
	   			 <li><a href="/myPage">MyPage</a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li><a href="/">Login</a></li>
	   			<li><a href="/joinForm">Sign in</a></li>
	    	</c:otherwise>
	    </c:choose>
	</ul> 
</div>
</body>