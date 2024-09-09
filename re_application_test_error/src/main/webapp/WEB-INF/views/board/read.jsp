<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/header.jsp"%>
<div class="container">
	<div>
		<h1>게시글 상세</h1>
	</div>
	<div class="row  m-5">
		<div class="col-md-2">
			<span class="form-control-plaintext">작성자</span>
		</div>
		<div class="col-md-4">
			<span class="form-control-plaintext">${board.writer}</span>
		</div>
	</div>
	<div class="row m-5">
		<div class="col-md-2">
			<span class="form-control-plaintext">제목</span>
		</div>
		<div class="col-md-10">${board.title}</div>
	</div>

	<div class="row m-5">
		<div class="col-md-2">
			<span class="form-control-plaintext">내용</span>
		</div>
		<div class="col-md-10">
			<textarea name="notice_content" class="form-control" rows="10"
				readonly>${board.content}</textarea>
		</div>
	</div>
	<div class="row m-5">
		<div class="col-md-4">
			<a href="${contextPath}/board/update?bno=${board.bno}"
				class="form-control btn btn-warning">수정</a>
		</div>
		<div class="col-md-4">
			<a href="#" onclick="deleteNotice('${board.bno}',event);"
				class="form-control btn btn-danger">삭제</a>
		</div>
		<div class="col-md-4">
			<a href="${contextPath}/board/list?bno=${board.bno}"
				class="form-control btn btn-primary">목록</a>
		</div>
	</div>
</div>
<script>
	function deleteNotice(bno,event){
		event.preventDefault();
		if(confirm(bno+" 게시물을 삭제하시겠습니까?")){
			location.href='${contextPath}/board/delete?bno='+bno;
		}
	}
</script>
<%@ include file="../common/footer.jsp"%>