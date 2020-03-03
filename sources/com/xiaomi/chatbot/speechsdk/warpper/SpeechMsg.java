package com.xiaomi.chatbot.speechsdk.warpper;

import com.google.gson.JsonObject;
import com.xiaomi.chatbot.speechsdk.common.Utils;

public class SpeechMsg {
    private Meta meta;
    private JsonObject request;
    private JsonObject response;

    public static SpeechMsg fromJson(String str) {
        return (SpeechMsg) Utils.GSON.fromJson(str, SpeechMsg.class);
    }

    public String toString() {
        return Utils.GSON.toJson((Object) this);
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta2) {
        this.meta = meta2;
    }

    public JsonObject getRequest() {
        return this.request;
    }

    public void setRequest(JsonObject jsonObject) {
        this.request = jsonObject;
    }

    public JsonObject getResponse() {
        return this.response;
    }

    public void setResponse(JsonObject jsonObject) {
        this.response = jsonObject;
    }
}
