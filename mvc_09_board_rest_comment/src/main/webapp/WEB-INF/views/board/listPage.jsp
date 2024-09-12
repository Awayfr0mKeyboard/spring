<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listPage.jsp</title>
<script>
	let msg = "${msg}";
	if(msg != '') alert(msg);
</script>
</head>
<body>
	<!-- model : list, pm -->
	<h3>BOARD LIST PAGE</h3>
	<button onclick="location.href='register';">NEW BOARD</button>
	<h3> LIST </h3>
	<table border=1>
		<tr>
			<th>BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEWCNT</th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.bno}</td>
				<td><a href="readPage${pm.makeQuery(pm.cri.page)}&bno=${board.bno}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td><f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/></td>
				<td>${board.viewcnt}</td>
			</tr>
		</c:forEach>
		<c:if test="${!empty pm and pm.maxPage > 1}">
			<tr>
				<th colspan="5">
					<c:if test="${pm.first}">
						<a href="?page=1">[&laquo;]</a>
					</c:if>
					<c:if test="${pm.prev}">
						<a href="?page=${pm.prevPage}">[&lt;]</a>
					</c:if>
					<c:forEach var="i" begin="${pm.startPage}" end ="${pm.endPage}">
						<a href="?page=${i}">[${i}]</a>
					</c:forEach>
					<c:if test="${pm.next}">
						<a href="?page=${pm.nextPage}">[&gt;]</a>
					</c:if>
					<c:if test="${pm.last}">
						<a href="?page=${pm.maxPage}">[&raquo;]</a>
					</c:if>
				</th>
			</tr>
		</c:if>
	</table>
</body>
</html>







