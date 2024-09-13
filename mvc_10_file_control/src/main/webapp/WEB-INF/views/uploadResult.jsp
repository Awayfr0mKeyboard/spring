<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadResult.jsp</title>
</head>
<body>
	<h3>Upload Result</h3>
	<h3><a href="uploadForm">uploadForm</a></h3>
	<h4>${saveName}</h4>
	
	<hr/>
	<h3>${auth}</h3>
	<h3>${content}</h3>
	<hr/>
	
	<c:if test="${!empty saves}">
		<hr/>
		<h3>Upload Multiple saves</h3>
		<c:forEach var="name" items="${saves}" >
			<h4>${name}</h4>
			<c:set var="origin" value="${fn:substringAfter(name, '_')}" />
			<h4>${origin}</h4>
			<h4><a download=${origin} href="downloadFile?fileName=${name}">${origin}</a></h4>
			<h4><a href="downloadFile?fileName=${name}">${origin}</a></h4>
		</c:forEach>
	</c:if>
</body>
</html>