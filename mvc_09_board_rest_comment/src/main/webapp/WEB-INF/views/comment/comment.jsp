<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!-- comment.jsp -->

<hr/>
<h2>AJAX - COMMENT TEST</h2>
<div>
	<div>
		Comment Author : <input type="text" id="cAuth" />
	</div>
	<div>
		Comment Content : <textarea id="cText" > </textarea>
	</div>
	<button id="addBtn">ADD Comment</button>
</div>
<!-- jQuery library 등록 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

	var bno = '${boardVO.bno}';			// 댓글을 등록할 게시글 번호
	
	// let cAuth = $("#cAuth").val();		// 입력 태그 요소의 value read
	// let cText = $("#cText").val("컨텐츠 내용");
	
	// jQuery 문법임을 명시하는 keyword
	// 1. jQuery 2. $
	// jQuery("#addBtn");
	$("#addBtn").click(function() {
		let cAuth = $("#cAuth").val();
		let cText = $("#cText").val();
		
		// jQuery ajax
		$.ajax({
			type : "POST",
			// comments 라고 요청을 보냄
			url : "${path}/comments",
			data : {
				bno : bno,
				commentAuth : cAuth,
				commentText : cText
			},
			// dataType : "json",			// 결과를 JavaScript 객체로 전달
			dataType : "text",
			// {key : value}
			success : function(result) {
				// 응답이 성공하면 실행될 함수
				alert(result);
			},
			error : function(res) {
				console.log(res);
			}
		});
		
	});
	
</script>