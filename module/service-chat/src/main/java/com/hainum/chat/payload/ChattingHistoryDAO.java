package com.hainum.chat.payload;

import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class ChattingHistoryDAO {

    // A simple cache for temporarily storing chat data
    private final Cache<UUID, MessageDto> chatHistoryCache = CacheBuilder
            .newBuilder().maximumSize(20).expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public void save(MessageDto chatObj) {
        this.chatHistoryCache.put(UUID.randomUUID(), chatObj);
    }

    public List<MessageDto> get() {
        return chatHistoryCache.asMap().values().stream()
                .sorted(Comparator.comparing(MessageDto::getTimeStamp))
                .collect(Collectors.toList());
    }

}