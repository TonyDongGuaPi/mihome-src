package com.mics.core.data.request;

import java.util.concurrent.atomic.AtomicInteger;

public class SendText {
    public static final String PIC = "PIC";
    public static final String TEXT = "TEXT";
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private String connectionId;
    private String content;
    private String msgType;
    private String roomId;
    private String subType;
    private String umsgId = (Long.toString(System.currentTimeMillis(), 32) + String.valueOf(generateId()));
    private String userId;

    private static int generateId() {
        int i;
        int i2;
        do {
            i = atomicInteger.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!atomicInteger.compareAndSet(i, i2));
        return i;
    }

    public String getUmsgId() {
        return this.umsgId;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getConnectionId() {
        return this.connectionId;
    }

    public void setConnectionId(String str) {
        this.connectionId = str;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String str) {
        this.subType = str;
    }
}
