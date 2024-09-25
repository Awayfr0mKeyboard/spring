<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- chat.jsp -->

<div>
	<h1>Chat Page</h1>
	<textarea id="data" readonly rows="20" cols="50"></textarea> <br/>
	<input type="text" id="message" />
	<button id="sendBtn">SEND</button>
</div>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
	// 객체 생성 시 서버에 연결 요청
	var sock = new SockJS('${path}/chatHandler');
	
	sock.onopen = function() {
	    console.log('open');
	    sock.send('test');
	};
	
	sock.onmessage = function(message) {
		let msg = message.data;
	    console.log('message', msg);
	    $("#data").append(msg+"\n");
	};
	
	sock.onclose = function() {
	    console.log('close');
	};
	
	function sendMessage() {
		let msg = $("#message").val();
		sock.send(msg);
		$("#message").val("");
		$("#message").focus();
	}
	
	// send 버튼 클릭 시 작성된 메세지 전송
	$("#sendBtn").click(function() {
		sendMessage();
	});
	
	// message 입력 필드에 Enter가 눌러지면 메세지 전송
	$("#message").keydown(function(e) {
		if (e.keyCode == 13) {
			sendMessage();
		}
	});
	
</script>
</body>
</html>