<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="session"/>
<!-- header.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER</title>
<style>
	.profile {
		width: 20px;
		height: 20px;
		border-radius: 10px;
		border: 1px solid orange;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	let msg = '${msg}';
	if (msg != '') alert(msg);
	
	let expire = '${expire}';
	if (expire != '') {
		alert(expire);
		location.href='${path}/user/logout';
	}
</script>
</head>
<body>
	<header>
		<c:choose>
			<c:when test="${empty sessionScope.userInfo}">
				<a href="${path}/user/join">회원가입</a>
				<a href="${path}/user/login">로그인</a>
			</c:when>
			<c:otherwise>
				<c:if test="${!empty userInfo.u_profile}">
					<img class="profile" src="${path}/displayFile?fileName=${userInfo.u_profile}" />
				</c:if>
				<c:if test="${empty userInfo.u_profile}">
					<!-- 프로필 이미지 없음. -->
					<img class="profile" src="${path}/resource/img/profile.jpg" />
				</c:if>
				${userInfo.u_name}님 방가.
				<a href="${path}/user/logout">로그아웃</a> |
				<a href="${path}/userChat">USER CHAT</a>
			</c:otherwise>
		</c:choose>
		| <a href="${path}/chat">CHAT</a>
	</header>
	
	
	
	
	
	
	
	
	
	
	
	
	
	