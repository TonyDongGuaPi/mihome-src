package com.mics.core.data.request;

public class SessionLeavingMessage extends Session {
    private String queueId;

    public String getQueueId() {
        return this.queueId;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }
}
