package com.hainum.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic"); //Request subscribe
		config.setApplicationDestinationPrefixes("/app"); //Request publish
	}
	
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		//Connect Websocket path(description path)
		registry.addEndpoint("/chatting").setAllowedOrigins("*").withSockJS();
	}

}
