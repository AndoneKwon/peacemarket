package com.hainum.chat.payload;

import com.hainum.chat.model.MessageDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Getter
@Setter
public class ChattingHistoryDto {
    private int userId;
    private int roomnum;
    private String message;
    private String createdAt = LocalDate.now().toString();

    public ChattingHistoryDto(MessageDoc entity){
        this.userId = entity.getUserId();
        this.roomnum = entity.getRoomnum();
        this.message = entity.getMessage();
        this.createdAt = entity.getCreatedAt();
    }

}