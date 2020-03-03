package com.mics.core.data.request;

public class PullMessage {
    private int batchSize;
    private long fromMsgId;
    private String roomId;

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public long getFromMsgId() {
        return this.fromMsgId;
    }

    public void setFromMsgId(long j) {
        this.fromMsgId = j;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public void setBatchSize(int i) {
        this.batchSize = i;
    }
}
