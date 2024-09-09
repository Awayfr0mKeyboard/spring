<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ include file="../common/header.jsp"%>

<div class="container">
	<h1>List Page</h1>
	<table class="table table-hover">
		<tr>
			<td colspan="5">
				<div>
					<a class="btn btn-primary" href="${contextPath}/board/write">글 작성</a>
				</div>
			</td>
		</tr>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성시간</th>
			<th>조회수</th>
		</tr>

		<c:choose>
			<c:when test="${!empty list}">
				<c:forEach var="b" items="${list}">
					<tr onclick="detail('${b.bno}');">
						<td>${b.bno}</td>
						<td>${b.title}</td>
						<td>${b.writer}</td>
						<td>
							<f:formatDate value="${b.regdate}" pattern="yyyy/MM/dd hh:mm" />
						</td>
						<td>${b.viewcnt}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">등록된 게시글이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>

<div class="container">
	<ul class="pagination justify-content-center">
		<c:if test="${pm.prev}">
			<li class="page-item"><a class="page-link"
				href="?page=${pm.startPage-1}">&lt;</a></li>
		</c:if>
		<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
			<c:choose>
				<c:when test="${pm.cri.page eq i}">
					<li class="page-item active"><a class="page-link" href="#">${i}</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${i}">${i}</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pm.next}">
			<li class="page-item"><a class="page-link"
				href="?page=${pm.endPage+1}">&gt;</a></li>
		</c:if>
	</ul>
</div>

<script>
		function detail(bno){
			location.href="${contextPath}/board/detail?bno="+bno;
		}
	</script>
<%@ include file="../common/footer.jsp"%>