package com.mi.global.bbs.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class TitleMenu implements Serializable {
    private static final long serialVersionUID = -1495756615508283146L;
    @SerializedName("event_json")
    private String shareEvent;
    @SerializedName("tid")
    private String tid;
    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private String value;

    public String getType() {
        return this.type;
    }

    public TitleMenu(String str, String str2) {
        this.type = str;
        this.value = str2;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getShareEvent() {
        return this.shareEvent;
    }

    public void setShareEvent(String str) {
        this.shareEvent = str;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
    }
}
