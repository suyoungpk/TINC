package com.tinc.web.ws.handler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler
{
private List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		InetSocketAddress clientAddress = session.getRemoteAddress();
		
		System.out.printf("Accepted connection from: {%s}:{%s}\n", clientAddress.getHostString(), clientAddress.getPort());
		
		sessions.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
	{
		InetSocketAddress clientAddress = session.getRemoteAddress();
		
		System.out.printf("Connection closed by: {%s}:{%s}\n", clientAddress.getHostString(), clientAddress.getPort());
		
		sessions.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
	{
		System.out.println("rcvd: " + message);
		
		for(WebSocketSession s : sessions)
		{
			s.sendMessage(new TextMessage(message.getPayload()));			
		}
		
	}
}
