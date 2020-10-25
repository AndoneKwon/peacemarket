package com.hainum.chat.model;

import com.hainum.chat.payload.MessageDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageMongoDBRepository extends MongoRepository<MessageDoc, String> {
    List<MessageDoc> findAllByRoomnum(int roommum);
}
