package com.xiaomi.mimc;

import com.taobao.weex.el.parse.Operators;

public class MIMCServerAck {

    /* renamed from: a  reason: collision with root package name */
    private String f11155a;
    private long b;
    private long c;
    private String d;

    public MIMCServerAck(String str, long j, long j2, String str2) {
        this.f11155a = str;
        this.b = j;
        this.c = j2;
        this.d = str2;
    }

    public String a() {
        return this.f11155a;
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

    public String toString() {
        return "MIMCServerAck{packetId='" + this.f11155a + Operators.SINGLE_QUOTE + ", sequence=" + this.b + ", timestamp=" + this.c + ", desc='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
