package com.hainum.chat.service;

import com.hainum.chat.model.MessageDoc;
import com.hainum.chat.model.MessageMongoDBRepository;
import com.hainum.chat.payload.ChattingHistoryDto;
import com.hainum.chat.payload.ChattingHistoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KafkaChatHistoryService {
    private final MongoTemplate mongoTemplate;
    private final MessageMongoDBRepository messageMongoDBRepository;

    public List<ChattingHistoryDto> searchByRoomNum(ChattingHistoryRequestDto roomNum){
        Criteria criteria = Criteria.where("roomnum").is(roomNum.getRoomNum());
        Query query = new Query(criteria);
        System.out.println("check");
        return mongoTemplate.find(query,MessageDoc.class).stream()
                .map(ChattingHistoryDto::new)
                .collect(Collectors.toList());
    }
}
