package com.mi.global.bbs.model;

import java.io.Serializable;

public class PostUrlContent implements Serializable {
    public String content;
    public String href;

    public PostUrlContent() {
    }

    public PostUrlContent(String str, String str2) {
        this.content = str;
        this.href = str2;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String str) {
        this.href = str;
    }
}
