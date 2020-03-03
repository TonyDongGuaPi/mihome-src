package com.miui.tsmclient.entity.eventbus;

public class DefaultCardEvent {
    private String mDefaultCardAId;

    public DefaultCardEvent(String str) {
        this.mDefaultCardAId = str;
    }

    public String getDefaultCardAId() {
        return this.mDefaultCardAId;
    }
}
