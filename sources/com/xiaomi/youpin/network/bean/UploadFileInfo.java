package com.xiaomi.youpin.network.bean;

public class UploadFileInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f23662a;
    private String b;
    private byte[] c;
    private String d;
    private String e;

    public UploadFileInfo a(byte[] bArr) {
        this.c = bArr;
        return this;
    }

    public String a() {
        return this.f23662a;
    }

    public UploadFileInfo a(String str) {
        this.f23662a = str;
        return this;
    }

    public String b() {
        return this.b;
    }

    public UploadFileInfo b(String str) {
        this.b = str;
        return this;
    }

    public byte[] c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public UploadFileInfo c(String str) {
        this.d = str;
        return this;
    }

    public String e() {
        return this.e;
    }

    public UploadFileInfo d(String str) {
        this.e = str;
        return this;
    }
}
