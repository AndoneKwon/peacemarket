package com.hainum.chat.config;

import com.hainum.chat.payload.SocketSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.print.DocFlavor;

@Configuration
public class RedisConfig {

    private final String Hostname;
    private final int Port;

    public RedisConfig(
            @Value("${spring.redis.host}") String hostname,
            @Value("${spring.redis.port}") int port
    ){
        this.Hostname=hostname;
        this.Port=port;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }


}
