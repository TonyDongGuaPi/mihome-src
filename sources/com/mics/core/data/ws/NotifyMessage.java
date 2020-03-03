package com.mics.core.data.ws;

public class NotifyMessage {
    private int code;
    private String msg;
    private long requestId;

    public long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(long j) {
        this.requestId = j;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
