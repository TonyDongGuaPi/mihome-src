package com.xiaomi.smarthome.framework.page.verify;

import java.util.HashMap;
import java.util.Map;

public class DeviceLockCache {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17026a = 30000;
    private static volatile DeviceLockCache b;
    private Map<String, Long> c = new HashMap();

    private DeviceLockCache() {
    }

    public static DeviceLockCache a() {
        if (b == null) {
            synchronized (DeviceLockCache.class) {
                if (b == null) {
                    b = new DeviceLockCache();
                }
            }
        }
        return b;
    }

    public void a(String str) {
        this.c.put(str, Long.valueOf(System.currentTimeMillis()));
    }

    public boolean b(String str) {
        if (!this.c.containsKey(str)) {
            return false;
        }
        if (System.currentTimeMillis() - this.c.get(str).longValue() < 30000) {
            return true;
        }
        this.c.remove(str);
        return false;
    }
}
