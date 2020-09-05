package com.hainum.chat.payload;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage implements Serializable {
	
	public enum MessageType{
		ENTER, TALK
	}
	
	public ChatMessage() {}
	
	
	private String type;
	private String roomId;
	private String sender;
	private String message;

}
