package com.mi.mistatistic.sdk.controller;

import com.mi.mistatistic.sdk.controller.asyncjobs.CorrectingServerTimeJob;

public class TimeUtil {

    /* renamed from: a  reason: collision with root package name */
    private static volatile TimeUtil f7346a;

    private TimeUtil() {
    }

    public static TimeUtil a() {
        if (f7346a == null) {
            synchronized (TimeUtil.class) {
                if (f7346a == null) {
                    f7346a = new TimeUtil();
                }
            }
        }
        return f7346a;
    }

    public long b() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = 0;
        try {
            long a2 = PrefPersistUtils.a(ApplicationContextHolder.a(), CorrectingServerTimeJob.c, 0);
            try {
                if (Math.abs(currentTimeMillis - a2) >= 1000) {
                    j = a2;
                }
            } catch (Exception e) {
                long j2 = a2;
                e = e;
                j = j2;
                e.printStackTrace();
                return currentTimeMillis + j;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return currentTimeMillis + j;
        }
        return currentTimeMillis + j;
    }
}
