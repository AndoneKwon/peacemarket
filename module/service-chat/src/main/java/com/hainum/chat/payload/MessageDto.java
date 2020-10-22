package com.hainum.chat.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class MessageDto implements Serializable {
    private String message;
    private int user;
    private LocalDate timeStamp;
    private int roomNum;

    public MessageDto(String message, int user, int roomNum) {
        this.user = user;
        this.message = message;
        this.roomNum=roomNum;
    }
}