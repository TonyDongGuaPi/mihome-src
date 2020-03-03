package com.xiaomi.chatbot.speechsdk.warpper;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Meta {
    private JsonObject asr;
    private List<Cmd> cmds;
    @SerializedName("request_id")
    private String requestId;
    private JsonObject tts;
    private MetaType type;

    public Meta() {
    }

    public Meta(MetaType metaType) {
        this.type = metaType;
    }

    public MetaType getType() {
        return this.type;
    }

    public void setType(MetaType metaType) {
        this.type = metaType;
    }

    public List<Cmd> getCmds() {
        return this.cmds;
    }

    public void setCmds(List<Cmd> list) {
        this.cmds = list;
    }

    public JsonObject getAsr() {
        return this.asr;
    }

    public void setAsr(JsonObject jsonObject) {
        this.asr = jsonObject;
    }

    public JsonObject getTts() {
        return this.tts;
    }

    public void setTts(JsonObject jsonObject) {
        this.tts = jsonObject;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }
}
