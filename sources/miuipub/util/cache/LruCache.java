package miuipub.util.cache;

import android.app.ActivityManager;
import com.miuipub.internal.util.PackageConstants;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LruCache<K, V> implements Cache<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3050a = ((((ActivityManager) PackageConstants.a().getSystemService("activity")).getMemoryClass() * 1024) * 1024);
    private final ReentrantLock b;
    private long c;
    private long d;
    private final LinkedHashMap<K, CacheItem<K, V>> e;

    private static class CacheItem<K, T> {

        /* renamed from: a  reason: collision with root package name */
        public K f3051a;
        public int b;
        public T c;
        public SoftReference<T> d;

        private CacheItem() {
        }
    }

    public LruCache(int i) {
        i = i < 0 ? f3050a / 8 : i;
        this.b = new ReentrantLock();
        this.c = (long) i;
        this.d = 0;
        this.e = new LinkedHashMap<>(0, 0.75f, true);
    }

    public void a(int i) {
        if (i < 0) {
            i = f3050a / 8;
        }
        this.c = (long) i;
        try {
            this.b.lock();
            if (this.d < this.c) {
                c();
            }
        } finally {
            this.b.unlock();
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [T, V] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(K r3, V r4, int r5) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0037
            if (r4 != 0) goto L_0x0005
            goto L_0x0037
        L_0x0005:
            miuipub.util.cache.LruCache$CacheItem r0 = new miuipub.util.cache.LruCache$CacheItem
            r1 = 0
            r0.<init>()
            r0.f3051a = r3
            r0.c = r4
            if (r5 < 0) goto L_0x0012
            goto L_0x0013
        L_0x0012:
            r5 = 0
        L_0x0013:
            r0.b = r5
            java.util.concurrent.locks.ReentrantLock r4 = r2.b
            r4.lock()
            java.util.LinkedHashMap<K, miuipub.util.cache.LruCache$CacheItem<K, V>> r4 = r2.e     // Catch:{ all -> 0x0030 }
            r4.put(r3, r0)     // Catch:{ all -> 0x0030 }
            long r3 = r2.d     // Catch:{ all -> 0x0030 }
            int r5 = r0.b     // Catch:{ all -> 0x0030 }
            long r0 = (long) r5     // Catch:{ all -> 0x0030 }
            long r3 = r3 + r0
            r2.d = r3     // Catch:{ all -> 0x0030 }
            r2.c()     // Catch:{ all -> 0x0030 }
            java.util.concurrent.locks.ReentrantLock r3 = r2.b
            r3.unlock()
            return
        L_0x0030:
            r3 = move-exception
            java.util.concurrent.locks.ReentrantLock r4 = r2.b
            r4.unlock()
            throw r3
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.util.cache.LruCache.a(java.lang.Object, java.lang.Object, int):void");
    }

    public V a(K k) {
        Object obj;
        this.b.lock();
        try {
            c();
            CacheItem cacheItem = this.e.get(k);
            if (cacheItem != null) {
                if (cacheItem.c != null) {
                    obj = cacheItem.c;
                } else {
                    if (cacheItem.d != null) {
                        cacheItem.c = cacheItem.d.get();
                        if (cacheItem.c != null) {
                            this.d += (long) cacheItem.b;
                            if (this.d > this.c) {
                                c();
                            }
                            obj = cacheItem.c;
                        }
                    }
                    this.e.remove(k);
                }
                this.b.unlock();
                return obj;
            }
            return null;
        } finally {
            this.b.unlock();
        }
    }

    public void a() {
        this.b.lock();
        try {
            for (Map.Entry<K, CacheItem<K, V>> value : this.e.entrySet()) {
                CacheItem cacheItem = (CacheItem) value.getValue();
                if (cacheItem.d != null) {
                    cacheItem.d.clear();
                }
            }
            this.e.clear();
        } finally {
            this.b.unlock();
        }
    }

    public int b() {
        this.b.lock();
        try {
            c();
            return this.e.size();
        } finally {
            this.b.unlock();
        }
    }

    private void c() {
        Iterator<Map.Entry<K, CacheItem<K, V>>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            CacheItem cacheItem = (CacheItem) it.next().getValue();
            if (cacheItem.c == null && (cacheItem.d == null || cacheItem.d.get() == null)) {
                it.remove();
            }
        }
        Iterator<Map.Entry<K, CacheItem<K, V>>> it2 = this.e.entrySet().iterator();
        while (this.d > this.c && it2.hasNext()) {
            CacheItem cacheItem2 = (CacheItem) it2.next().getValue();
            if (cacheItem2 != null) {
                if (cacheItem2.d == null) {
                    cacheItem2.d = new SoftReference<>(cacheItem2.c);
                }
                this.d -= (long) cacheItem2.b;
                cacheItem2.c = null;
            }
        }
    }
}
