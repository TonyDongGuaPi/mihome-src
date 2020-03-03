package com.xiaomi.chatbot.speechsdk;

public class RecordResult {
    private boolean isFinal = false;
    private long recordTimestamp = System.currentTimeMillis();
    private String requestId;
    private String text = "";

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public void setFinal(boolean z) {
        this.isFinal = z;
    }

    public long getRecordTimestamp() {
        return this.recordTimestamp;
    }

    public void setRecordTimestamp(long j) {
        this.recordTimestamp = j;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }
}
