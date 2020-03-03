package com.xiaomi.miot.support.monitor.core.net;

import android.os.Handler;
import android.os.HandlerThread;
import com.xiaomi.miot.support.monitor.MiotMonitorClient;
import com.xiaomi.miot.support.monitor.core.tasks.BaseTask;

public class NetTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    public static final int f11465a = 180000;
    private HandlerThread e = new HandlerThread("netThread");
    /* access modifiers changed from: private */
    public Handler f;
    /* access modifiers changed from: private */
    public Runnable g = new Runnable() {
        public void run() {
            if (NetTask.this.f()) {
                MiotMonitorClient.l();
                if (NetTask.this.f != null) {
                    NetTask.this.f.postDelayed(NetTask.this.g, 180000);
                }
            }
        }
    };

    public String a() {
        return "net";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        super.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r4.e.run();
        r4.f = new android.os.Handler(r4.e.getLooper());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0069, code lost:
        if (com.xiaomi.miot.support.monitor.MiotMonitorManager.a().c().i == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006f, code lost:
        r4.f.postDelayed(r4.g, 180000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0077, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0078, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r4 = this;
            r0 = 180000(0x2bf20, double:8.8932E-319)
            super.b()     // Catch:{ Exception -> 0x0038 }
            android.os.HandlerThread r2 = r4.e     // Catch:{ Exception -> 0x0038 }
            boolean r2 = r2.isAlive()     // Catch:{ Exception -> 0x0038 }
            if (r2 != 0) goto L_0x0020
            android.os.HandlerThread r2 = r4.e     // Catch:{ Exception -> 0x0038 }
            r2.start()     // Catch:{ Exception -> 0x0038 }
            android.os.Handler r2 = new android.os.Handler     // Catch:{ Exception -> 0x0038 }
            android.os.HandlerThread r3 = r4.e     // Catch:{ Exception -> 0x0038 }
            android.os.Looper r3 = r3.getLooper()     // Catch:{ Exception -> 0x0038 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0038 }
            r4.f = r2     // Catch:{ Exception -> 0x0038 }
        L_0x0020:
            com.xiaomi.miot.support.monitor.MiotMonitorManager r2 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ Exception -> 0x0038 }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r2 = r2.c()     // Catch:{ Exception -> 0x0038 }
            boolean r2 = r2.i     // Catch:{ Exception -> 0x0038 }
            if (r2 == 0) goto L_0x007b
            android.os.Handler r2 = r4.f     // Catch:{ Exception -> 0x0038 }
            if (r2 == 0) goto L_0x007b
            android.os.Handler r2 = r4.f     // Catch:{ Exception -> 0x0038 }
            java.lang.Runnable r3 = r4.g     // Catch:{ Exception -> 0x0038 }
            r2.postDelayed(r3, r0)     // Catch:{ Exception -> 0x0038 }
            goto L_0x007b
        L_0x0038:
            super.b()     // Catch:{ Exception -> 0x0077 }
            android.os.HandlerThread r2 = r4.e     // Catch:{ Exception -> 0x0077 }
            boolean r2 = r2.isAlive()     // Catch:{ Exception -> 0x0077 }
            if (r2 != 0) goto L_0x005f
            android.os.HandlerThread r2 = r4.e     // Catch:{ Exception -> 0x0077 }
            java.lang.Thread$State r2 = r2.getState()     // Catch:{ Exception -> 0x0077 }
            java.lang.Thread$State r3 = java.lang.Thread.State.NEW     // Catch:{ Exception -> 0x0077 }
            if (r2 == r3) goto L_0x005f
            android.os.HandlerThread r2 = r4.e     // Catch:{ Exception -> 0x0077 }
            r2.run()     // Catch:{ Exception -> 0x0077 }
            android.os.Handler r2 = new android.os.Handler     // Catch:{ Exception -> 0x0077 }
            android.os.HandlerThread r3 = r4.e     // Catch:{ Exception -> 0x0077 }
            android.os.Looper r3 = r3.getLooper()     // Catch:{ Exception -> 0x0077 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0077 }
            r4.f = r2     // Catch:{ Exception -> 0x0077 }
        L_0x005f:
            com.xiaomi.miot.support.monitor.MiotMonitorManager r2 = com.xiaomi.miot.support.monitor.MiotMonitorManager.a()     // Catch:{ Exception -> 0x0077 }
            com.xiaomi.miot.support.monitor.config.MiotMonitorConfig r2 = r2.c()     // Catch:{ Exception -> 0x0077 }
            boolean r2 = r2.i     // Catch:{ Exception -> 0x0077 }
            if (r2 == 0) goto L_0x007b
            android.os.Handler r2 = r4.f     // Catch:{ Exception -> 0x0077 }
            if (r2 == 0) goto L_0x007b
            android.os.Handler r2 = r4.f     // Catch:{ Exception -> 0x0077 }
            java.lang.Runnable r3 = r4.g     // Catch:{ Exception -> 0x0077 }
            r2.postDelayed(r3, r0)     // Catch:{ Exception -> 0x0077 }
            goto L_0x007b
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.support.monitor.core.net.NetTask.b():void");
    }
}
