package com.xiaomi.youpin.hawkeye.utils;

import java.util.HashMap;

public class TimeUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1582a = "cold_start";
    public static final String b = "hot_start";
    public static long c = 0;
    private static HashMap<String, Long> d = new HashMap<>();

    public static void a(String str) {
        d.put(str, Long.valueOf(System.currentTimeMillis()));
    }

    public static long b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        Long l = d.get(str);
        if (l == null) {
            return -1;
        }
        d.remove(str);
        return currentTimeMillis - l.longValue();
    }

    public static long c(String str) {
        if (d.get(str) == null) {
            return 0;
        }
        return d.get(str).longValue();
    }

    public static void d(String str) {
        d.remove(str);
    }
}
