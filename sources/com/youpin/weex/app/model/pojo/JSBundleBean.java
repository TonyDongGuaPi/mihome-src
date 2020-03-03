package com.youpin.weex.app.model.pojo;

public class JSBundleBean {
    public String htmlUrl;
    public int id;
    public String jsVersion;
    public String jsbundleUrl;
    public String md5;
    public long time;

    public String getJsVersion() {
        return this.jsVersion;
    }

    public void setJsVersion(String str) {
        this.jsVersion = str;
    }

    public String getJsbundleUrl() {
        return this.jsbundleUrl;
    }

    public void setJsbundleUrl(String str) {
        this.jsbundleUrl = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public void setHtmlUrl(String str) {
        this.htmlUrl = str;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }
}
