package com.tinc.web.ws.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinc.web.entity.ChatMessage;
import com.tinc.web.service.tinc.TincChatRoomService;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler 
{

	 private static TincChatRoomService service;

	 static {
		 service = new TincChatRoomService();
	 }
	 
	int roomId;

	private ObjectMapper objectMapper;
	public ChatWebSocketHandler() {
		int roomId=0;
		objectMapper = new ObjectMapper();		 
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
	{
		service.delSession(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
	{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		Date time = new Date();
		String time1 = format1.format(time);
		String time2 = format2.format(time);
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(message.getPayload());
		JSONObject data = (JSONObject) obj;		
			data.put("date",time1);
			data.put("time",time2);
		System.out.println(data);
		ChatMessage chatMessage = objectMapper.readValue(data.toJSONString(), ChatMessage.class);
		int roomId=chatMessage.getChatId();
		//System.out.println(roomId);
			
		if(chatMessage.getType().equals("enter")) 
			service.addSession(roomId,session);
		else service.sendMessage(roomId,chatMessage);
	}
		
}
