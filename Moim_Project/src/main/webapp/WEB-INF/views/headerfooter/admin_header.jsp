<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_header</title>
<link rel="stylesheet"  href="../resources/css/headerfooter.css">
</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">MOIM BOARD</li>
	    <li><a href="/studyBoard">STUDY</a></li>
	    <c:choose>
	    	<c:when test="${adminUser!=null }">
	   			 <li><a href="/adminLoginForm">Logout</a></li>
	   			 <li><a href="/admin_Member">회원</a></li>
	   			 <li><a href="/admin_Board">게시글</a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li><a href="/adminLoginForm">Login</a></li>
	    	</c:otherwise>
	    </c:choose>
	</ul> 
</div>
</body>