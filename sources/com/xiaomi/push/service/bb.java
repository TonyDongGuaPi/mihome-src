package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.al;
import com.xiaomi.push.c;
import com.xiaomi.push.el;
import com.xiaomi.push.em;
import com.xiaomi.push.gx;
import com.xiaomi.push.i;
import com.xiaomi.push.t;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class bb {

    /* renamed from: a  reason: collision with root package name */
    private static bb f12899a = new bb();

    /* renamed from: a  reason: collision with other field name */
    private static String f314a;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with other field name */
    public al.b f315a;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with other field name */
    public el.a f316a;

    /* renamed from: a  reason: collision with other field name */
    private List<a> f317a = new ArrayList();

    public static abstract class a {
        public void a(el.a aVar) {
        }

        public void a(em.b bVar) {
        }
    }

    private bb() {
    }

    public static bb a() {
        return f12899a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public static synchronized String m316a() {
        String str;
        synchronized (bb.class) {
            if (f314a == null) {
                SharedPreferences sharedPreferences = t.a().getSharedPreferences("XMPushServiceConfig", 0);
                f314a = sharedPreferences.getString("DeviceUUID", (String) null);
                if (f314a == null) {
                    f314a = i.a(t.a(), false);
                    if (f314a != null) {
                        sharedPreferences.edit().putString("DeviceUUID", f314a).commit();
                    }
                }
            }
            str = f314a;
        }
        return str;
    }

    private void b() {
        if (this.f316a == null) {
            d();
        }
    }

    private void c() {
        if (this.f315a == null) {
            this.f315a = new bc(this);
            gx.a(this.f315a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.xiaomi.push.t.a()     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = "XMCloudCfg"
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch:{ Exception -> 0x002b }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x002b }
            r2.<init>(r1)     // Catch:{ Exception -> 0x002b }
            com.xiaomi.push.b r0 = com.xiaomi.push.b.a((java.io.InputStream) r2)     // Catch:{ Exception -> 0x0025, all -> 0x0021 }
            com.xiaomi.push.el$a r0 = com.xiaomi.push.el.a.c(r0)     // Catch:{ Exception -> 0x0025, all -> 0x0021 }
            r4.f316a = r0     // Catch:{ Exception -> 0x0025, all -> 0x0021 }
            r2.close()     // Catch:{ Exception -> 0x0025, all -> 0x0021 }
            com.xiaomi.push.y.a((java.io.Closeable) r2)
            goto L_0x0047
        L_0x0021:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x0053
        L_0x0025:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x002c
        L_0x0029:
            r1 = move-exception
            goto L_0x0053
        L_0x002b:
            r1 = move-exception
        L_0x002c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0029 }
            r2.<init>()     // Catch:{ all -> 0x0029 }
            java.lang.String r3 = "load config failure: "
            r2.append(r3)     // Catch:{ all -> 0x0029 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0029 }
            r2.append(r1)     // Catch:{ all -> 0x0029 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0029 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r1)     // Catch:{ all -> 0x0029 }
            com.xiaomi.push.y.a((java.io.Closeable) r0)
        L_0x0047:
            com.xiaomi.push.el$a r0 = r4.f316a
            if (r0 != 0) goto L_0x0052
            com.xiaomi.push.el$a r0 = new com.xiaomi.push.el$a
            r0.<init>()
            r4.f316a = r0
        L_0x0052:
            return
        L_0x0053:
            com.xiaomi.push.y.a((java.io.Closeable) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.bb.d():void");
    }

    private void e() {
        try {
            if (this.f316a != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(t.a().openFileOutput("XMCloudCfg", 0));
                c a2 = c.a((OutputStream) bufferedOutputStream);
                this.f316a.a(a2);
                a2.a();
                bufferedOutputStream.close();
            }
        } catch (Exception e) {
            b.a("save config failure: " + e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public int m317a() {
        b();
        if (this.f316a != null) {
            return this.f316a.d();
        }
        return 0;
    }

    /* renamed from: a  reason: collision with other method in class */
    public el.a m318a() {
        b();
        return this.f316a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public synchronized void m319a() {
        this.f317a.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(em.b bVar) {
        a[] aVarArr;
        if (bVar.i() && bVar.h() > a()) {
            c();
        }
        synchronized (this) {
            aVarArr = (a[]) this.f317a.toArray(new a[this.f317a.size()]);
        }
        for (a a2 : aVarArr) {
            a2.a(bVar);
        }
    }

    public synchronized void a(a aVar) {
        this.f317a.add(aVar);
    }
}
