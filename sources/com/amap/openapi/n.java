package com.amap.openapi;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.amap.location.collection.CollectionConfig;
import java.util.ArrayList;
import java.util.List;

public class n {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4735a = "n";
    private Context b;
    private CollectionConfig.TrackCollectorConfig c;
    private Handler d;
    private final Object e = new Object();
    private t f;
    private j g;
    private cu h;
    private long i;
    private Location j;
    /* access modifiers changed from: private */
    public long k;
    private List<y> l = new ArrayList();
    /* access modifiers changed from: private */
    public List<ct> m;
    private v n = new v();

    public n(Context context, t tVar, CollectionConfig.TrackCollectorConfig trackCollectorConfig, Looper looper) {
        this.b = context;
        this.c = trackCollectorConfig;
        this.f = tVar;
        this.d = new Handler(looper);
        this.g = new j();
    }

    private byte[] b(Location location) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.j != null && (elapsedRealtime - this.i < 2000 || location.distanceTo(this.j) < 5.0f)) {
            return null;
        }
        this.i = elapsedRealtime;
        this.j = location;
        boolean z = false;
        if (this.c.c() && this.k != 0 && elapsedRealtime - this.k <= 3000) {
            z = true;
        }
        ba.a(this.n, ba.a(this.l, z, this.m), location, location.getTime(), System.currentTimeMillis());
        return this.g.a(this.b, this.n, this.l, this.c.a());
    }

    public void a() {
        this.h = new cu() {
            public void a() {
            }

            public void a(int i) {
            }

            public void a(int i, int i2, float f, List<ct> list) {
                long unused = n.this.k = SystemClock.elapsedRealtime();
                List unused2 = n.this.m = list;
            }

            public void b() {
            }
        };
        try {
            cr.a(this.b).a(this.h, this.d.getLooper());
        } catch (Exception | SecurityException unused) {
        }
    }

    public void a(Location location) {
        byte[] b2 = b(location);
        if (b2 != null) {
            this.f.a(1, b2);
        }
    }

    public void b() {
        try {
            cr.a(this.b).a(this.h);
        } catch (Exception | SecurityException unused) {
        }
        synchronized (this.e) {
            this.d.removeCallbacksAndMessages((Object) null);
            this.d = null;
        }
    }
}
