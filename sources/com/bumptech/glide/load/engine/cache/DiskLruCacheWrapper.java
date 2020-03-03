package com.bumptech.glide.load.engine.cache;

import android.util.Log;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;

public class DiskLruCacheWrapper implements DiskCache {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4909a = "DiskLruCacheWrapper";
    private static final int b = 1;
    private static final int c = 1;
    private static DiskLruCacheWrapper d;
    private final SafeKeyGenerator e;
    private final File f;
    private final long g;
    private final DiskCacheWriteLocker h = new DiskCacheWriteLocker();
    private DiskLruCache i;

    @Deprecated
    public static synchronized DiskCache a(File file, long j) {
        DiskLruCacheWrapper diskLruCacheWrapper;
        synchronized (DiskLruCacheWrapper.class) {
            if (d == null) {
                d = new DiskLruCacheWrapper(file, j);
            }
            diskLruCacheWrapper = d;
        }
        return diskLruCacheWrapper;
    }

    public static DiskCache b(File file, long j) {
        return new DiskLruCacheWrapper(file, j);
    }

    @Deprecated
    protected DiskLruCacheWrapper(File file, long j) {
        this.f = file;
        this.g = j;
        this.e = new SafeKeyGenerator();
    }

    private synchronized DiskLruCache b() throws IOException {
        if (this.i == null) {
            this.i = DiskLruCache.a(this.f, 1, 1, this.g);
        }
        return this.i;
    }

    public File a(Key key) {
        String a2 = this.e.a(key);
        if (Log.isLoggable(f4909a, 2)) {
            Log.v(f4909a, "Get: Obtained: " + a2 + " for for Key: " + key);
        }
        try {
            DiskLruCache.Value a3 = b().a(a2);
            if (a3 != null) {
                return a3.a(0);
            }
            return null;
        } catch (IOException e2) {
            if (!Log.isLoggable(f4909a, 5)) {
                return null;
            }
            Log.w(f4909a, "Unable to get from disk cache", e2);
            return null;
        }
    }

    public void a(Key key, DiskCache.Writer writer) {
        DiskLruCache.Editor b2;
        String a2 = this.e.a(key);
        this.h.a(a2);
        try {
            if (Log.isLoggable(f4909a, 2)) {
                Log.v(f4909a, "Put: Obtained: " + a2 + " for for Key: " + key);
            }
            try {
                DiskLruCache b3 = b();
                if (b3.a(a2) == null) {
                    b2 = b3.b(a2);
                    if (b2 != null) {
                        if (writer.a(b2.b(0))) {
                            b2.a();
                        }
                        b2.c();
                        this.h.b(a2);
                        return;
                    }
                    throw new IllegalStateException("Had two simultaneous puts for: " + a2);
                }
            } catch (IOException e2) {
                if (Log.isLoggable(f4909a, 5)) {
                    Log.w(f4909a, "Unable to put to disk cache", e2);
                }
            } catch (Throwable th) {
                b2.c();
                throw th;
            }
        } finally {
            this.h.b(a2);
        }
    }

    public void b(Key key) {
        try {
            b().c(this.e.a(key));
        } catch (IOException e2) {
            if (Log.isLoggable(f4909a, 5)) {
                Log.w(f4909a, "Unable to delete from disk cache", e2);
            }
        }
    }

    public synchronized void a() {
        try {
            b().f();
        } catch (IOException e2) {
            try {
                if (Log.isLoggable(f4909a, 5)) {
                    Log.w(f4909a, "Unable to clear disk cache or disk cache cleared externally", e2);
                }
            } catch (Throwable th) {
                c();
                throw th;
            }
        }
        c();
    }

    private synchronized void c() {
        this.i = null;
    }
}
