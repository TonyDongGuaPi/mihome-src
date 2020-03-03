package com.amap.location.offline;

import android.content.Context;
import android.support.annotation.NonNull;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.common.model.FPS;
import com.amap.location.offline.upload.a;
import com.amap.openapi.bo;
import com.amap.openapi.bp;
import com.amap.openapi.co;

public class OfflineManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4597a = "OfflineManager";
    private static volatile OfflineManager b;
    private OfflineConfig c;
    private a d;
    private Context e;
    private bo f;
    private b g;
    private bp h;

    private OfflineManager() {
    }

    public static OfflineManager a() {
        if (b == null) {
            synchronized (OfflineManager.class) {
                if (b == null) {
                    b = new OfflineManager();
                }
            }
        }
        return b;
    }

    private void d() {
        this.h = new bp(this.e, this.c, this.d);
        this.h.a();
    }

    /* access modifiers changed from: protected */
    public synchronized AmapLoc a(@NonNull FPS fps, int i, boolean z, String str) {
        if (!c()) {
            return null;
        }
        if (this.h == null) {
            co.a a2 = this.g.a(fps, i, str);
            if (a2.f4665a) {
                return a2.b;
            }
            d();
        }
        return this.h.a(fps, i, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014 A[Catch:{ all -> 0x000f }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001b A[Catch:{ all -> 0x000f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.amap.location.common.model.AmapLoc a(@android.support.annotation.NonNull com.amap.location.common.model.FPS r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            if (r4 == 0) goto L_0x0011
            com.amap.location.offline.a r1 = r2.d     // Catch:{ all -> 0x000f }
            if (r1 == 0) goto L_0x0011
            com.amap.location.offline.a r1 = r2.d     // Catch:{ all -> 0x000f }
            int r1 = r1.e()     // Catch:{ all -> 0x000f }
            goto L_0x0012
        L_0x000f:
            r3 = move-exception
            goto L_0x002d
        L_0x0011:
            r1 = 0
        L_0x0012:
            if (r4 == 0) goto L_0x001b
            r4 = 100033(0x186c1, float:1.40176E-40)
            com.amap.location.offline.upload.a.a((int) r4)     // Catch:{ all -> 0x000f }
            goto L_0x0021
        L_0x001b:
            r4 = 100034(0x186c2, float:1.40177E-40)
            com.amap.location.offline.upload.a.a((int) r4)     // Catch:{ all -> 0x000f }
        L_0x0021:
            android.content.Context r4 = r2.e     // Catch:{ all -> 0x000f }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x000f }
            com.amap.location.common.model.AmapLoc r3 = r2.a(r3, r1, r0, r4)     // Catch:{ all -> 0x000f }
            monitor-exit(r2)
            return r3
        L_0x002d:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.location.offline.OfflineManager.a(com.amap.location.common.model.FPS, boolean):com.amap.location.common.model.AmapLoc");
    }

    public synchronized void a(@NonNull Context context, @NonNull OfflineConfig offlineConfig, @NonNull IOfflineCloudConfig iOfflineCloudConfig) {
        if (this.c == null) {
            if (offlineConfig == null) {
                offlineConfig = new OfflineConfig();
            }
            this.c = offlineConfig;
        }
        if (this.d == null) {
            this.d = new a();
            if (iOfflineCloudConfig != null) {
                this.d.f4599a = iOfflineCloudConfig;
            }
        }
        if (this.f == null) {
            this.f = new bo(context, this.c, this.d);
            this.f.a();
        }
        if (this.g == null) {
            this.e = context.getApplicationContext();
            a.a(this.e, this.c, this.d);
            this.g = new b(context, this.c, this.d);
            if (!this.g.a(this.e.getPackageName()) && this.h == null) {
                d();
            }
        }
    }

    public synchronized void a(@NonNull FPS fps) {
        a(fps, 0, true, this.e.getPackageName());
    }

    public synchronized void a(@NonNull FPS fps, AmapLoc amapLoc) {
        a(fps, amapLoc, this.e.getPackageName());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(@android.support.annotation.NonNull com.amap.location.common.model.FPS r2, com.amap.location.common.model.AmapLoc r3, java.lang.String r4) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c()     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            com.amap.openapi.bp r0 = r1.h     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x001a
            com.amap.location.offline.b r0 = r1.g     // Catch:{ all -> 0x0021 }
            boolean r2 = r0.a((com.amap.location.common.model.FPS) r2, (com.amap.location.common.model.AmapLoc) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0021 }
            if (r2 != 0) goto L_0x0018
            r1.d()     // Catch:{ all -> 0x0021 }
        L_0x0018:
            monitor-exit(r1)
            return
        L_0x001a:
            com.amap.openapi.bp r4 = r1.h     // Catch:{ all -> 0x0021 }
            r4.a((com.amap.location.common.model.FPS) r2, (com.amap.location.common.model.AmapLoc) r3)     // Catch:{ all -> 0x0021 }
            monitor-exit(r1)
            return
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.location.offline.OfflineManager.a(com.amap.location.common.model.FPS, com.amap.location.common.model.AmapLoc, java.lang.String):void");
    }

    public synchronized void a(OfflineConfig offlineConfig) {
        if (offlineConfig != null) {
            if (this.g != null) {
                this.c = offlineConfig;
                this.g.a(this.c);
                if (this.h != null) {
                    this.h.a(this.c);
                }
            }
        }
    }

    public synchronized void b() {
        OfflineConfig offlineConfig = this.c;
        this.c = null;
        this.d = null;
        this.g = null;
        if (this.f != null) {
            this.f.b();
            this.f = null;
        }
        if (this.h != null) {
            this.h.b();
            this.h = null;
        }
        a.a(offlineConfig);
    }

    public synchronized boolean c() {
        return (this.g == null || this.c == null || !this.c.q || this.d == null || !this.d.a()) ? false : true;
    }
}
