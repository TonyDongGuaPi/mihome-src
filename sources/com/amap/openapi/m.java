package com.amap.openapi;

import android.content.Context;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.amap.location.collection.CollectionConfig;
import java.util.List;

public class m {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4733a = "m";
    private Context b;
    private Handler c;
    private t d;
    private l e;
    private o f;
    private cs g;
    /* access modifiers changed from: private */
    public long h;
    private long i;
    private Location j;
    private h k;
    private v l = new v();

    public m(Context context, t tVar, CollectionConfig.FpsCollectorConfig fpsCollectorConfig, Looper looper) {
        this.b = context;
        this.d = tVar;
        this.c = new Handler(looper);
        this.e = new l(this.b, looper);
        this.f = new o(this.b, looper);
        this.k = new h();
    }

    public void a() {
        this.e.a();
        this.f.a();
        this.g = new cs() {
            public void a(long j, String str) {
                long unused = m.this.h = j;
            }
        };
        try {
            cr.a(this.b).a(this.g, this.c.getLooper());
        } catch (Exception | SecurityException unused) {
        }
    }

    public void a(Location location, List<ScanResult> list, long j2, long j3) {
        Location location2 = location;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.j == null || location2.distanceTo(this.j) >= 10.0f) {
            q a2 = this.e.a(location2);
            List<aa> a3 = this.f.a(location, list, j2, j3);
            if (!(a2 == null && a3 == null)) {
                ba.a(this.l, location, this.h, j3);
                byte[] a4 = this.k.a(this.b, this.l, a2, this.f.c(), a3);
                if (a4 != null) {
                    this.d.a(0, a4);
                }
            }
            this.j = location2;
            this.i = elapsedRealtime;
        }
    }

    public void b() {
        try {
            cr.a(this.b).a(this.g);
        } catch (Exception unused) {
        }
        this.c.removeCallbacksAndMessages((Object) null);
        this.e.b();
        this.f.b();
    }
}
