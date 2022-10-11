<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link  rel="stylesheet" href="/admin/admin.css">  
<script src="/admin/admin."></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquert/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#myButton').click(function(){
		var formselect = $("fileupForm")[0];	//지목된 폼을 변수에 저장
		var formdata = new FormData(formselect);	//전송용 폼객체에 다시 저장
		
		//웹페이지 이동 / 새로고침이 필요없는 request 요청
		$.ajax({
			url:"<%=request.getContextPath()%>/fileup",
			//Controller가 있는 경로에서 fileup이라는 리퀘스트를 찾아 이동하기 위한 경로 설정
			type="POST",
			enctype:"multipart/form-data",
			async:false,
			data:formdata,
			timeout: 10000,
			contentType: false,
			processData: false,
			success:function(data){	//result->data
				if(data.STATUS==1){
					$('#filename').append("<div>"+data.FILENAME+"<div>");
					$('#image').val(dadta.FILENAME);
					$('#filename').append(
							"<img src='product_image/"+data.FILENAME+"'height='150'/>");
				}
				
			},
			error:function(){ alert("실패")};
		});
	});
});
</script>
</head>
<body>

<div id="wrap">
	<header>
		<div id="logo"><!-- 최상단 "/" 리퀘스트 요청 링크 -->
			<a href="/"><img src="/images/logo.png"  width="180" height="100"></a></div>
		<nav id="top_menu"> <!-- top menu -->
			<ul>
				<c:choose>
					<c:when test="${empty loginUser}">
						<li><a href="loginForm">LOGIN</a></li>
						<li><a href="contract">JOIN</a></li>
					</c:when>
		    		<c:otherwise>
		       			<li style="color:blue;font-weight:bold;font-size:110%;">
		       				${loginUser.NAME}(${loginUser.ID})</li>
		       			<li><a href="memberEditForm">정보수정</a></li>
		       			<li><a href="logout">LOGOUT</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="cartList">CART</a></li>
				<li><a href="myPage">MY PAGE</a></li>
		    	<li ><a href="qnaList">Q&amp;A(1:1)</a></li>
		    	<li ><a href="admin">admin</a></li>
			</ul>
		</nav>
		<nav id="catagory_menu"> <!-- catagory menu -->
			<ul>
				<li><a href="catagory?kind=1">Heels</a></li>
				<li><a href="catagory?kind=2">Boots</a></li>
				<li><a href="catagory?kind=3">Sandals</a></li>
				<li><a href="catagory?kind=4">Sneakers</a></li>
				<li><a href="catagory?kind=5">Sleeper</a></li>	
				<li><a href="catagory?kind=6">On Sale</a></li>
			</ul>
		</nav>
		<div class="clear"></div><hr>
	</header>

