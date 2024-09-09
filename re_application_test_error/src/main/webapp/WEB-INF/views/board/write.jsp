<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/header.jsp"%>
<div class="container">
	<form action="${contextPath}/board/write" method="post">
		<div>
			<h1>게시글 작성</h1>
		</div>
		<div class="row  m-5">
			<div class="col-md-2">
				<span class="form-control-plaintext">작성자</span>
			</div>
			<div class="col-md-10">
				<input class="form-control" name="writer" type="text" />
			</div>
		</div>
		<div class="row m-5">
			<div class="col-md-2">
				<span class="form-control-plaintext">제목</span>
			</div>
			<div class="col-md-10">
				<!-- <input class="form-control" type="text" /> -->
				<input class="form-control" name="title" type="text" />
			</div>
		</div>

		<div class="row m-5">
			<div class="col-md-2">
				<span class="form-control-plaintext">내용</span>
			</div>
			<div class="col-md-10">
				<textarea name="content" class="form-control" rows="10"></textarea>
			</div>
		</div>
		<div class="row m-5">
			<div class="col-md-12">
				<input type="submit" value="작성완료" class="form-control btn btn-primary" />
			</div>
		</div>
	</form>
</div>
<%@ include file="../common/footer.jsp"%>