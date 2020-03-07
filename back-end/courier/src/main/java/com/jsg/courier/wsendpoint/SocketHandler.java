package com.jsg.courier.wsendpoint;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsg.courier.datatypes.Message;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage messageJson) throws Exception {
//		System.out.println(message.getPayload());
//		System.out.println(message);
		Message message = objectMapper.readValue(messageJson.getPayload(), Message.class);
		message.print();
		broadcast(message);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		sessions.add(session);
		System.out.println("WebSocket connection established.");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
		sessions.remove(session);
		System.out.println("WebSocket connection closed.");
	}
	
	public void broadcast(Message message) throws Exception {
		for(WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		}
	}
}