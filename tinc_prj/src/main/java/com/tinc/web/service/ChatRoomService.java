package com.tinc.web.service;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public interface ChatRoomService {
	public List<WebSocketSession> getChatRoomById(int roomId);
	public void addSession(int roomId,WebSocketSession session);
    public <T> void sendMessage(int roomId, T message);
    public void delSession(WebSocketSession session);
}
