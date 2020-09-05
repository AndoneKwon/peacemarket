package com.hainum.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.hainum.chat.payload.ChatMessage;

@Service
@RequiredArgsConstructor
public class KafkaChatReceiver {

	private final SimpMessagingTemplate template;
	
	@KafkaListener(id = "main-listener", topics = "test")
	public void receive(ChatMessage message) {
		
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
		
	}
	

}
