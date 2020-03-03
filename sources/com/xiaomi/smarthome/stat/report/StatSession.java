package com.xiaomi.smarthome.stat.report;

import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.concurrent.atomic.AtomicLong;

public class StatSession {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22773a = "\\\"session\\\":";
    public static final String b = ",\\\"order\\\":";
    private static Long c = null;
    private static AtomicLong d = null;
    private static int e = 1;
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 10;
    private static final long i = 1;
    private static final long j = 2;

    private static String b(long j2, long j3) {
        if (c == null) {
            synchronized (StatLogCache.f22761a) {
                if (c == null) {
                    if (j2 <= 10) {
                        j2 = System.currentTimeMillis();
                    }
                    c = Long.valueOf(j2);
                    d = new AtomicLong(j3);
                }
            }
        }
        return f22773a + c + b + d.incrementAndGet();
    }

    public static String a(long j2, long j3) {
        if (j2 >= 10) {
            return b(j2, j3);
        }
        boolean z = j3 == 1;
        e += z ? 1 : -1;
        if (z) {
            if (e > 1) {
                return null;
            }
            return "enter";
        } else if (e > 0) {
            return null;
        } else {
            synchronized (StatLogCache.f22761a) {
                c = Long.valueOf(System.currentTimeMillis());
                d = new AtomicLong(0);
            }
            return "reset";
        }
    }

    public static final boolean a(CoreApi coreApi, boolean z, boolean z2) {
        return coreApi.a((long) ((z ^ true ? 1 : 0) + true), z2 ? 1 : 2) != null;
    }

    public static String a(CoreApi coreApi) {
        if (coreApi == null || !coreApi.l()) {
            return b(10, 0);
        }
        if (c != null) {
            synchronized (StatLogCache.f22761a) {
                if (c != null) {
                    String a2 = coreApi.a(c.longValue(), d.get());
                    if (a2 == null) {
                        String b2 = b(10, 0);
                        return b2;
                    }
                    c = null;
                    d = null;
                    return a2;
                }
            }
        }
        return coreApi.a(10, 0);
    }
}
