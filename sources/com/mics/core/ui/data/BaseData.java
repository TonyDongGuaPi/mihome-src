package com.mics.core.ui.data;

public class BaseData {
    private CharSequence content;
    private String id;
    private long time;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public long getTime() {
        return this.time;
    }

    public void setContent(CharSequence charSequence) {
        this.content = charSequence;
    }

    public CharSequence getContent() {
        return this.content;
    }
}
