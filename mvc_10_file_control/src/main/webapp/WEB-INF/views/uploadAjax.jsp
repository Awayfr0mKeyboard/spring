<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadAjax.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	.fileDrop{
		width : 100%;
		height:200px;
		background-color:#cccccc;
		border:1px solid black;
	}
</style>
</head>
<body>
	<h2>File Drag & Drop</h2>
	<div class="fileDrop"></div>
	<!-- upload된 파일 목록 출력 -->
	<div id="uploadList"></div>
	
	<script>
		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		/*
		$(".fileDrop").dragenter(function(event){
			event.preventDefault();
		});
		
		$(".fileDrop").dragover(function(){
			
		});
		*/
		// file upload
		$(".fileDrop").on("drop",function(event){
			event.preventDefault();
			let files = event.originalEvent.dataTransfer.files;
			console.log(files);
			
			// html 태그의  form 입력태그 양식 정보를 저장하는 javascript 객체
			let formData = new FormData();
			
			for(let i = 0; i < files.length; i++){
				let file = files[i];
				console.log(file);
				formData.append("files",file);
			}
			
			// 검색 : GET, 
			// 수정 : PATCH, PUT
			// 삭제 : DELETE
			// 삽입 : POST
			
			$.ajax({
				type : "POST",
				url : "uploadFiles",
				data : formData,
				// ajax를 통해서 서버에 데이터를 전달할때 데이터유형 결정
				// application/x-www-urlencoded; charset=utf-8
				contentType : false,
				// contentType에 맞게 쿼리 문자열로 처리되는게 기본
				// 처리되지 않은 데이터를 전송하려면 false로 변경
				processData : false,
				dataType : "json",
				success : function(result){
					// result == List<String>
					console.log(result);
					let str = "";
					$(result).each(function(){
						let fileName = this;
						// fileName : 70c7bacca99b4c4abdf53883e529b048_next.txt
						let index = fileName.lastIndexOf("_") + 1;
						// index번호 부터 끝까지 자른 문자열 반환
						let original = fileName.substr(index);
						str += "<div>";
						str += "<a href='downloadFile?fileName"+fileName+"'>";
						str += original;			 // 사용자에게 출력할 파일 이름
						str += "</a>";
						str += "</div>";
					}); // end each for
					
					// upload 된 파일 이름 및 링크 출력
					$("#uploadList").append(str);
				}
			});
			
		});
		
	</script>
</body>
</html>















