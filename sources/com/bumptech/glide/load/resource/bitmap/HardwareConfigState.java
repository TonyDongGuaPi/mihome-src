package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import java.io.File;

final class HardwareConfigState {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5002a = 128;
    private static final File b = new File("/proc/self/fd");
    private static final int c = 50;
    private static final int d = 700;
    private static volatile HardwareConfigState g;
    private volatile int e;
    private volatile boolean f = true;

    static HardwareConfigState a() {
        if (g == null) {
            synchronized (HardwareConfigState.class) {
                if (g == null) {
                    g = new HardwareConfigState();
                }
            }
        }
        return g;
    }

    private HardwareConfigState() {
    }

    /* access modifiers changed from: package-private */
    @TargetApi(26)
    public boolean a(int i, int i2, BitmapFactory.Options options, DecodeFormat decodeFormat, boolean z, boolean z2) {
        if (!z || Build.VERSION.SDK_INT < 26 || z2) {
            return false;
        }
        boolean z3 = i >= 128 && i2 >= 128 && b();
        if (z3) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return z3;
    }

    private synchronized boolean b() {
        int i = this.e + 1;
        this.e = i;
        if (i >= 50) {
            boolean z = false;
            this.e = 0;
            int length = b.list().length;
            if (length < 700) {
                z = true;
            }
            this.f = z;
            if (!this.f && Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + 700);
            }
        }
        return this.f;
    }
}
