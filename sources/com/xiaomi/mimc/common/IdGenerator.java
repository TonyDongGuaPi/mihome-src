package com.xiaomi.mimc.common;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final IdGenerator b = new IdGenerator();

    /* renamed from: a  reason: collision with root package name */
    AtomicLong f11174a = new AtomicLong(1);

    private IdGenerator() {
    }

    public static IdGenerator a() {
        return b;
    }

    public long b() {
        return this.f11174a.addAndGet(1);
    }
}
