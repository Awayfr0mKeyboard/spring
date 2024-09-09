<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>javascript.jsp</title>
</head>
<body>
	<h1>JavaScript Ajax XMLHttprequest</h1>
	<input type="button" id="btn" value="js btn"/>
	<div id="box"></div>
	<hr/>
	<script>
		/*
			AJAX : 비동기 자바스크립트와 XML(Asynchronous JavaScript And Xml)
			Javscript를 이용하여 서버와 통신하기 위한 XMLHttpRequest 객체를 사용하는 것
		*/
		var httpRequest;
	
		document.getElementById("btn").onclick = function(){
				
			httpRequest = new XMLHttpRequest();
			
			if(!httpRequest){
				alert("Ajax 생성 실패");
				return false;
			}
			
			// true - 비동기 , false - 동기 
			httpRequest.open('GET',"getSampleList?name=안녕&age=15", true);
			httpRequest.onreadystatechange = setContents;
			httpRequest.send();
			console.log('send 호출 완료');
		};
		
		function setContents(){
			console.log(httpRequest.readyState);
			/*
			 0(uninitialized) - (request가 초기화 되지 않음)
			 1(loading)		 - (서버와의 연결이 성사됨)
			 2(loaded)		 - (서버가 request를 받음)
			 3(interactive)  - (request(요청)을 처리하는 중)
			 4(complete)     - (request에 대한 처리가 끝났으며 응답받은 데이터를 사용할 준비가 완료됨)
			*/
			if(httpRequest.readyState === 4){
				console.log(httpRequest.status);
				// httpRequest.status 응답 상태코드
				if(httpRequest.status === 200){
					// 응답이 완료되었고 정상처리됨.
					let str = httpRequest.responseText;
					console.log(str);
					
					// JSON 형식으로 작성된 문자열 데이터를
					// JavaScript Object로 변환
					let obj = JSON.parse(str);
					console.log(obj);
					
					let html = "<table border=1>";
					html += "<tr><th>이름</th><th>나이</th></tr>";
					for(let i = 0; i < obj.length; i++){
						html += "<tr><th>"+obj[i].name+"</th><th>"+obj[i].age+"</th></tr>";
					}
					html += "</table>";
					
					document.getElementById("box").innerHTML = html;
					
				} //end 200 status
			}
		}
	</script>
</body>
</html>








