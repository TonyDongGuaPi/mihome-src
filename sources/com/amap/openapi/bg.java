package com.amap.openapi;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.amap.location.collection.CollectionConfig;
import com.amap.openapi.bh;
import java.util.List;

public class bg {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public bh f4621a;
    private LocationListener b;
    private boolean c;
    private cr d;
    private cu e;
    private final Object f = new Object();
    /* access modifiers changed from: private */
    public Context g;
    private Looper h;
    /* access modifiers changed from: private */
    public bf i;
    /* access modifiers changed from: private */
    public boolean j;

    public bg(Context context, @NonNull CollectionConfig.FpsCollectorConfig fpsCollectorConfig, @NonNull bf bfVar, @NonNull Looper looper) {
        this.g = context;
        this.h = looper;
        this.d = cr.a(context);
        this.i = bfVar;
        this.f4621a = new bh(context, fpsCollectorConfig, looper);
        this.b = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (bg.this.j) {
                    try {
                        if (!ba.a(location)) {
                            return;
                        }
                        if (!ba.a(bg.this.g, location)) {
                            bg.this.b();
                            if (bg.this.i != null) {
                                bh.a f = bg.this.f4621a.f();
                                bg.this.i.a(location, f.f4627a, f.b, System.currentTimeMillis());
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
            }

            public void onProviderDisabled(String str) {
            }

            public void onProviderEnabled(String str) {
            }

            public void onStatusChanged(String str, int i, Bundle bundle) {
            }
        };
        this.e = new cu() {
            public void a() {
            }

            public void a(int i) {
            }

            public void a(int i, int i2, float f, List<ct> list) {
                bg.this.a(i);
            }

            public void b() {
            }
        };
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        boolean z = i2 < 4;
        if (this.c != z) {
            this.c = z;
            if (z) {
                this.f4621a.d();
            } else {
                this.f4621a.c();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|4|5|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f
            monitor-enter(r0)
            r1 = 0
            r3.j = r1     // Catch:{ all -> 0x0016 }
            com.amap.openapi.cr r1 = r3.d     // Catch:{ SecurityException | Throwable -> 0x0014 }
            android.location.LocationListener r2 = r3.b     // Catch:{ SecurityException | Throwable -> 0x0014 }
            r1.a((android.location.LocationListener) r2)     // Catch:{ SecurityException | Throwable -> 0x0014 }
            com.amap.openapi.cr r1 = r3.d     // Catch:{ SecurityException | Throwable -> 0x0014 }
            com.amap.openapi.cu r2 = r3.e     // Catch:{ SecurityException | Throwable -> 0x0014 }
            r1.a((com.amap.openapi.cu) r2)     // Catch:{ SecurityException | Throwable -> 0x0014 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.bg.a():void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|4|5|6|(1:10)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0031 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r8, long r9, float r11) {
        /*
            r7 = this;
            java.lang.Object r11 = r7.f
            monitor-enter(r11)
            r0 = 1
            r7.j = r0     // Catch:{ all -> 0x0033 }
            com.amap.openapi.cr r0 = r7.d     // Catch:{ SecurityException | Throwable -> 0x0031 }
            java.util.List r0 = r0.a()     // Catch:{ SecurityException | Throwable -> 0x0031 }
            java.lang.String r1 = "gps"
            boolean r1 = r0.contains(r1)     // Catch:{ SecurityException | Throwable -> 0x0031 }
            if (r1 != 0) goto L_0x001c
            java.lang.String r1 = "passive"
            boolean r0 = r0.contains(r1)     // Catch:{ SecurityException | Throwable -> 0x0031 }
            if (r0 == 0) goto L_0x0031
        L_0x001c:
            com.amap.openapi.cr r0 = r7.d     // Catch:{ SecurityException | Throwable -> 0x0031 }
            r4 = 0
            android.location.LocationListener r5 = r7.b     // Catch:{ SecurityException | Throwable -> 0x0031 }
            android.os.Looper r6 = r7.h     // Catch:{ SecurityException | Throwable -> 0x0031 }
            r1 = r8
            r2 = r9
            r0.a(r1, r2, r4, r5, r6)     // Catch:{ SecurityException | Throwable -> 0x0031 }
            com.amap.openapi.cr r8 = r7.d     // Catch:{ SecurityException | Throwable -> 0x0031 }
            com.amap.openapi.cu r9 = r7.e     // Catch:{ SecurityException | Throwable -> 0x0031 }
            android.os.Looper r10 = r7.h     // Catch:{ SecurityException | Throwable -> 0x0031 }
            r8.a((com.amap.openapi.cu) r9, (android.os.Looper) r10)     // Catch:{ SecurityException | Throwable -> 0x0031 }
        L_0x0031:
            monitor-exit(r11)     // Catch:{ all -> 0x0033 }
            return
        L_0x0033:
            r8 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0033 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.bg.a(java.lang.String, long, float):void");
    }

    public void b() {
        if (!this.f4621a.e()) {
            this.f4621a.a();
        }
    }

    public void c() {
        if (this.f4621a.e()) {
            this.f4621a.b();
        }
    }
}
