package com.xiaomi.chatbot.speechsdk.common;

import com.google.gson.annotations.SerializedName;

public class AsrParam {
    private static final AsrParam INSTANCE = new AsrParam();
    private Boolean continuous = false;
    @SerializedName("punctuation")
    private Boolean punctuation;
    private Boolean vad;

    public static AsrParam getInstance() {
        return INSTANCE;
    }

    public Boolean getVad() {
        return this.vad;
    }

    public void setVad(Boolean bool) {
        this.vad = bool;
    }

    public Boolean getPunctuation() {
        return this.punctuation;
    }

    public void setPunctuation(Boolean bool) {
        this.punctuation = bool;
    }

    public Boolean getContinuous() {
        return this.continuous;
    }

    public void setContinuous(Boolean bool) {
        this.continuous = bool;
    }
}
