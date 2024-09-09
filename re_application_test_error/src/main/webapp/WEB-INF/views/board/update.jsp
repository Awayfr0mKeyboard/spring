<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/header.jsp"%>

<div class="container">
	<form action="${contextPath}/board/update" method="post">
		<!-- 수정할 게시글 번호 추가 수정 -->
		<input type="hidden" name="bno" value="${boardVO.bno}">
		<div>
			<h1>게시글 수정</h1>
		</div>
		<div class="row  m-5">
			<div class="col-md-2">
				<span class="form-control-plaintext text-center">작성자</span>
			</div>
			<div class="col-md-10">
				<input class="form-control" name="writer" type="text"
					value="${boardVO.writer}" readonly />
			</div>
		</div>
		<div class="row m-5">
			<div class="col-md-2">
				<span class="form-control-plaintext">제목</span>
			</div>
			<div class="col-md-10">
				<input class="form-control" name="title" type="text"
					value="${boardVO.title}" />
			</div>
		</div>

		<div class="row m-5">
			<div class="col-md-2">
				<span class="form-control-plaintext">내용</span>
			</div>
			<div class="col-md-10">
				<textarea name="content" class="form-control" rows="10">${boardVO.content}</textarea>
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