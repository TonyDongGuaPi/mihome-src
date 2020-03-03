package com.amap.openapi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import java.util.List;

public class df {
    private static volatile df c;

    /* renamed from: a  reason: collision with root package name */
    private di f4695a;
    private dh b;
    private long d;

    public interface a {
        void a();
    }

    private df(Context context) {
        this.f4695a = dg.a(context);
        this.b = new dh(context, this.f4695a);
    }

    public static df a(@NonNull Context context) {
        if (c == null) {
            synchronized (df.class) {
                if (c == null) {
                    c = new df(context.getApplicationContext());
                }
            }
        }
        return c;
    }

    public boolean a() {
        boolean z = false;
        if (cq.f4667a > 0 && SystemClock.elapsedRealtime() - this.d < cq.f4667a) {
            return false;
        }
        try {
            z = "true".equals(String.valueOf(de.a(this.f4695a, "startScanActive", new Object[0])));
        } catch (Exception unused) {
        }
        if (!z) {
            z = this.f4695a.b();
        }
        this.d = SystemClock.elapsedRealtime();
        return z;
    }

    @Nullable
    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public List<ScanResult> b() {
        return this.f4695a.a();
    }

    public boolean c() {
        return this.f4695a.c();
    }
}
