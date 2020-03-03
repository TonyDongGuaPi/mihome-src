package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

final class BitmapPreFillRunner implements Runnable {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final String f4927a = "PreFillRunner";
    static final long b = 32;
    static final long c = 40;
    static final int d = 4;
    static final long e = TimeUnit.SECONDS.toMillis(1);
    private static final Clock f = new Clock();
    private final BitmapPool g;
    private final MemoryCache h;
    private final PreFillQueue i;
    private final Clock j;
    private final Set<PreFillType> k;
    private final Handler l;
    private long m;
    private boolean n;

    public BitmapPreFillRunner(BitmapPool bitmapPool, MemoryCache memoryCache, PreFillQueue preFillQueue) {
        this(bitmapPool, memoryCache, preFillQueue, f, new Handler(Looper.getMainLooper()));
    }

    @VisibleForTesting
    BitmapPreFillRunner(BitmapPool bitmapPool, MemoryCache memoryCache, PreFillQueue preFillQueue, Clock clock, Handler handler) {
        this.k = new HashSet();
        this.m = c;
        this.g = bitmapPool;
        this.h = memoryCache;
        this.i = preFillQueue;
        this.j = clock;
        this.l = handler;
    }

    public void a() {
        this.n = true;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean b() {
        Bitmap bitmap;
        long a2 = this.j.a();
        while (!this.i.c() && !a(a2)) {
            PreFillType a3 = this.i.a();
            if (!this.k.contains(a3)) {
                this.k.add(a3);
                bitmap = this.g.b(a3.a(), a3.b(), a3.c());
            } else {
                bitmap = Bitmap.createBitmap(a3.a(), a3.b(), a3.c());
            }
            int b2 = Util.b(bitmap);
            if (c() >= ((long) b2)) {
                this.h.b(new UniqueKey(), BitmapResource.a(bitmap, this.g));
            } else {
                this.g.a(bitmap);
            }
            if (Log.isLoggable(f4927a, 3)) {
                Log.d(f4927a, "allocated [" + a3.a() + "x" + a3.b() + "] " + a3.c() + " size: " + b2);
            }
        }
        return !this.n && !this.i.c();
    }

    private boolean a(long j2) {
        return this.j.a() - j2 >= 32;
    }

    private long c() {
        return this.h.b() - this.h.a();
    }

    public void run() {
        if (b()) {
            this.l.postDelayed(this, d());
        }
    }

    private long d() {
        long j2 = this.m;
        this.m = Math.min(this.m * 4, e);
        return j2;
    }

    private static final class UniqueKey implements Key {
        UniqueKey() {
        }

        public void a(@NonNull MessageDigest messageDigest) {
            throw new UnsupportedOperationException();
        }
    }

    @VisibleForTesting
    static class Clock {
        Clock() {
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return SystemClock.currentThreadTimeMillis();
        }
    }
}
