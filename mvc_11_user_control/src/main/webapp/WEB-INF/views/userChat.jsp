<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- userChat.jsp -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
	.chatBox{
		height:200px;
		overflow-y:scroll;
		border:1px solid #ccc;
		border-radius:5px; 
	}
	
	.chatWrap {
		margin: 3px;
		margin-bottom: 3px;
	}
	
	.chatWrap img {
		width: 30px;
		height: 30px;
		border-radius: 15px;
		border: 1px solid black;
	}
</style>
<div>
	<h1>CHAT PAGE - ${userInfo.u_name}</h1>
	<div class="chatBox" id="data">
		<!-- 채팅 내용 출력 -->
	</div>
	<br/>
	<div>
		<div><input type="text" id="message"/></div>
		<div><input type="button" id="sendBtn" value="SEND"/></div>
	</div>
</div>

<script>

	var webSocket = new WebSocket("${path}/sobangCha");
	
	webSocket.onopen = function(){
		console.log("연결완료");
	}
	
	webSocket.onclose = function(){
		console.log("server 와 연결 끊김");
		webSocket.close();
	};
	
	webSocket.onmessage = function(msgData){
		console.log(msgData);
		let message = msgData.data;
		console.log(message);
		let msgObj = JSON.parse(message);
		console.log(msgObj);
		
		let str = "<div class='chatWrap'>";
		if (msgObj.u_profile != null && msgObj.u_profile != '') {
			str += "<img src='${path}/displayFile?fileName="+msgObj.u_profile+"' />";
		} else {
			str += "<img src='${path}/resources/img/profile.jpg' />";
		}
		str += "&nbsp";
		str += msgObj.u_name + " : " + msgObj.message;
		str += "</div>";
		
		$("#data").append(str);
		
		$("#data").focus();
		let scrollHeight = $("#data").prop("scrollHeight");
		$("#data").scrollTop(scrollHeight);
		$("#message").focus();
	}
	
	function sendMessage(){
		
		let msg = $("#message").val();
		
		let msgObj = {
			u_id : '${userInfo.u_id}',
			u_name : '${userInfo.u_name}',
			u_profile : '${userInfo.u_profile}',
			message : msg
		};
		
		webSocket.send(JSON.stringify(msgObj));
		
		$("#message").val("");
		$("#message").focus();
	}
	
	// send 버튼 클릭시 작성된 메세지 전송
	$("#sendBtn").click(function(){
		sendMessage();
	});
	
	// message 입력필드에 Enter가 눌러지면 메세지 전송
	$("#message").keydown(function(e){
		if(e.keyCode == 13){
			sendMessage();		
		}
	});

</script>
</body>
</html>





