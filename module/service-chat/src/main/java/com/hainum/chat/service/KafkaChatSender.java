package com.hainum.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hainum.chat.config.ElasticsearchConf;
import com.hainum.chat.model.FilterDto;
import com.hainum.chat.payload.MessageDto;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaChatSender {

	private final ElasticsearchConf elasticsearchConf;
	private final KafkaTemplate<String, MessageDto> kafkaTemplate;
	Properties props = new Properties();


	public void send(String topic, MessageDto data) throws IOException {
		props.put("bootstrap.servers","localhost:9092");
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

		FilterDto filterDto = new FilterDto();
		filterDto.setText(data.getMessage());
		String[] list = {"새꺄","새끼야","씨발","씨발놈"};
		String JsonData;
		Gson gson = new Gson();
		JsonData = gson.toJson(filterDto);


		final HttpEntity httpEntity = new NStringEntity(JsonData, ContentType.APPLICATION_JSON);


		Request request = new Request("POST","/capston/_analyze");
		request.addParameter("pretty","true");
		request.setEntity(httpEntity);
		Response response = elasticsearchConf.getRestClient().performRequest(request);
		String responseBody = EntityUtils.toString(response.getEntity());
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode document = mapper.readTree(responseBody);
		JsonNode document2 = mapper.readTree(responseBody);
		int a = document.get("tokens").size();
		document2 = document.get("tokens");
		boolean change=false;


		String ab = document2.get(0).get("token").toString();
		for(int i=0;i<a;i++){
			for(int j=0;j<list.length;j++)
				if(document2.get(i).get("token").toString().replace("\"","").equals(list[j])) change=true;
		}
		if(change){
			data.setMessage("욕설이 필터링 되었습니다.");
		}
		String retVal = objectMapper.writeValueAsString(data);
		Producer<String,String> producer = new KafkaProducer<String, String>(props);
		producer.send(new ProducerRecord<String, String>("kafka-chatting", retVal));
		producer.close();
		//kafkaTemplate.send(topic, data);// send to react clients via websocket(STOMP)
	}
}
