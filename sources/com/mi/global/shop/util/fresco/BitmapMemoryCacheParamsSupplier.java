package com.mi.global.shop.util.fresco;

import android.app.ActivityManager;
import android.os.Build;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

public class BitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7122a = 128;
    private static final int b = 10;
    private static final int c = 10;
    private final ActivityManager d;

    public BitmapMemoryCacheParamsSupplier(ActivityManager activityManager) {
        this.d = activityManager;
    }

    /* renamed from: a */
    public MemoryCacheParams get() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new MemoryCacheParams(b(), 128, 10, 10, 5);
        }
        return new MemoryCacheParams(b(), 128, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private int b() {
        int min = Math.min(this.d.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return 4194304;
        }
        if (min < 67108864) {
            return 6291456;
        }
        if (Build.VERSION.SDK_INT < 11) {
            return 8388608;
        }
        return min / 4;
    }
}
