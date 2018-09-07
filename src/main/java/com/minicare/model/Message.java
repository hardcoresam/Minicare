package com.minicare.model;
import java.sql.Time;

public class Message {
    private int messageId;
    private int conversationId;
    private String content;
    private Time time;

    //Ask pranav to use a DTO or this only bcoz we are adding firstName field here which is not there in message table.

    private String firstName;

    public Message() {}

    public Message(int messageId, int conversationId, String content) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.content = content;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
