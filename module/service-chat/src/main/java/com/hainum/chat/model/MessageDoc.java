package com.hainum.chat.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "post-capston")
public class MessageDoc {
    private int userId;
    private int roomNum;
    private String message;
    private String createdAt = LocalDate.now().toString();

    @Builder
    MessageDoc(int userId, int roomNum, String message){
        this.userId=userId;
        this.roomNum=roomNum;
        this.message=message;
    }
}
