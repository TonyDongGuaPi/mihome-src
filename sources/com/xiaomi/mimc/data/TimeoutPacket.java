package com.xiaomi.mimc.data;

import com.xiaomi.mimc.proto.Mimc;

public class TimeoutPacket {

    /* renamed from: a  reason: collision with root package name */
    private long f11193a;
    private Mimc.MIMCPacket b;

    public TimeoutPacket(Mimc.MIMCPacket mIMCPacket, long j) {
        this.b = mIMCPacket;
        this.f11193a = j;
    }

    public long a() {
        return this.f11193a;
    }

    public Mimc.MIMCPacket b() {
        return this.b;
    }
}
