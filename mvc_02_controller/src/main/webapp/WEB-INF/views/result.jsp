<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- webapp/WEB-INF/views/result.jsp -->
<h2>result jsp page</h2>
<h3>${requestScope.msg}</h3>
<h3>${requestScope.msgRequest}</h3>
<h4>${productVO.num}</h4>
<h4>${productVO.name}</h4>
<h4>${productVO.price}</h4>
</body>
</html>