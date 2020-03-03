package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;

public final class MemorySizeCalculator {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final int f4915a = 4;
    private static final String b = "MemorySizeCalculator";
    private static final int c = 2;
    private final int d;
    private final int e;
    private final Context f;
    private final int g;

    interface ScreenDimensions {
        int a();

        int b();
    }

    MemorySizeCalculator(Builder builder) {
        int i;
        this.f = builder.f;
        if (a(builder.g)) {
            i = builder.m / 2;
        } else {
            i = builder.m;
        }
        this.g = i;
        int a2 = a(builder.g, builder.k, builder.l);
        float a3 = (float) (builder.h.a() * builder.h.b() * 4);
        int round = Math.round(builder.j * a3);
        int round2 = Math.round(a3 * builder.i);
        int i2 = a2 - this.g;
        int i3 = round2 + round;
        if (i3 <= i2) {
            this.e = round2;
            this.d = round;
        } else {
            float f2 = ((float) i2) / (builder.j + builder.i);
            this.e = Math.round(builder.i * f2);
            this.d = Math.round(f2 * builder.j);
        }
        if (Log.isLoggable(b, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(a(this.e));
            sb.append(", pool size: ");
            sb.append(a(this.d));
            sb.append(", byte array size: ");
            sb.append(a(this.g));
            sb.append(", memory class limited? ");
            sb.append(i3 > a2);
            sb.append(", max size: ");
            sb.append(a(a2));
            sb.append(", memoryClass: ");
            sb.append(builder.g.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(a(builder.g));
            Log.d(b, sb.toString());
        }
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.g;
    }

    private static int a(ActivityManager activityManager, float f2, float f3) {
        boolean a2 = a(activityManager);
        float memoryClass = (float) (activityManager.getMemoryClass() * 1024 * 1024);
        if (a2) {
            f2 = f3;
        }
        return Math.round(memoryClass * f2);
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.f, (long) i);
    }

    @TargetApi(19)
    static boolean a(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    public static final class Builder {
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        static final int f4916a = 2;
        static final int b = (Build.VERSION.SDK_INT < 26 ? 4 : 1);
        static final float c = 0.4f;
        static final float d = 0.33f;
        static final int e = 4194304;
        final Context f;
        ActivityManager g;
        ScreenDimensions h;
        float i = 2.0f;
        float j = ((float) b);
        float k = 0.4f;
        float l = d;
        int m = 4194304;

        public Builder(Context context) {
            this.f = context;
            this.g = (ActivityManager) context.getSystemService("activity");
            this.h = new DisplayMetricsScreenDimensions(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT >= 26 && MemorySizeCalculator.a(this.g)) {
                this.j = 0.0f;
            }
        }

        public Builder a(float f2) {
            Preconditions.a(f2 >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.i = f2;
            return this;
        }

        public Builder b(float f2) {
            Preconditions.a(f2 >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.j = f2;
            return this;
        }

        public Builder c(float f2) {
            Preconditions.a(f2 >= 0.0f && f2 <= 1.0f, "Size multiplier must be between 0 and 1");
            this.k = f2;
            return this;
        }

        public Builder d(float f2) {
            Preconditions.a(f2 >= 0.0f && f2 <= 1.0f, "Low memory max size multiplier must be between 0 and 1");
            this.l = f2;
            return this;
        }

        public Builder a(int i2) {
            this.m = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public Builder a(ActivityManager activityManager) {
            this.g = activityManager;
            return this;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public Builder a(ScreenDimensions screenDimensions) {
            this.h = screenDimensions;
            return this;
        }

        public MemorySizeCalculator a() {
            return new MemorySizeCalculator(this);
        }
    }

    private static final class DisplayMetricsScreenDimensions implements ScreenDimensions {

        /* renamed from: a  reason: collision with root package name */
        private final DisplayMetrics f4917a;

        DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics) {
            this.f4917a = displayMetrics;
        }

        public int a() {
            return this.f4917a.widthPixels;
        }

        public int b() {
            return this.f4917a.heightPixels;
        }
    }
}
