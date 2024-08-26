<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
</head>
<body>
	<h1>REGISTER BOARD</h1>
	<!-- board/register -->
	<form method="POST">
		<div>
			<label>TITLE</label>
			<input type="text" name="title"/>
		</div>
		<div>
			<label>CONTENT</label>
			<textarea name="content" rows=3></textarea>
		</div>
		<div>
			<label>WRITER</label>
			<input type="text" name="writer"/>
		</div>
		<div>
			<input type="submit" value="글작성 완료"/>
			<a href="listPage">paging 글 목록</a>
		</div>
	</form>
</body>
</html>












