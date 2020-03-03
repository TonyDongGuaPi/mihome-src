package com.xiaomi.smarthome.stat;

import java.util.concurrent.atomic.AtomicInteger;

public class ActionCounter {

    /* renamed from: a  reason: collision with root package name */
    private long f22744a = System.currentTimeMillis();
    private AtomicInteger b = new AtomicInteger(0);

    public long a() {
        return this.f22744a;
    }

    public int b() {
        return this.b.incrementAndGet();
    }
}
