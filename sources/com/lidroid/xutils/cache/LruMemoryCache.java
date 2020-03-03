package com.lidroid.xutils.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruMemoryCache<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final LinkedHashMap<K, V> f6313a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private KeyExpiryMap<K, Long> i;

    /* access modifiers changed from: protected */
    public int a(K k, V v) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, K k, V v, V v2) {
    }

    /* access modifiers changed from: protected */
    public V d(K k) {
        return null;
    }

    public LruMemoryCache(int i2) {
        if (i2 > 0) {
            this.c = i2;
            this.f6313a = new LinkedHashMap<>(0, 0.75f, true);
            this.i = new KeyExpiryMap<>(0, 0.75f);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void a(int i2) {
        this.c = i2;
        b(i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r0 = d(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        if (r0 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.e++;
        r1 = r4.f6313a.put(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
        if (r1 == null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        r4.f6313a.put(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        r4.b += c(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
        if (r1 == null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        a(false, r5, r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        b(r4.c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V a(K r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0061
            monitor-enter(r4)
            com.lidroid.xutils.cache.KeyExpiryMap<K, java.lang.Long> r0 = r4.i     // Catch:{ all -> 0x005e }
            boolean r0 = r0.containsKey(r5)     // Catch:{ all -> 0x005e }
            r1 = 0
            if (r0 != 0) goto L_0x0011
            r4.b(r5)     // Catch:{ all -> 0x005e }
            monitor-exit(r4)     // Catch:{ all -> 0x005e }
            return r1
        L_0x0011:
            java.util.LinkedHashMap<K, V> r0 = r4.f6313a     // Catch:{ all -> 0x005e }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x005e }
            if (r0 == 0) goto L_0x0021
            int r5 = r4.g     // Catch:{ all -> 0x005e }
            int r5 = r5 + 1
            r4.g = r5     // Catch:{ all -> 0x005e }
            monitor-exit(r4)     // Catch:{ all -> 0x005e }
            return r0
        L_0x0021:
            int r0 = r4.h     // Catch:{ all -> 0x005e }
            int r0 = r0 + 1
            r4.h = r0     // Catch:{ all -> 0x005e }
            monitor-exit(r4)     // Catch:{ all -> 0x005e }
            java.lang.Object r0 = r4.d(r5)
            if (r0 != 0) goto L_0x002f
            return r1
        L_0x002f:
            monitor-enter(r4)
            int r1 = r4.e     // Catch:{ all -> 0x005b }
            int r1 = r1 + 1
            r4.e = r1     // Catch:{ all -> 0x005b }
            java.util.LinkedHashMap<K, V> r1 = r4.f6313a     // Catch:{ all -> 0x005b }
            java.lang.Object r1 = r1.put(r5, r0)     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0044
            java.util.LinkedHashMap<K, V> r2 = r4.f6313a     // Catch:{ all -> 0x005b }
            r2.put(r5, r1)     // Catch:{ all -> 0x005b }
            goto L_0x004d
        L_0x0044:
            int r2 = r4.b     // Catch:{ all -> 0x005b }
            int r3 = r4.c(r5, r0)     // Catch:{ all -> 0x005b }
            int r2 = r2 + r3
            r4.b = r2     // Catch:{ all -> 0x005b }
        L_0x004d:
            monitor-exit(r4)     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0055
            r2 = 0
            r4.a(r2, r5, r0, r1)
            return r1
        L_0x0055:
            int r5 = r4.c
            r4.b((int) r5)
            return r0
        L_0x005b:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x005b }
            throw r5
        L_0x005e:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x005e }
            throw r5
        L_0x0061:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r0 = "key == null"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruMemoryCache.a(java.lang.Object):java.lang.Object");
    }

    public final V b(K k, V v) {
        return a(k, v, Long.MAX_VALUE);
    }

    public final V a(K k, V v, long j) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.d++;
            this.b += c(k, v);
            put = this.f6313a.put(k, v);
            this.i.put(k, Long.valueOf(j));
            if (put != null) {
                this.b -= c(k, put);
            }
        }
        if (put != null) {
            a(false, k, put, v);
        }
        b(this.c);
        return put;
    }

    private void b(int i2) {
        Object key;
        Object value;
        while (true) {
            synchronized (this) {
                if (this.b > i2) {
                    if (this.f6313a.isEmpty()) {
                        break;
                    }
                    Map.Entry next = this.f6313a.entrySet().iterator().next();
                    key = next.getKey();
                    value = next.getValue();
                    this.f6313a.remove(key);
                    this.i.remove(key);
                    this.b -= c(key, value);
                    this.f++;
                }
            }
            a(true, key, value, (Object) null);
        }
    }

    public final V b(K k) {
        V remove;
        if (k != null) {
            synchronized (this) {
                remove = this.f6313a.remove(k);
                this.i.remove((Object) k);
                if (remove != null) {
                    this.b -= c(k, remove);
                }
            }
            if (remove != null) {
                a(false, k, remove, (V) null);
            }
            return remove;
        }
        throw new NullPointerException("key == null");
    }

    public final boolean c(K k) {
        return this.f6313a.containsKey(k);
    }

    private int c(K k, V v) {
        int a2 = a(k, v);
        if (a2 <= 0) {
            this.b = 0;
            for (Map.Entry next : this.f6313a.entrySet()) {
                this.b += a(next.getKey(), next.getValue());
            }
        }
        return a2;
    }

    public final void a() {
        b(-1);
        this.i.clear();
    }

    public final synchronized int b() {
        return this.b;
    }

    public final synchronized int c() {
        return this.c;
    }

    public final synchronized int d() {
        return this.g;
    }

    public final synchronized int e() {
        return this.h;
    }

    public final synchronized int f() {
        return this.e;
    }

    public final synchronized int g() {
        return this.d;
    }

    public final synchronized int h() {
        return this.f;
    }

    public final synchronized Map<K, V> i() {
        return new LinkedHashMap(this.f6313a);
    }

    public final synchronized String toString() {
        int i2;
        i2 = this.g + this.h;
        return String.format("LruMemoryCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i2 != 0 ? (this.g * 100) / i2 : 0)});
    }
}
