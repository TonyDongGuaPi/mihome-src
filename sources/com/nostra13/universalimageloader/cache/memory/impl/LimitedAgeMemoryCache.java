package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LimitedAgeMemoryCache implements MemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private final MemoryCache f8451a;
    private final long b;
    private final Map<String, Long> c = Collections.synchronizedMap(new HashMap());

    public LimitedAgeMemoryCache(MemoryCache memoryCache, long j) {
        this.f8451a = memoryCache;
        this.b = j * 1000;
    }

    public boolean a(String str, Bitmap bitmap) {
        boolean a2 = this.f8451a.a(str, bitmap);
        if (a2) {
            this.c.put(str, Long.valueOf(System.currentTimeMillis()));
        }
        return a2;
    }

    public Bitmap a(String str) {
        Long l = this.c.get(str);
        if (l != null && System.currentTimeMillis() - l.longValue() > this.b) {
            this.f8451a.b(str);
            this.c.remove(str);
        }
        return this.f8451a.a(str);
    }

    public Bitmap b(String str) {
        this.c.remove(str);
        return this.f8451a.b(str);
    }

    public Collection<String> a() {
        return this.f8451a.a();
    }

    public void b() {
        this.f8451a.b();
        this.c.clear();
    }
}
