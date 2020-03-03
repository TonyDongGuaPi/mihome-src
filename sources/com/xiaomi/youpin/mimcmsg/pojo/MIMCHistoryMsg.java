package com.xiaomi.youpin.mimcmsg.pojo;

import java.io.Serializable;
import java.util.List;

public class MIMCHistoryMsg implements Serializable {
    private String appId;
    private List<MIMCCustomMsg> messages;
    private int row;
    private String timestamp;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public List<MIMCCustomMsg> getMessages() {
        return this.messages;
    }

    public void setMessages(List<MIMCCustomMsg> list) {
        this.messages = list;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int i) {
        this.row = i;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }
}
