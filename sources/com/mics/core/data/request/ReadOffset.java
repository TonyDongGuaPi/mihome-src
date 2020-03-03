package com.mics.core.data.request;

import com.mics.core.MiCS;

public class ReadOffset {
    private long maxReadMsgId;
    private String roomId;
    private String userId = MiCS.a().n();

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public long getMaxReadMsgId() {
        return this.maxReadMsgId;
    }

    public void setMaxReadMsgId(long j) {
        this.maxReadMsgId = j;
    }
}
