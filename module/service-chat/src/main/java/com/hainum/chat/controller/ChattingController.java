package com.hainum.chat.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.hainum.chat.payload.ChattingHistoryDto;
import com.hainum.chat.payload.ChattingHistoryRequestDto;
import com.hainum.chat.payload.MessageDto;
import com.hainum.chat.service.KafkaChatHistoryService;
import com.hainum.chat.service.KafkaChatReceiver;
import com.hainum.chat.service.KafkaChatSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ChattingController {

    private final KafkaChatSender sender;

    private final KafkaChatReceiver receiver;

    private final KafkaChatHistoryService history;

    private static String BOOT_TOPIC = "kafka-chatting";


    //@RequestHeader(value = "authorization") String headers
    @PostMapping("/message")
    public void sendMessage(@RequestBody MessageDto message) throws IOException {
        sender.send(BOOT_TOPIC, message);
    }

    @PostMapping("/history")
    public List<ChattingHistoryDto> getChattingHistory(@RequestBody ChattingHistoryRequestDto roomNum) throws Exception {
        return history.searchByRoomNum(roomNum);//이전 채팅 데이터 불러오
    }
}