package com.huawei.hms.support.api.client;

public class SubAppInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f5880a;

    public SubAppInfo(SubAppInfo subAppInfo) {
        if (subAppInfo != null) {
            this.f5880a = subAppInfo.getSubAppID();
        }
    }

    public SubAppInfo(String str) {
        this.f5880a = str;
    }

    public String getSubAppID() {
        return this.f5880a;
    }

    public void setSubAppID(String str) {
        this.f5880a = str;
    }
}
