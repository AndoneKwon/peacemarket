package com.hainum.chat.service;

import com.hainum.chat.model.MessageDoc;
import com.hainum.chat.model.MessageMongoDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaChatHistory {
    private final MessageMongoDBRepository messageMongoDBRepository;

    public List<MessageDoc> searchByRoomNum(int roomNum){
        return messageMongoDBRepository.findByRoomNum(roomNum);
    }
}
