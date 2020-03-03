package com.alipay.zoloz.android.phone.mrpc.core;

public class HttpUrlResponse extends Response {

    /* renamed from: a  reason: collision with root package name */
    private int f1188a;
    private String b;
    private long c;
    private long d;
    private String e;
    private HttpUrlHeader f;

    public HttpUrlResponse(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.f = httpUrlHeader;
        this.f1188a = i;
        this.b = str;
        this.mResData = bArr;
    }

    public int getCode() {
        return this.f1188a;
    }

    public String getMsg() {
        return this.b;
    }

    public String getCharset() {
        return this.e;
    }

    public void setCharset(String str) {
        this.e = str;
    }

    public long getCreateTime() {
        return this.c;
    }

    public void setCreateTime(long j) {
        this.c = j;
    }

    public long getPeriod() {
        return this.d;
    }

    public void setPeriod(long j) {
        this.d = j;
    }

    public HttpUrlHeader getHeader() {
        return this.f;
    }

    public void setHeader(HttpUrlHeader httpUrlHeader) {
        this.f = httpUrlHeader;
    }
}
