package com.tinc.web.ws.handler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinc.web.entity.ChatMessage;
import com.tinc.web.service.ChatRoomService;
import com.tinc.web.service.tinc.TincChatRoomService;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler 
{
	 //@Autowired 
	 private static TincChatRoomService service;

	 static {
		 service = new TincChatRoomService();
	 }
	 
	int roomId;
	//private List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();

	private ObjectMapper objectMapper;
	public ChatWebSocketHandler() {
		int roomId=0;
		
//		if(service == null)
//		 service = new TincChatRoomService();
		 
		objectMapper = new ObjectMapper();		 
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		//InetSocketAddress clientAddress = session.getRemoteAddress();
		
		//System.out.printf("Accepted connection from: {%s}:{%s}\n", clientAddress.getHostString(), clientAddress.getPort());

		//newSessions.add(session);
		
		//sessions.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
	{
		//InetSocketAddress clientAddress = session.getRemoteAddress();
		
		//System.out.printf("Connection closed by: {%s}:{%s}\n", clientAddress.getHostString(), clientAddress.getPort());
		
		//sessions.remove(session);
		service.delSession(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(message.getPayload());
		JSONObject data = (JSONObject) obj;
		System.out.println(data);
		
		ChatMessage chatMessage = objectMapper.readValue(data.toJSONString(), ChatMessage.class);
		int roomId=chatMessage.getChatId();
		System.out.println(roomId);
		//System.out.println(chatMessage.getType());
		if(chatMessage.getType().equals("enter")) {
			System.out.println(chatMessage.getType());
			service.addSession(roomId,session);
		}else {
			System.out.println(chatMessage.getType());
			service.sendMessage(roomId,chatMessage);
//			List<WebSocketSession> rooms = service.getChatRoomById(roomId);
//    		for (WebSocketSession s:rooms) //objectMapper.writeValueAsString(message)
//    			s.sendMessage(new TextMessage("제발되라고오오오오오오"));
			System.out.println("채팅룸으로보냈음");
		}
	}
		
}
