package com.hainum.chat.service;

import com.hainum.chat.payload.MessageDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatSender {

	private final KafkaTemplate<String, MessageDto> kafkaTemplate;
	Properties props = new Properties();


	public void send(String topic, MessageDto data) {
		props.put("bootstrap.servers","localhost:9092");
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

		Producer<String,String> producer = new KafkaProducer<String, String>(props);
		producer.send(new ProducerRecord<String, String>("kafka-chatting","Apache Kafka is a distributed streaming platform"));
		producer.close();
		//kafkaTemplate.send(topic, data);// send to react clients via websocket(STOMP)
	}

	

}
