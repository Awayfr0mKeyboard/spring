<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>methodTest.jsp</title>
</head>
<body>
	<h2>requestScope name : ${name}</h2>
	<h2>requestScope name : ${age}</h2>
	<h1>POST</h1>
	<form action="methodTest" method="POST">
		<input type="text" name="name" required> <br/>
		<input type="number" name="age" required> <br/>
		<input type="submit" value="POST">
	</form>
	<br/>
	<hr/>
	<br/>
	<!-- GET(읽기), POST(쓰기), PATCH(부분 수정), PUT(전체 수정), DELETE(삭제) -->
	<h1>PUT</h1>
	<!-- http 통신 방식에는 PUT이 없어서 기본값인 GET 방식으로 전송 -->
	<form action="methodTest" method="POST">
		<!-- PUT으로 전송 방식 변환 -->
		<input type="hidden" name="_method" value="PUT">
		<input type="text" name="name" required> <br/>
		<input type="number" name="age" required> <br/>
		<input type="submit" value="PUT">
	</form>
	<br/>
	<hr/>
	<br/>
	<h1>Response Data Test</h1>
	<form action="sampleTest" method="GET">
		<input type="text" name="name" required> <br/>
		<input type="number" name="age" required> <br/>
		<input type="submit" value="GET SAMPLE">
	</form>
</body>
</html>