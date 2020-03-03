package com.xiaomi.base.utils;

import android.os.Handler;
import android.os.Looper;

public class DispatchQueue extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public volatile Handler f10021a = null;
    private final Object b = new Object();

    public DispatchQueue(String str) {
        setName(str);
        start();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.f10021a.sendMessage(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.os.Message r4, int r5) {
        /*
            r3 = this;
        L_0x0000:
            android.os.Handler r0 = r3.f10021a
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Object r0 = r3.b     // Catch:{ Throwable -> 0x0025 }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x0025 }
            java.lang.Object r1 = r3.b     // Catch:{ all -> 0x0022 }
            r1.wait()     // Catch:{ all -> 0x0022 }
            android.os.Handler r1 = r3.f10021a     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x0020
            if (r5 <= 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            android.os.Handler r0 = r3.f10021a
            long r1 = (long) r5
            r0.sendMessageDelayed(r4, r1)
            goto L_0x0005
        L_0x001b:
            android.os.Handler r1 = r3.f10021a     // Catch:{ all -> 0x0022 }
            r1.sendMessage(r4)     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r1     // Catch:{ Throwable -> 0x0025 }
        L_0x0025:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.DispatchQueue.a(android.os.Message, int):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Runnable r3) {
        /*
            r2 = this;
            android.os.Handler r0 = r2.f10021a
            if (r0 == 0) goto L_0x0005
            goto L_0x0021
        L_0x0005:
            java.lang.Object r0 = r2.b
            monitor-enter(r0)
            android.os.Handler r1 = r2.f10021a     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x000d
            goto L_0x0020
        L_0x000d:
            java.lang.Object r1 = r2.b     // Catch:{ Throwable -> 0x001c }
            r1.wait()     // Catch:{ Throwable -> 0x001c }
            android.os.Handler r1 = r2.f10021a     // Catch:{ Throwable -> 0x001c }
            if (r1 == 0) goto L_0x0020
            android.os.Handler r1 = r2.f10021a     // Catch:{ Throwable -> 0x001c }
            r1.removeCallbacks(r3)     // Catch:{ Throwable -> 0x001c }
            goto L_0x0020
        L_0x001c:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
        L_0x0021:
            return
        L_0x0022:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.DispatchQueue.a(java.lang.Runnable):void");
    }

    public void a() {
        if (this.f10021a != null) {
            this.f10021a.removeCallbacksAndMessages((Object) null);
        }
    }

    public void b(Runnable runnable) {
        a(runnable, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0024, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Runnable r5, long r6) {
        /*
            r4 = this;
            android.os.Handler r0 = r4.f10021a
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Object r0 = r4.b
            monitor-enter(r0)
            android.os.Handler r1 = r4.f10021a     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x000d
            goto L_0x0029
        L_0x000d:
            java.lang.Object r1 = r4.b     // Catch:{ Throwable -> 0x0025 }
            r1.wait()     // Catch:{ Throwable -> 0x0025 }
            android.os.Handler r1 = r4.f10021a     // Catch:{ Throwable -> 0x0025 }
            if (r1 == 0) goto L_0x0023
            r1 = 0
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x001e
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            goto L_0x002a
        L_0x001e:
            android.os.Handler r1 = r4.f10021a     // Catch:{ Throwable -> 0x0025 }
            r1.post(r5)     // Catch:{ Throwable -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0025:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0030 }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
        L_0x002a:
            android.os.Handler r0 = r4.f10021a
            r0.postDelayed(r5, r6)
            goto L_0x0005
        L_0x0030:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.DispatchQueue.a(java.lang.Runnable, long):void");
    }

    public void run() {
        Looper.prepare();
        synchronized (this.b) {
            this.f10021a = new Handler();
            this.b.notify();
            Looper.loop();
        }
    }
}
