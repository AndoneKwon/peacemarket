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

    @Id
    String id;
    private int userId;
    private String roomnum;
    private String message;
    private String createdAt = LocalDate.now().toString();

    MessageDoc(int userId, String roomnum, String message){
        this.userId=userId;
        this.roomnum=roomnum;
        this.message=message;
    }
}
