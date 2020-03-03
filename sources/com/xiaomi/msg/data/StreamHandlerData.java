package com.xiaomi.msg.data;

import com.xiaomi.msg.data.XMDPacket;

public class StreamHandlerData implements Comparable<StreamHandlerData> {

    /* renamed from: a  reason: collision with root package name */
    private long f12085a;
    private short b;
    private int c;
    private byte[] d;
    private boolean e;
    private XMDPacket.DataPriority f;
    private XMDPacket.PayLoadType g;
    private long h;
    private short i;

    public StreamHandlerData(long j, short s, int i2, byte[] bArr, boolean z, XMDPacket.DataPriority dataPriority, XMDPacket.PayLoadType payLoadType, Short sh) {
        this.f12085a = j;
        this.b = s;
        this.c = i2;
        this.d = bArr;
        this.e = z;
        this.f = dataPriority;
        this.g = payLoadType;
        this.i = sh.shortValue();
    }

    public long a() {
        return this.f12085a;
    }

    public short b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public byte[] d() {
        return this.d;
    }

    public short e() {
        return this.i;
    }

    public boolean f() {
        return this.e;
    }

    public XMDPacket.DataPriority g() {
        return this.f;
    }

    public XMDPacket.PayLoadType h() {
        return h();
    }

    /* renamed from: a */
    public int compareTo(StreamHandlerData streamHandlerData) {
        return this.c - streamHandlerData.c;
    }

    public long i() {
        return this.h;
    }

    public void a(long j) {
        this.h = j;
    }

    public String j() {
        return this.f12085a + "-" + this.b;
    }
}
