package com.hainum.chat.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hainum.chat.payload.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class KafkaChatReceiver {

	Properties props = new Properties();
	private final SimpMessagingTemplate webSocket;

	@KafkaListener(topics = "kafka-chatting",groupId = "test-consumer-group")
	public void consumer(String message) throws Exception {
		props.put("bootstrap.servers","localhost:9092");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		ObjectMapper objectMapper = new ObjectMapper();
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		String json = objectMapper.writeValueAsString(message);


		//this.template.convertAndSend("/topic/public", json);
	}

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public void SendMessage(String message){
		webSocket.convertAndSend("topics/api",message);
	}

}
