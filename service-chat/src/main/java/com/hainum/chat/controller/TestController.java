package com.hainum.chat.controller;

import com.hainum.chat.payload.ChatRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test1")
    public void test1(){
        ChatRoom chatRoom = ChatRoom.create("sdf");
    }



}
