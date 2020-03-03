package com.xiaomi.mimc.data;

import com.xiaomi.mimc.proto.RtsSignal;

public abstract class RtsSession {

    /* renamed from: a  reason: collision with root package name */
    private RtsSignal.CallType f11191a;
    private long b;
    private long c;

    public RtsSession(RtsSignal.CallType callType, long j, long j2) {
        this.f11191a = callType;
        this.b = j;
        this.c = j2;
    }

    public RtsSignal.CallType r() {
        return this.f11191a;
    }

    public void d(long j) {
        this.c = j;
    }

    public long s() {
        return this.c;
    }
}
