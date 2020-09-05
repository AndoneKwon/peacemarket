package com.hainum.chat.config;



import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.hainum.chat.payload.ChatMessage;

@EnableKafka
@Configuration
public class KafkaConfig {
	
    @Bean
    public KafkaTemplate<String, ChatMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
	
	
	//Send
    @Bean
    public ProducerFactory<String, ChatMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), null, new JsonSerializer<ChatMessage>());
    	//return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
	
    @Bean
    public Map<String, Object> producerConfigs() {

    	Map<String, Object> props = new HashMap<String, Object>();
    	
                props.put("bootstrap.servers", "localhost:9092");//kafka server ip & port
                props.put("key.serializer", StringSerializer.class);
                props.put("value.serializer", JsonSerializer.class);//Object json parser
                props.put("group.id", "spring-boot-test"); // chatting  group id
                
        return props;
    }
    
    
    //Receive
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ChatMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), null, new JsonDeserializer<>(ChatMessage.class));
    	//return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    
    @Bean
    public Map<String, Object> consumerConfigs() {

    	Map<String,Object> props = new HashMap<String, Object>();
    	
                props.put("bootstrap.servers", "localhost:9092");//kafka server ip & port
                props.put("key.deserializer", StringDeserializer.class);
                props.put("value.deserializer", JsonDeserializer.class);
                props.put("group.id", "spring-boot-test"); // chatting  group id
                
        return props;
    }

}
