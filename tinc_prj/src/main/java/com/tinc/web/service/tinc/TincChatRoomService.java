package com.tinc.web.service.tinc;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TincChatRoomService {
	
	private static final ObjectMapper objectMapper= new ObjectMapper();
    private static Map<Integer, List<WebSocketSession>> chatRooms= new ConcurrentHashMap<>();
  
    
//	 public TincChatRoomService() {
//		// objectMapper = new ObjectMapper();
//		 chatRooms = new LinkedHashMap<>();
//	}
    public static List<WebSocketSession> getChatRoomById(int roomId) {
        return chatRooms.get(roomId);
    }
 
    public static void addSession(int roomId,WebSocketSession session) {
    	if(chatRooms.get(roomId) == null) {
    		List<WebSocketSession> list = new CopyOnWriteArrayList<>();
    		list.add(session);
    		chatRooms.put(roomId,list);
    	}else 
    		getChatRoomById(roomId).add(session);
    	System.out.println("접속완료");
    }
    public static <T> void sendMessage(int roomId, T message) {
        try {
        	System.out.println("service 여기까진 되나");
        	List<WebSocketSession> rooms = getChatRoomById(roomId);
    		for (WebSocketSession s:rooms) //objectMapper.writeValueAsString(message)
    			s.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
            //session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
          //  log.error(e.getMessage(), e);
        }
    }
	public static void delSession(WebSocketSession session) {
		for (List<WebSocketSession> rooms: chatRooms.values()) 
			rooms.remove(session);
	}
}
