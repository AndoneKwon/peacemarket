package com.hainum.chat.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MessageDto implements Serializable {
    private String message;
    private String user;
    private Long timeStamp;

    private String fileName;
    private String rawData;

    public MessageDto() {
    }

    public MessageDto(String message, String user) {
        this.user = user;
        this.message = message;
    }

    public MessageDto(String fileName, String rawData, String user) {

        this.fileName = fileName;
        this.rawData = rawData;
        this.user = user;
    }

    public MessageDto(String message) {
        this.message = message;
    }
}