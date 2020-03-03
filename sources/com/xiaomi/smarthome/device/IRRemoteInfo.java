package com.xiaomi.smarthome.device;

public class IRRemoteInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f14856a;
    public String b;
    public int c;
    public String d;

    public IRRemoteInfo() {
    }

    public IRRemoteInfo(String str, String str2, int i, String str3) {
        this.f14856a = str;
        this.b = str2;
        this.c = i;
        this.d = str3;
    }

    public String toString() {
        return IRRemoteInfo.class.getSimpleName() + ":mId=" + this.f14856a + ",mName=" + this.b + ",mType=" + this.c + ",mAction=" + this.d;
    }
}
