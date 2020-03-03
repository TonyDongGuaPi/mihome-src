package com.xiaomi.msg.data;

import com.xiaomi.msg.data.XMDPacket;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamInfo {

    /* renamed from: a  reason: collision with root package name */
    private AtomicInteger f12086a = new AtomicInteger(0);
    private long b;
    private XMDPacket.StreamType c;
    private short d;
    private boolean e;
    private long f;

    public StreamInfo(long j, XMDPacket.StreamType streamType, short s, boolean z) {
        this.b = j;
        this.c = streamType;
        this.d = s;
        this.e = z;
        this.f = System.currentTimeMillis();
    }

    public long a() {
        return this.b;
    }

    public XMDPacket.StreamType b() {
        return this.c;
    }

    public int c() {
        return this.f12086a.getAndAdd(1);
    }

    public short d() {
        return this.d;
    }

    public void a(short s) {
        this.d = s;
    }

    public boolean e() {
        return this.e;
    }

    public void a(long j) {
        this.f = j;
    }

    public long f() {
        return this.f;
    }
}
