package com.alipay.sdk.auth;

public class APAuthInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f1082a;
    private String b;
    private String c;
    private String d;

    public APAuthInfo(String str, String str2, String str3) {
        this(str, str2, str3, (String) null);
    }

    public APAuthInfo(String str, String str2, String str3, String str4) {
        this.f1082a = str;
        this.b = str2;
        this.d = str3;
        this.c = str4;
    }

    public String getAppId() {
        return this.f1082a;
    }

    public String getProductId() {
        return this.b;
    }

    public String getPid() {
        return this.c;
    }

    public String getRedirectUri() {
        return this.d;
    }
}
