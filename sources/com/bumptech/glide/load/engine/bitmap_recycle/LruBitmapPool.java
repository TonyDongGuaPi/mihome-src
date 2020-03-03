package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool implements BitmapPool {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4896a = "LruBitmapPool";
    private static final Bitmap.Config b = Bitmap.Config.ARGB_8888;
    private final LruPoolStrategy c;
    private final Set<Bitmap.Config> d;
    private final long e;
    private final BitmapTracker f;
    private long g;
    private long h;
    private int i;
    private int j;
    private int k;
    private int l;

    private interface BitmapTracker {
        void a(Bitmap bitmap);

        void b(Bitmap bitmap);
    }

    LruBitmapPool(long j2, LruPoolStrategy lruPoolStrategy, Set<Bitmap.Config> set) {
        this.e = j2;
        this.g = j2;
        this.c = lruPoolStrategy;
        this.d = set;
        this.f = new NullBitmapTracker();
    }

    public LruBitmapPool(long j2) {
        this(j2, f(), g());
    }

    public LruBitmapPool(long j2, Set<Bitmap.Config> set) {
        this(j2, f(), set);
    }

    public long a() {
        return this.g;
    }

    public synchronized void a(float f2) {
        this.g = (long) Math.round(((float) this.e) * f2);
        c();
    }

    public synchronized void a(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (!bitmap.isRecycled()) {
            if (bitmap.isMutable() && ((long) this.c.c(bitmap)) <= this.g) {
                if (this.d.contains(bitmap.getConfig())) {
                    int c2 = this.c.c(bitmap);
                    this.c.a(bitmap);
                    this.f.a(bitmap);
                    this.k++;
                    this.h += (long) c2;
                    if (Log.isLoggable(f4896a, 2)) {
                        Log.v(f4896a, "Put bitmap in pool=" + this.c.b(bitmap));
                    }
                    d();
                    c();
                    return;
                }
            }
            if (Log.isLoggable(f4896a, 2)) {
                Log.v(f4896a, "Reject bitmap from pool, bitmap: " + this.c.b(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.d.contains(bitmap.getConfig()));
            }
            bitmap.recycle();
        } else {
            throw new IllegalStateException("Cannot pool recycled bitmap");
        }
    }

    private void c() {
        a(this.g);
    }

    @NonNull
    public Bitmap a(int i2, int i3, Bitmap.Config config) {
        Bitmap d2 = d(i2, i3, config);
        if (d2 == null) {
            return c(i2, i3, config);
        }
        d2.eraseColor(0);
        return d2;
    }

    @NonNull
    public Bitmap b(int i2, int i3, Bitmap.Config config) {
        Bitmap d2 = d(i2, i3, config);
        return d2 == null ? c(i2, i3, config) : d2;
    }

    @NonNull
    private static Bitmap c(int i2, int i3, @Nullable Bitmap.Config config) {
        if (config == null) {
            config = b;
        }
        return Bitmap.createBitmap(i2, i3, config);
    }

    @TargetApi(26)
    private static void a(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
    }

    @Nullable
    private synchronized Bitmap d(int i2, int i3, @Nullable Bitmap.Config config) {
        Bitmap a2;
        a(config);
        a2 = this.c.a(i2, i3, config != null ? config : b);
        if (a2 == null) {
            if (Log.isLoggable(f4896a, 3)) {
                Log.d(f4896a, "Missing bitmap=" + this.c.b(i2, i3, config));
            }
            this.j++;
        } else {
            this.i++;
            this.h -= (long) this.c.c(a2);
            this.f.b(a2);
            b(a2);
        }
        if (Log.isLoggable(f4896a, 2)) {
            Log.v(f4896a, "Get bitmap=" + this.c.b(i2, i3, config));
        }
        d();
        return a2;
    }

    private static void b(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        c(bitmap);
    }

    @TargetApi(19)
    private static void c(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    public void b() {
        if (Log.isLoggable(f4896a, 3)) {
            Log.d(f4896a, "clearMemory");
        }
        a(0);
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i2) {
        if (Log.isLoggable(f4896a, 3)) {
            Log.d(f4896a, "trimMemory, level=" + i2);
        }
        if (i2 >= 40) {
            b();
        } else if (i2 >= 20 || i2 == 15) {
            a(a() / 2);
        }
    }

    private synchronized void a(long j2) {
        while (this.h > j2) {
            Bitmap a2 = this.c.a();
            if (a2 == null) {
                if (Log.isLoggable(f4896a, 5)) {
                    Log.w(f4896a, "Size mismatch, resetting");
                    e();
                }
                this.h = 0;
                return;
            }
            this.f.b(a2);
            this.h -= (long) this.c.c(a2);
            this.l++;
            if (Log.isLoggable(f4896a, 3)) {
                Log.d(f4896a, "Evicting bitmap=" + this.c.b(a2));
            }
            d();
            a2.recycle();
        }
    }

    private void d() {
        if (Log.isLoggable(f4896a, 2)) {
            e();
        }
    }

    private void e() {
        Log.v(f4896a, "Hits=" + this.i + ", misses=" + this.j + ", puts=" + this.k + ", evictions=" + this.l + ", currentSize=" + this.h + ", maxSize=" + this.g + "\nStrategy=" + this.c);
    }

    private static LruPoolStrategy f() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SizeConfigStrategy();
        }
        return new AttributeStrategy();
    }

    @TargetApi(26)
    private static Set<Bitmap.Config> g() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        if (Build.VERSION.SDK_INT >= 19) {
            hashSet.add((Object) null);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            hashSet.remove(Bitmap.Config.HARDWARE);
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static class ThrowingBitmapTracker implements BitmapTracker {

        /* renamed from: a  reason: collision with root package name */
        private final Set<Bitmap> f4897a = Collections.synchronizedSet(new HashSet());

        private ThrowingBitmapTracker() {
        }

        public void a(Bitmap bitmap) {
            if (!this.f4897a.contains(bitmap)) {
                this.f4897a.add(bitmap);
                return;
            }
            throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + "x" + bitmap.getHeight() + Operators.ARRAY_END_STR);
        }

        public void b(Bitmap bitmap) {
            if (this.f4897a.contains(bitmap)) {
                this.f4897a.remove(bitmap);
                return;
            }
            throw new IllegalStateException("Cannot remove bitmap not in tracker");
        }
    }

    private static final class NullBitmapTracker implements BitmapTracker {
        public void a(Bitmap bitmap) {
        }

        public void b(Bitmap bitmap) {
        }

        NullBitmapTracker() {
        }
    }
}
