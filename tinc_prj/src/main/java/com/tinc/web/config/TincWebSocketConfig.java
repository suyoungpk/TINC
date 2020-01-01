package com.tinc.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.tinc.web.ws.handler.ChatWebSocketHandler;

@Configuration
@ComponentScan(basePackages = "com.tinc.web.ws.handler")
@EnableWebSocket
public class TincWebSocketConfig implements WebSocketConfigurer
{
	@Autowired
	private ChatWebSocketHandler chatSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
	{
		// TODO Auto-generated method stub
		registry.addHandler(chatSocketHandler, "tinc/chat");
	}

}
