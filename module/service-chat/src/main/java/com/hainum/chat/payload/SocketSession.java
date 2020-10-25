package com.hainum.chat.payload;

import java.io.Serializable;

public class SocketSession implements Serializable {
    private String sessionKey;

    SocketSession(String sessionKey){
        this.sessionKey=sessionKey;
    }
}
