package com.minicare.model;

public class Message {
    private int messageId;
    private int conversationId;
    private String content;

    // Check whether we should have Some others also here - CHECK DATABASE.

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
}
