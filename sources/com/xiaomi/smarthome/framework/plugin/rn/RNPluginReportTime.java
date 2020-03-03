package com.xiaomi.smarthome.framework.plugin.rn;

import java.util.Random;

public class RNPluginReportTime {
    private static RNPluginReportTime e;

    /* renamed from: a  reason: collision with root package name */
    private long f17248a;
    private long b;
    private long c;
    private boolean d = false;

    private RNPluginReportTime() {
    }

    public static RNPluginReportTime a() {
        if (e == null) {
            synchronized (RNPluginReportTime.class) {
                if (e == null) {
                    e = new RNPluginReportTime();
                }
            }
        }
        return e;
    }

    public long b() {
        if (this.f17248a < 1000) {
            this.f17248a = System.currentTimeMillis() - ((long) (new Random().nextInt(50) + 300));
        }
        return this.f17248a;
    }

    public void a(long j) {
        this.f17248a = j;
    }

    public long c() {
        if (this.b < 1000) {
            this.b = System.currentTimeMillis() - ((long) (new Random().nextInt(50) + 100));
        }
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long d() {
        if (this.c < 1000) {
            this.c = System.currentTimeMillis() - ((long) (new Random().nextInt(20) + 30));
        }
        return this.c;
    }

    public void c(long j) {
        this.c = j;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean e() {
        return this.d;
    }
}
