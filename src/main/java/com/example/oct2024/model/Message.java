package com.example.oct2024.model;

/**
 *  {
 *      "msgId" : 123,
 *      "msgString" : "TEST MESSAGE"
 *  }
 */

public class Message {
    private int msgId;
    private String msgString;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getMsgString() {
        return msgString;
    }

    public void setMsgString(String msgString) {
        this.msgString = msgString;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId=" + msgId +
                ", msgString='" + msgString + '\'' +
                '}';
    }
}
