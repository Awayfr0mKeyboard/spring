<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm</title>
</head>
<body>
	<h1>Upload Form</h1>
	<form action="uploadForm" method="POST" enctype="multipart/form-data" >
		<input type="file" name="file" />
		<button>확인</button>
	</form>
	
	<hr/>
	
	<h1>Upload Multiple</h1>
	<form action="uploadMultiple" method="POST" enctype="multipart/form-data">
		<input type="file" name="files" multiple />
		<button>전송</button>
	</form>
	
	<hr>
	
	<h1>Request Parameter Upload</h1>
	<form action="uploadWithParam" method="POST" enctype="multipart/form-data">
		<input type="text" name="auth" /> <br/>
		<textarea name="content"></textarea> <br/>
		<input type="file" name="porfile" accept="image/*" /> <br/>
		<input type="file" name="files" multiple />
		<button>제출</button>
	</form>
	
	<hr>
	
	<h1>Request Parameter Upload 2</h1>
	<form action="uploadDTO" method="POST" enctype="multipart/form-data">
		<input type="text" name="auth" /> <br/>
		<textarea name="content"></textarea> <br/>
		<input type="file" name="porfile" accept="image/*" /> <br/>
		<input type="file" name="files" multiple />
		<button>제출</button>
	</form>
</body>
</html>