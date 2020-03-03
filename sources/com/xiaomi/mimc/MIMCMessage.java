package com.xiaomi.mimc;

public class MIMCMessage implements Comparable {

    /* renamed from: a  reason: collision with root package name */
    private String f11154a;
    private long b;
    private long c;
    private String d;
    private String e;
    private String f;
    private String g;
    private byte[] h;
    private String i;

    public MIMCMessage(String str, long j, String str2, String str3, String str4, String str5, byte[] bArr, long j2) {
        this.f11154a = str;
        this.b = j;
        this.c = j2;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = bArr;
    }

    public MIMCMessage(String str, long j, String str2, String str3, String str4, String str5, byte[] bArr, long j2, String str6) {
        this(str, j, str2, str3, str4, str5, bArr, j2);
        this.i = str6;
    }

    public String a() {
        return this.f11154a;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public byte[] h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public String toString() {
        return "packetId:" + this.f11154a + " sequence:" + this.b + " timestamp:" + this.c + " fromAccount:" + this.d + "fromResource:" + this.e + " toAccount:" + this.f + "toResource" + this.g + " bizType:" + this.i + " payload:" + this.h;
    }

    public int compareTo(Object obj) {
        return (int) (this.b - ((MIMCMessage) obj).b);
    }
}
