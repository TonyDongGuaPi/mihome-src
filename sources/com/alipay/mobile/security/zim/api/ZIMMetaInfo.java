package com.alipay.mobile.security.zim.api;

import com.taobao.weex.el.parse.Operators;

public class ZIMMetaInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f1052a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public String getZimVer() {
        return this.h;
    }

    public void setZimVer(String str) {
        this.h = str;
    }

    public String getApdidToken() {
        return this.f1052a;
    }

    public void setApdidToken(String str) {
        this.f1052a = str;
    }

    public String getDeviceType() {
        return this.b;
    }

    public void setDeviceType(String str) {
        this.b = str;
    }

    public String getDeviceModel() {
        return this.c;
    }

    public void setDeviceModel(String str) {
        this.c = str;
    }

    public String getAppName() {
        return this.d;
    }

    public void setAppName(String str) {
        this.d = str;
    }

    public String getAppVersion() {
        return this.e;
    }

    public void setAppVersion(String str) {
        this.e = str;
    }

    public String getOsVersion() {
        return this.f;
    }

    public void setOsVersion(String str) {
        this.f = str;
    }

    public String getBioMetaInfo() {
        return this.g;
    }

    public void setBioMetaInfo(String str) {
        this.g = str;
    }

    public String toString() {
        return "ZIMMetaInfo{apdidToken='" + this.f1052a + Operators.SINGLE_QUOTE + ", deviceType='" + this.b + Operators.SINGLE_QUOTE + ", deviceModel='" + this.c + Operators.SINGLE_QUOTE + ", appName='" + this.d + Operators.SINGLE_QUOTE + ", appVersion='" + this.e + Operators.SINGLE_QUOTE + ", osVersion='" + this.f + Operators.SINGLE_QUOTE + ", bioMetaInfo='" + this.g + Operators.SINGLE_QUOTE + ", zimVer='" + this.h + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
