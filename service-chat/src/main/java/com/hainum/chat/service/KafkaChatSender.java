package com.hainum.chat.service;

import com.hainum.chat.payload.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatSender {

	private final KafkaTemplate<String, ChatMessage> kafaKaTemplate;
	
	
	public void send(String topic, ChatMessage message) {
		log.info(message.toString());
		kafaKaTemplate.send(topic, message);
	}
	

}
