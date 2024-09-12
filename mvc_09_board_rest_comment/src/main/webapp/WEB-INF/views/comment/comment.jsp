<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- comment.jsp -->
<style>
	#comments li{
		list-style:none;
		padding:10px;
		border:1px solid #ccc;
		margin: 15px 0;
		/* height:150px; */
	}
	
	#modDiv{
		border:1px solid black;
		padding:10px;
		display:none;
	}
</style>
<div id="modDiv">
	<!-- 댓글 수정창 -->
	<h1>댓글 수정</h1>
	<!-- 수정할 댓글 번호 -->
	<div id="modCno"></div>
	<div>
		<input type="text" id="modAuth" placeholder="작성자"/>
	</div>
	<div>
		<textarea id="modText" placeholder="댓글내용"></textarea>
	</div>	
	<div>
		<button id="modBtn">MODIFY</button>
		<button id="delBtn">DELETE</button>
		<button id="closeBtn">CLOSE</button>
	</div>
</div>
<hr/>
<h2>AJAX - COMMENT TEST</h2>
<div>
	<div>
		Comment Author : <input type="text" id="cAuth"/>
	</div>
	<div>
		Comment Content : <textarea id="cText"></textarea>
	</div>
	<button id="addBtn">ADD Comment</button>
</div>
<!-- 댓글 리스트 출력 -->
<div>
	<ul id="comments"></ul>
</div>
<br/><br/><br/><br/><br/><br/><br/><br/>
<!-- jQuery library 등록 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

	var bno = '${boardVO.bno}';		// 댓글을 등록할 게시글 번호
	
	let page = 1;
	
	listPage(page);
	
	function listPage(page){
		// 어떤게시글에 댓글 목록.
		// 몇번째 페이지 인가.
		let url = "${path}/comments/"+bno+"/"+page;
		$.getJSON(url,function(data){
			console.log(data);
			console.log(data.list);
			console.log(data.pm);
			printPage(data);
		});
	}
	
	// 댓글 목록 comments ul 에 출력하는 함수
	function printPage(data){
		// data.list = [CommentVO, CommentVO ... ]
		let str = "";
		
		$(data.list).each(function(){
			console.log(this);
			let cno = this.cno;
			let cAuth = this.commentAuth;
			let cText = this.commentText;
			console.log(cno+"-"+cAuth+"-"+cText);
			str += "<li>";
			str += cno + "-" + cAuth +"<br/><hr/>"+cText;
			str += "<br/><hr/>";
			str += getTime(this.regdate);
			str += "<br/><hr/>";
			str += "<button onclick='modDiv(this,"+cno+",\""+cAuth+"\",\""+cText+"\");'>MODIFY</button>";
			str += "</li>";
		});
		
		// $("#comments").innerHTML = str;
		$("body").prepend($("#modDiv"));
		$("#modDiv").css("display","none");
		if(page == 1){
			$("#comments").html(str);	
		}else{
			$("#comments").append(str);
		}
		
		if(page < data.pm.maxPage){
			let str = "<button onclick='nextPage(this);' style='width:100%;padding:10px;'>더볼래?</button>";
			$("#comments").append(str);
		}
		
	}
	
	function nextPage(nextBtn) {
		$(nextBtn).remove();
		page++;
		listPage(page);
	}
	
	// getCommentList();
	
	// 전체 댓글 목록 검색 후 출력
	function getCommentList(){
		// GET - 검색(read)
		let url = "${path}/comments/all/"+bno;
		// 댓글 목록
		$.getJSON(url, function(data){
			console.log(data);
			printList(data);
		});
		/*
		$.ajax({
			type : "GET", url : url, dataType : "json",
			success : function(data){
				console.log(data);
			}
		});
		*/
	}
	
	// 댓글 목록 comments ul 에 출력하는 함수
	function printList(list){
		// list = [CommentVO, CommentVO ... ]
		let str = "";
		
		$(list).each(function(){
			console.log(this);
			let cno = this.cno;
			let cAuth = this.commentAuth;
			let cText = this.commentText;
			console.log(cno+"-"+cAuth+"-"+cText);
			str += "<li>";
			str += cno + "-" + cAuth +"<br/><hr/>"+cText;
			str += "<br/><hr/>";
			str += getTime(this.regdate);
			str += "<br/><hr/>";
			str += "<button onclick='modDiv(this,"+cno+",\""+cAuth+"\",\""+cText+"\");'>MODIFY</button>";
			str += "</li>";
		});
		
		// $("#comments").innerHTML = str;
		$("body").prepend($("#modDiv"));
		$("#modDiv").css("display","none");
		$("#comments").html(str);
	}
	
	// 수정창 open
	function modDiv(el, cno, auth, text){
		console.log("modDiv");
		console.log(el);
		// slow(600millis), normal(500), fast(400)
		// $("#modDiv").show('slow');
		// $("#modDiv").slideDown('slow');
		// $("#modDiv").fadeIn('slow');
		// $("#modDiv").toggle('slow');
		// $("#modDiv").fadeToggle('slow');
		
		$("#modCno").html(cno);
		$("#modText").val(text);
		$("#modAuth").val(auth);
		
		$("#modDiv").css("display", "none");
		// 이벤트가 발생한 button 태그요소의 부모 li요소 뒤에 수정 창 추가(이동)
		$(el).parent().after($("#modDiv"));
		// $(el).parent().before($("#modDiv"));
		// $(el).parent().append($("#modDiv"));
		// $(el).parent().prepend($("#modDiv"));
		$("#modDiv").slideDown("slow");
	}
	
	$("#closeBtn").click(function(){
		// $("#modDiv").hide('slow');
		// $("#modDiv").slideUp('slow');
		$("#modDiv").fadeOut('slow');
	});
	
	function getTime(time){
		
		let date = new Date(time);
		
		let year = date.getFullYear();
		let month = date.getMonth() + 1;
		let day = date.getDate();
		let hour = date.getHours();
		let minute = date.getMinutes();
		let seconds = date.getSeconds();
		let millis = date.getMilliseconds();
		
		return date.toLocaleString("ko");
	}
	
	// let cAuth = $("#cAuth").val();	// 입력태그요소의 value read
	// let cText = $("#cText").val("컨텐츠 내용");
	
	// jQuery 문법 임을 명시하는 keyword 
	// 1. jQuery,  2. $
	// jQuery("#addBtn");
	$("#addBtn").click(function(){
		
		let cAuth = $("#cAuth").val();
		let cText = $("#cText").val();
		
		// jQuery ajax
		// $.ajax({key :value, key:value});
		$.ajax({
			type : "POST",
			url : "${path}/comments",
			data : {
				bno : bno,
				commentAuth : cAuth,
				commentText : cText
			},
			// dataType : "json",			// 결과를 JavaScript 객체로 전달
			dataType : "text",
			// {key : value}
			success : function(result){
				// 응답이 성공하면 실행될 함수
				alert(result);
				getCommentList();
			},
			error : function(res){
				console.log(res);
			}
		});
	});
	
	/*
		수정 요청 처리
	*/
	$("#modBtn").click(function(){
		// 수정할 댓글 번호, 수정된 작성자, 수정된 댓글 내용
		let cno = $("#modCno").text(); // innerText 
		let auth = $("#modAuth").val(); 
		let text = $("#modText").val();
		console.log("----------------------------------------");
		console.log(auth);
		console.log(text);
		$.ajax({
			type : "PUT",
			url : "${path}/comments/"+cno,
			headers : {
				"Content-Type" : "application/json"
			},
			// queryString : ?commentText=text&commentAuth=auth
			// JSON.parse(); == 문자열 type의 json 데이터를 javascript 객체로 변환
			// JSON.stringify(); == javascript 객체를 JSON 형식의 문자열로 변환
			data : JSON.stringify({
				commentText : text,
				commentAuth : auth
			}),
			dataType : "text",
			success : function(result){
				alert(result);
				$("#modDiv").slideUp("slow");
				getCommentList();
			}
		});
	});
	
	/*
		댓글 삭제 요청 처리
	*/
	$("#delBtn").click(function(){
		
		let cno = $("#modCno").text();
		
		$.ajax({
			type : "DELETE",
			url : "${path}/comments/"+cno,
			dataType : "text",
			success : function(result){
				alert(result);
				if(result == "SUCCESS"){
					// $("#modDiv").parent(); == ul#comments
					console.log($("#modDiv").parent());
					console.log($("#modDiv").parent().first());
					getCommentList();
				}
			}
		});
	});	
	
</script>
















