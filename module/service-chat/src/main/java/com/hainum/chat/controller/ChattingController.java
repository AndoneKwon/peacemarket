package com.hainum.chat.controller;


import com.hainum.chat.payload.ChattingHistoryDAO;
import com.hainum.chat.payload.MessageDto;
import com.hainum.chat.service.KafkaChatHistory;
import com.hainum.chat.service.KafkaChatReceiver;
import com.hainum.chat.service.KafkaChatSender;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ChattingController {

    private final KafkaChatSender sender;

    private final KafkaChatReceiver receiver;

    private final ChattingHistoryDAO chattingHistoryDAO;

    private final KafkaChatHistory history;

    private static String BOOT_TOPIC = "kafka-chatting";

    @PostMapping("/message")
    public void sendMessage(@RequestHeader(value = "authorization") String headers, @RequestBody MessageDto message){
        message.setTimeStamp(LocalDate.now());
        //chattingHistoryDAO.save(message);
        sender.send(BOOT_TOPIC, message);
    }

    @PostMapping("/history")
    public List<MessageDto> getChattingHistory(@RequestBody Long roomNum) throws Exception {
        return history.getChatHistory(roomNum);;
    }
/*
    @MessageMapping("/file")
    @SendTo("/topic/chatting")
    public ChattingMessage sendFile(ChattingMessage message) throws Exception {
        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
    }
*/
}