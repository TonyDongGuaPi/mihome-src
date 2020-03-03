package com.xiaomi.push;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;

public class hf implements fx {

    /* renamed from: a  reason: collision with root package name */
    XMPushService f12770a;
    fu b;
    private int c;
    private Exception d;
    private String e;
    private long f = 0;
    private long g = 0;
    private long h = 0;
    private long i = 0;
    private long j = 0;
    private long k = 0;

    hf(XMPushService xMPushService) {
        this.f12770a = xMPushService;
        this.e = "";
        c();
        int myUid = Process.myUid();
        this.k = TrafficStats.getUidRxBytes(myUid);
        this.j = TrafficStats.getUidTxBytes(myUid);
    }

    private void c() {
        this.g = 0;
        this.i = 0;
        this.f = 0;
        this.h = 0;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (az.c(this.f12770a)) {
            this.f = elapsedRealtime;
        }
        if (this.f12770a.c()) {
            this.h = elapsedRealtime;
        }
    }

    private synchronized void d() {
        b.c("stat connpt = " + this.e + " netDuration = " + this.g + " ChannelDuration = " + this.i + " channelConnectedTime = " + this.h);
        fk fkVar = new fk();
        fkVar.f12736a = 0;
        fkVar.a(fj.CHANNEL_ONLINE_RATE.a());
        fkVar.a(this.e);
        fkVar.d((int) (System.currentTimeMillis() / 1000));
        fkVar.b((int) (this.g / 1000));
        fkVar.c((int) (this.i / 1000));
        hg.a().a(fkVar);
        c();
    }

    /* access modifiers changed from: package-private */
    public Exception a() {
        return this.d;
    }

    public void a(fu fuVar) {
        b();
        this.h = SystemClock.elapsedRealtime();
        hi.a(0, fj.CONN_SUCCESS.a(), fuVar.e(), fuVar.k());
    }

    public void a(fu fuVar, int i2, Exception exc) {
        if (this.c == 0 && this.d == null) {
            this.c = i2;
            this.d = exc;
            hi.b(fuVar.e(), exc);
        }
        if (i2 == 22 && this.h != 0) {
            long g2 = fuVar.g() - this.h;
            if (g2 < 0) {
                g2 = 0;
            }
            this.i += g2 + ((long) (ga.c() / 2));
            this.h = 0;
        }
        b();
        int myUid = Process.myUid();
        long uidRxBytes = TrafficStats.getUidRxBytes(myUid);
        long uidTxBytes = TrafficStats.getUidTxBytes(myUid);
        b.c("Stats rx=" + (uidRxBytes - this.k) + ", tx=" + (uidTxBytes - this.j));
        this.k = uidRxBytes;
        this.j = uidTxBytes;
    }

    public void a(fu fuVar, Exception exc) {
        hi.a(0, fj.CHANNEL_CON_FAIL.a(), 1, fuVar.e(), az.c(this.f12770a) ? 1 : 0);
        b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0070, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r11 = this;
            monitor-enter(r11)
            com.xiaomi.push.service.XMPushService r0 = r11.f12770a     // Catch:{ all -> 0x0071 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r11)
            return
        L_0x0007:
            com.xiaomi.push.service.XMPushService r0 = r11.f12770a     // Catch:{ all -> 0x0071 }
            java.lang.String r0 = com.xiaomi.push.az.k(r0)     // Catch:{ all -> 0x0071 }
            com.xiaomi.push.service.XMPushService r1 = r11.f12770a     // Catch:{ all -> 0x0071 }
            boolean r1 = com.xiaomi.push.az.c(r1)     // Catch:{ all -> 0x0071 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0071 }
            long r4 = r11.f     // Catch:{ all -> 0x0071 }
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x002b
            long r4 = r11.g     // Catch:{ all -> 0x0071 }
            long r8 = r11.f     // Catch:{ all -> 0x0071 }
            r10 = 0
            long r8 = r2 - r8
            long r4 = r4 + r8
            r11.g = r4     // Catch:{ all -> 0x0071 }
            r11.f = r6     // Catch:{ all -> 0x0071 }
        L_0x002b:
            long r4 = r11.h     // Catch:{ all -> 0x0071 }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x003d
            long r4 = r11.i     // Catch:{ all -> 0x0071 }
            long r8 = r11.h     // Catch:{ all -> 0x0071 }
            r10 = 0
            long r8 = r2 - r8
            long r4 = r4 + r8
            r11.i = r4     // Catch:{ all -> 0x0071 }
            r11.h = r6     // Catch:{ all -> 0x0071 }
        L_0x003d:
            if (r1 == 0) goto L_0x006f
            java.lang.String r1 = r11.e     // Catch:{ all -> 0x0071 }
            boolean r1 = android.text.TextUtils.equals(r1, r0)     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x004f
            long r4 = r11.g     // Catch:{ all -> 0x0071 }
            r8 = 30000(0x7530, double:1.4822E-319)
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 > 0) goto L_0x0058
        L_0x004f:
            long r4 = r11.g     // Catch:{ all -> 0x0071 }
            r8 = 5400000(0x5265c0, double:2.6679545E-317)
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x005b
        L_0x0058:
            r11.d()     // Catch:{ all -> 0x0071 }
        L_0x005b:
            r11.e = r0     // Catch:{ all -> 0x0071 }
            long r0 = r11.f     // Catch:{ all -> 0x0071 }
            int r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0065
            r11.f = r2     // Catch:{ all -> 0x0071 }
        L_0x0065:
            com.xiaomi.push.service.XMPushService r0 = r11.f12770a     // Catch:{ all -> 0x0071 }
            boolean r0 = r0.c()     // Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x006f
            r11.h = r2     // Catch:{ all -> 0x0071 }
        L_0x006f:
            monitor-exit(r11)
            return
        L_0x0071:
            r0 = move-exception
            monitor-exit(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hf.b():void");
    }

    public void b(fu fuVar) {
        this.c = 0;
        this.d = null;
        this.b = fuVar;
        this.e = az.k(this.f12770a);
        hi.a(0, fj.CONN_SUCCESS.a());
    }
}
