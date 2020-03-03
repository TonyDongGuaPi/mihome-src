package com.xiaomi.miot.support.monitor.utils;

import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import java.util.Map;

public class TimeRecodUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1492a = "app_attach";
    public static final String b = "app_restart";

    public static synchronized Long a(String str) {
        Long a2;
        synchronized (TimeRecodUtils.class) {
            a2 = a(str, 0L);
        }
        return a2;
    }

    public static synchronized Long a(String str, Long l) {
        synchronized (TimeRecodUtils.class) {
            if (l.longValue() <= 0) {
                l = Long.valueOf(System.currentTimeMillis());
            }
            Map map = MiotDataStorage.a().d.get();
            if (map == null) {
                return l;
            }
            if (map.containsKey(str)) {
                map.put(str, l);
                Long valueOf = Long.valueOf(l.longValue() - ((Long) map.get(str)).longValue());
                return valueOf;
            }
            map.put(str, l);
            return l;
        }
    }

    public static Long b(String str) {
        Map map = MiotDataStorage.a().d.get();
        if (map == null) {
            return 0L;
        }
        if (!map.containsKey(str)) {
            return 0L;
        }
        return (Long) map.get(str);
    }
}
