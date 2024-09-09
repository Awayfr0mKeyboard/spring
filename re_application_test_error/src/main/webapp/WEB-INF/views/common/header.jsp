<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="${contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<title>board_list</title>
</head>
<body>
	<div class="container">
		<nav class="navbar">
			<ul class="navbar nav">
				<li class="nav-item">
					<a class="nav-link" href="${contextPath}/board/list">게시판</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">질문과답변</a></li>
				<li class="nav-item"><a class="nav-link" href="#">오시는 길</a></li>
				<li class="nav-item"><a class="nav-link" href="#">가시는 길</a></li>
			</ul>
		</nav>
	</div>