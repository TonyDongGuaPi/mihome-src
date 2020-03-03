package com.xiaomi.push;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.net.Socket;

public abstract class gb extends fu {
    protected Exception o = null;
    protected Socket p;
    String q = null;
    protected XMPushService r;
    protected volatile long s = 0;
    protected volatile long t = 0;
    protected volatile long u = 0;
    private String v;
    private int w;

    public gb(XMPushService xMPushService, fv fvVar) {
        super(xMPushService, fvVar);
        this.r = xMPushService;
    }

    private void a(fv fvVar) {
        a(fvVar.c(), fvVar.a());
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x023c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01c6 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01c6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r19, int r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            r3 = 0
            r1.o = r3
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "get bucket for host : "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.Integer r4 = com.xiaomi.channel.commonutils.logger.b.e(r4)
            int r4 = r4.intValue()
            com.xiaomi.push.cx r12 = r18.b((java.lang.String) r19)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Integer) r4)
            r4 = 1
            if (r12 == 0) goto L_0x0039
            java.util.ArrayList r3 = r12.a((boolean) r4)
        L_0x0039:
            boolean r5 = r3.isEmpty()
            if (r5 == 0) goto L_0x0042
            r3.add(r0)
        L_0x0042:
            r5 = 0
            r1.u = r5
            com.xiaomi.push.service.XMPushService r0 = r1.r
            java.lang.String r13 = com.xiaomi.push.az.k(r0)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.util.Iterator r3 = r3.iterator()
            r0 = 0
            r5 = 0
        L_0x0057:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0231
            java.lang.Object r0 = r3.next()
            r11 = r0
            java.lang.String r11 = (java.lang.String) r11
            long r15 = java.lang.System.currentTimeMillis()
            int r0 = r1.b
            int r0 = r0 + r4
            r1.b = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r0.<init>()     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r6 = "begin to connect to "
            r0.append(r6)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r0.append(r11)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.net.Socket r0 = r18.s()     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r1.p = r0     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.net.InetSocketAddress r0 = com.xiaomi.push.cz.b(r11, r2)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.net.Socket r6 = r1.p     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r7 = 8000(0x1f40, float:1.121E-41)
            r6.connect(r0, r7)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r0 = "tcp connected"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            java.net.Socket r0 = r1.p     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r0.setTcpNoDelay(r4)     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r1.v = r11     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            r18.c()     // Catch:{ Exception -> 0x015f, Throwable -> 0x00ee, all -> 0x00ea }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r0 = 0
            long r5 = r5 - r15
            r1.c = r5     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r1.j = r13     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            if (r12 == 0) goto L_0x00b6
            long r7 = r1.c     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r9 = 0
            r5 = r12
            r6 = r11
            r5.b(r6, r7, r9)     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
        L_0x00b6:
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r1.u = r5     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r0.<init>()     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r5 = "connected to "
            r0.append(r5)     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r0.append(r11)     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r5 = " in "
            r0.append(r5)     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            long r5 = r1.c     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r0.append(r5)     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)     // Catch:{ Exception -> 0x00e5, Throwable -> 0x00e1, all -> 0x00de }
            r17 = 1
            goto L_0x0233
        L_0x00de:
            r0 = move-exception
            goto L_0x01cd
        L_0x00e1:
            r0 = move-exception
            r17 = 1
            goto L_0x00f1
        L_0x00e5:
            r0 = move-exception
            r17 = 1
            goto L_0x0162
        L_0x00ea:
            r0 = move-exception
            r4 = r5
            goto L_0x01cd
        L_0x00ee:
            r0 = move-exception
            r17 = r5
        L_0x00f1:
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ all -> 0x01ca }
            java.lang.String r6 = "abnormal exception"
            r5.<init>(r6, r0)     // Catch:{ all -> 0x01ca }
            r1.o = r5     // Catch:{ all -> 0x01ca }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r0)     // Catch:{ all -> 0x01ca }
            if (r17 != 0) goto L_0x01c6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "SMACK: Could not connect to:"
            r0.append(r5)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r0)
            java.lang.String r0 = "SMACK: Could not connect to "
            r14.append(r0)
            r14.append(r11)
            java.lang.String r0 = " port:"
            r14.append(r0)
            r14.append(r2)
            java.lang.String r0 = " err:"
            r14.append(r0)
            java.lang.Exception r0 = r1.o
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r14.append(r0)
            java.lang.String r0 = "\n"
            r14.append(r0)
            java.lang.Exception r0 = r1.o
            com.xiaomi.push.hi.a((java.lang.String) r11, (java.lang.Exception) r0)
            if (r12 == 0) goto L_0x0151
            long r5 = java.lang.System.currentTimeMillis()
            long r7 = r5 - r15
            r9 = 0
            java.lang.Exception r0 = r1.o
            r5 = r12
            r6 = r11
            r11 = r0
            r5.b(r6, r7, r9, r11)
        L_0x0151:
            com.xiaomi.push.service.XMPushService r0 = r1.r
            java.lang.String r0 = com.xiaomi.push.az.k(r0)
            boolean r0 = android.text.TextUtils.equals(r13, r0)
            if (r0 != 0) goto L_0x01c6
            goto L_0x0233
        L_0x015f:
            r0 = move-exception
            r17 = r5
        L_0x0162:
            r1.o = r0     // Catch:{ all -> 0x01ca }
            if (r17 != 0) goto L_0x01c6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "SMACK: Could not connect to:"
            r0.append(r5)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r0)
            java.lang.String r0 = "SMACK: Could not connect to "
            r14.append(r0)
            r14.append(r11)
            java.lang.String r0 = " port:"
            r14.append(r0)
            r14.append(r2)
            java.lang.String r0 = " err:"
            r14.append(r0)
            java.lang.Exception r0 = r1.o
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r14.append(r0)
            java.lang.String r0 = "\n"
            r14.append(r0)
            java.lang.Exception r0 = r1.o
            com.xiaomi.push.hi.a((java.lang.String) r11, (java.lang.Exception) r0)
            if (r12 == 0) goto L_0x01b8
            long r5 = java.lang.System.currentTimeMillis()
            long r7 = r5 - r15
            r9 = 0
            java.lang.Exception r0 = r1.o
            r5 = r12
            r6 = r11
            r11 = r0
            r5.b(r6, r7, r9, r11)
        L_0x01b8:
            com.xiaomi.push.service.XMPushService r0 = r1.r
            java.lang.String r0 = com.xiaomi.push.az.k(r0)
            boolean r0 = android.text.TextUtils.equals(r13, r0)
            if (r0 != 0) goto L_0x01c6
            goto L_0x0233
        L_0x01c6:
            r5 = r17
            goto L_0x0057
        L_0x01ca:
            r0 = move-exception
            r4 = r17
        L_0x01cd:
            if (r4 != 0) goto L_0x0230
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "SMACK: Could not connect to:"
            r3.append(r5)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r3)
            java.lang.String r3 = "SMACK: Could not connect to "
            r14.append(r3)
            r14.append(r11)
            java.lang.String r3 = " port:"
            r14.append(r3)
            r14.append(r2)
            java.lang.String r2 = " err:"
            r14.append(r2)
            java.lang.Exception r2 = r1.o
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getSimpleName()
            r14.append(r2)
            java.lang.String r2 = "\n"
            r14.append(r2)
            java.lang.Exception r2 = r1.o
            com.xiaomi.push.hi.a((java.lang.String) r11, (java.lang.Exception) r2)
            if (r12 == 0) goto L_0x0221
            long r2 = java.lang.System.currentTimeMillis()
            long r7 = r2 - r15
            r9 = 0
            java.lang.Exception r2 = r1.o
            r5 = r12
            r6 = r11
            r11 = r2
            r5.b(r6, r7, r9, r11)
        L_0x0221:
            com.xiaomi.push.service.XMPushService r2 = r1.r
            java.lang.String r2 = com.xiaomi.push.az.k(r2)
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 != 0) goto L_0x0230
            r17 = r4
            goto L_0x0233
        L_0x0230:
            throw r0
        L_0x0231:
            r17 = r5
        L_0x0233:
            com.xiaomi.push.db r0 = com.xiaomi.push.db.a()
            r0.c()
            if (r17 == 0) goto L_0x023d
            return
        L_0x023d:
            com.xiaomi.push.gf r0 = new com.xiaomi.push.gf
            java.lang.String r2 = r14.toString()
            r0.<init>((java.lang.String) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.gb.a(java.lang.String, int):void");
    }

    /* access modifiers changed from: protected */
    public synchronized void a(int i, Exception exc) {
        if (l() != 2) {
            a(2, i, exc);
            this.i = "";
            try {
                this.p.close();
            } catch (Throwable unused) {
            }
            this.s = 0;
            this.t = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.u < 300000) {
            if (az.c(this.r)) {
                this.w++;
                if (this.w >= 2) {
                    String e = e();
                    b.a("max short conn time reached, sink down current host:" + e);
                    a(e, 0, exc);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        this.w = 0;
    }

    /* access modifiers changed from: protected */
    public void a(String str, long j, Exception exc) {
        cx a2 = db.a().a(fv.a(), false);
        if (a2 != null) {
            a2.b(str, j, 0, exc);
            db.a().c();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(boolean z);

    public void a(fn[] fnVarArr) {
        throw new gf("Don't support send Blob");
    }

    /* access modifiers changed from: package-private */
    public cx b(String str) {
        cx a2 = db.a().a(str, false);
        if (!a2.b()) {
            gx.a((Runnable) new ge(this, str));
        }
        return a2;
    }

    public void b(int i, Exception exc) {
        a(i, exc);
        if ((exc != null || i == 18) && this.u != 0) {
            a(exc);
        }
    }

    public void b(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        a(z);
        if (!z) {
            this.r.a((XMPushService.i) new gc(this, 13, currentTimeMillis), 10000);
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void c() {
    }

    public void c(int i, Exception exc) {
        this.r.a((XMPushService.i) new gd(this, 2, i, exc));
    }

    public String e() {
        return this.v;
    }

    public String q() {
        return this.i;
    }

    public synchronized void r() {
        try {
            if (!j()) {
                if (!i()) {
                    a(0, 0, (Exception) null);
                    a(this.l);
                    return;
                }
            }
            b.a("WARNING: current xmpp has connected");
        } catch (IOException e) {
            throw new gf((Throwable) e);
        }
    }

    public Socket s() {
        return new Socket();
    }

    public void t() {
        this.s = SystemClock.elapsedRealtime();
    }

    public void u() {
        this.t = SystemClock.elapsedRealtime();
    }
}
