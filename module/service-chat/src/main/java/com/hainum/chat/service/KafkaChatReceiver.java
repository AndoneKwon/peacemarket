package com.hainum.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hainum.chat.payload.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class KafkaChatReceiver {

	private final SimpMessagingTemplate template;

	@KafkaListener(id = "main-listener", topics = "kafka-chatting")
	public void receive(MessageDto message) throws Exception {
		HashMap<String, String> msg = new HashMap<>();
		msg.put("timestamp", Long.toString(message.getTimeStamp()));
		msg.put("message", message.getMessage());
		msg.put("author", message.getUser());

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(msg);

		this.template.convertAndSend("/topic/public", json);
	}
}
