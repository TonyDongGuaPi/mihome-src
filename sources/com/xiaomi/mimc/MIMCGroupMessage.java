package com.xiaomi.mimc;

public class MIMCGroupMessage implements Comparable {

    /* renamed from: a  reason: collision with root package name */
    private String f11153a;
    private long b;
    private long c;
    private String d;
    private String e;
    private long f;
    private byte[] g;
    private String h;

    public MIMCGroupMessage(String str, long j, String str2, String str3, long j2, byte[] bArr, long j3) {
        this.f11153a = str;
        this.b = j;
        this.c = j3;
        this.d = str2;
        this.e = str3;
        this.f = j2;
        this.g = bArr;
    }

    public MIMCGroupMessage(String str, long j, String str2, String str3, long j2, byte[] bArr, long j3, String str4) {
        this(str, j, str2, str3, j2, bArr, j3);
        this.h = str4;
    }

    public String a() {
        return this.f11153a;
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

    public long f() {
        return this.f;
    }

    public byte[] g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String toString() {
        return "packetId:" + this.f11153a + " sequence:" + this.b + " timestamp:" + this.c + " fromAccount:" + this.d + " fromResource:" + this.e + " topicId:" + this.f + " bizType:" + this.h + " payload:" + this.g;
    }

    public int compareTo(Object obj) {
        return (int) (this.b - ((MIMCGroupMessage) obj).b);
    }
}
