package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<T, Y> {

    /* renamed from: a  reason: collision with root package name */
    private final Map<T, Y> f5102a = new LinkedHashMap(100, 0.75f, true);
    private final long b;
    private long c;
    private long d;

    /* access modifiers changed from: protected */
    public int a(@Nullable Y y) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void a(@NonNull T t, @Nullable Y y) {
    }

    public LruCache(long j) {
        this.b = j;
        this.c = j;
    }

    public synchronized void a(float f) {
        if (f >= 0.0f) {
            this.c = (long) Math.round(((float) this.b) * f);
            e();
        } else {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
    }

    /* access modifiers changed from: protected */
    public synchronized int d() {
        return this.f5102a.size();
    }

    public synchronized long b() {
        return this.c;
    }

    public synchronized long a() {
        return this.d;
    }

    public synchronized boolean b(@NonNull T t) {
        return this.f5102a.containsKey(t);
    }

    @Nullable
    public synchronized Y c(@NonNull T t) {
        return this.f5102a.get(t);
    }

    @Nullable
    public synchronized Y b(@NonNull T t, @Nullable Y y) {
        long a2 = (long) a(y);
        if (a2 >= this.c) {
            a(t, y);
            return null;
        }
        if (y != null) {
            this.d += a2;
        }
        Y put = this.f5102a.put(t, y);
        if (put != null) {
            this.d -= (long) a(put);
            if (!put.equals(y)) {
                a(t, put);
            }
        }
        e();
        return put;
    }

    @Nullable
    public synchronized Y d(@NonNull T t) {
        Y remove;
        remove = this.f5102a.remove(t);
        if (remove != null) {
            this.d -= (long) a(remove);
        }
        return remove;
    }

    public void c() {
        a(0);
    }

    /* access modifiers changed from: protected */
    public synchronized void a(long j) {
        while (this.d > j) {
            Iterator<Map.Entry<T, Y>> it = this.f5102a.entrySet().iterator();
            Map.Entry next = it.next();
            Object value = next.getValue();
            this.d -= (long) a(value);
            Object key = next.getKey();
            it.remove();
            a(key, value);
        }
    }

    private void e() {
        a(this.c);
    }
}
