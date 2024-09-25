package com.bitc.chat.handler;

import java.util.List;
import java.util.Vector;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TextHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new Vector<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId());
		sessionList.add(session);
		System.out.println("연결된 사용자 수 : " + sessionList.size());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String sessionId = session.getId();
		String msg = message.getPayload();
		System.out.printf("id : %s , message : %s %n", sessionId, msg);
		
		for(WebSocketSession ses : sessionList) {
			ses.sendMessage(new TextMessage(msg));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionId = session.getId();
		System.out.printf("close id : %s %n", sessionId);
		sessionList.remove(session);
	}

	
	
}
