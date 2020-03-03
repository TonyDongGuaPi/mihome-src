package com.xiaomi.shopviews.adapter.countdown;

import android.os.Handler;
import android.os.SystemClock;

public abstract class CustomCountDownTimer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f13098a = 1;
    private final long b;
    /* access modifiers changed from: private */
    public final long c;
    /* access modifiers changed from: private */
    public long d;
    private long e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;
    private Handler h = new Handler() {
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x005b, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r8) {
            /*
                r7 = this;
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r8 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this
                monitor-enter(r8)
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r0 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                boolean r0 = r0.f     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x005a
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r0 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                boolean r0 = r0.g     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x0014
                goto L_0x005a
            L_0x0014:
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r0 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                long r0 = r0.d     // Catch:{ all -> 0x005c }
                long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x005c }
                r4 = 0
                long r0 = r0 - r2
                r2 = 0
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 > 0) goto L_0x002c
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r0 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                r0.a()     // Catch:{ all -> 0x005c }
                goto L_0x0058
            L_0x002c:
                long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x005c }
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r6 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                r6.a((long) r0)     // Catch:{ all -> 0x005c }
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r0 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                long r0 = r0.c     // Catch:{ all -> 0x005c }
                r6 = 0
                long r4 = r4 + r0
                long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x005c }
                r6 = 0
                long r4 = r4 - r0
            L_0x0043:
                int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r0 >= 0) goto L_0x0050
                com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer r0 = com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.this     // Catch:{ all -> 0x005c }
                long r0 = r0.c     // Catch:{ all -> 0x005c }
                r6 = 0
                long r4 = r4 + r0
                goto L_0x0043
            L_0x0050:
                r0 = 1
                android.os.Message r0 = r7.obtainMessage(r0)     // Catch:{ all -> 0x005c }
                r7.sendMessageDelayed(r0, r4)     // Catch:{ all -> 0x005c }
            L_0x0058:
                monitor-exit(r8)     // Catch:{ all -> 0x005c }
                return
            L_0x005a:
                monitor-exit(r8)     // Catch:{ all -> 0x005c }
                return
            L_0x005c:
                r0 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x005c }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.AnonymousClass1.handleMessage(android.os.Message):void");
        }
    };

    public abstract void a();

    public abstract void a(long j);

    public CustomCountDownTimer(long j, long j2) {
        this.b = j2 > 1000 ? j + 15 : j;
        this.c = j2;
    }

    private synchronized CustomCountDownTimer b(long j) {
        this.f = false;
        if (j <= 0) {
            a();
            return this;
        }
        this.d = SystemClock.elapsedRealtime() + j;
        this.h.sendMessage(this.h.obtainMessage(1));
        return this;
    }

    public final synchronized void b() {
        b(this.b);
    }

    public final synchronized void c() {
        this.f = true;
        this.h.removeMessages(1);
    }

    public final synchronized void d() {
        if (!this.f) {
            this.g = true;
            this.e = this.d - SystemClock.elapsedRealtime();
            this.h.removeMessages(1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void e() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.f     // Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x0014
            boolean r0 = r2.g     // Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x000a
            goto L_0x0014
        L_0x000a:
            r0 = 0
            r2.g = r0     // Catch:{ all -> 0x0016 }
            long r0 = r2.e     // Catch:{ all -> 0x0016 }
            r2.b((long) r0)     // Catch:{ all -> 0x0016 }
            monitor-exit(r2)
            return
        L_0x0014:
            monitor-exit(r2)
            return
        L_0x0016:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.adapter.countdown.CustomCountDownTimer.e():void");
    }
}
