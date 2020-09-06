package com.hainum.chat.service;

import com.hainum.chat.payload.ChattingMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatSender {

	private final KafkaTemplate<String, ChattingMessage> kafkaTemplate;


	public void send(String topic, ChattingMessage data) {
		kafkaTemplate.send(topic, data);// send to react clients via websocket(STOMP)
	}
	

}
