package com.bitc.chat.handler;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bitc.chat.vo.ChatVO;

public class UserChatHandler extends TextWebSocketHandler {

	private Map<WebSocketSession, ChatVO> sessionMap = new Hashtable<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		ChatVO vo = new ChatVO();
		vo.setSessionId(sessionId);
		sessionMap.put(session, vo);
		
		System.out.println("Connection : " + sessionMap);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String sessionId = session.getId();
		String msg = message.getPayload();
		
		System.out.println("sessionId : " + sessionId);
		System.out.println("msg : " + msg);
		
		for(WebSocketSession ses : sessionMap.keySet()) {
			ses.sendMessage(new TextMessage(msg));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionMap.remove(session);
	}

	
	
}
