<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../headerfooter/admin_header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_main</title>
</head>
<body>
<h3>어드민 페이지입니다</h3>
<input type="button" onclick="/admin_Member" value="회원관리">
<input type="button" onclick="/admin_Board" value="게시글관리">
</body>
</html>