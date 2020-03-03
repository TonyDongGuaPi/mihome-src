package com.mijia.model;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import com.mijia.debug.Tag;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public abstract class BaseFileLoadManager {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f7949a = 1;
    protected static final int b = 2;
    protected static final int c = 20000;
    private static final String i = "BaseFileLoadManager";
    protected volatile boolean d = false;
    protected volatile boolean e = false;
    protected int f = 0;
    /* access modifiers changed from: protected */
    public CameraDevice g;
    protected Handler h = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    LogUtil.a(Tag.d, "SYNC_DATA");
                    BaseFileLoadManager.this.a((Callback<Void>) null, true);
                    BaseFileLoadManager.this.h.removeMessages(1);
                    if (BaseFileLoadManager.this.f >= 0) {
                        BaseFileLoadManager.this.h.sendEmptyMessageDelayed(1, (long) BaseFileLoadManager.this.f);
                        return;
                    }
                    return;
                case 2:
                    LogUtil.b(Tag.d, "time out");
                    BaseFileLoadManager.this.a(-2, "TIME_OUT_FAILED");
                    return;
                default:
                    return;
            }
        }
    };
    private LocalBroadcastManager j;

    /* access modifiers changed from: protected */
    public abstract void a(Callback<Void> callback);

    public void c() {
    }

    public abstract String f();

    public BaseFileLoadManager(CameraDevice cameraDevice) {
        this.g = cameraDevice;
        this.j = LocalBroadcastManager.getInstance(XmPluginHostApi.instance().context());
    }

    public synchronized boolean a() {
        return this.d;
    }

    public void a(int i2) {
        LogUtil.a(Tag.d, "startSync:" + i2);
        this.h.removeMessages(1);
        this.h.removeMessages(2);
        this.f = i2;
        this.e = false;
        this.h.sendEmptyMessageDelayed(1, (long) this.f);
    }

    public void b() {
        LogUtil.a(Tag.d, "stopSync");
        this.f = 0;
        this.e = false;
        this.h.removeMessages(1);
        this.h.removeMessages(2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.xiaomi.smarthome.device.api.Callback<java.lang.Void> r4, boolean r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "SdCard"
            java.lang.String r1 = "syncData"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r0, r1)     // Catch:{ all -> 0x0049 }
            boolean r0 = r3.e     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x0018
            if (r4 == 0) goto L_0x0018
            java.lang.String r4 = "SdCard"
            java.lang.String r5 = "mIsDataSyncing"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r4, r5)     // Catch:{ all -> 0x0049 }
            monitor-exit(r3)
            return
        L_0x0018:
            boolean r0 = r3.d     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x002d
            if (r5 != 0) goto L_0x002d
            java.lang.String r5 = "SdCard"
            java.lang.String r0 = "mHasDataSynced"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r0)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x002b
            r5 = 0
            r4.onSuccess(r5)     // Catch:{ all -> 0x0049 }
        L_0x002b:
            monitor-exit(r3)
            return
        L_0x002d:
            android.os.Handler r5 = r3.h     // Catch:{ all -> 0x0049 }
            r0 = 2
            r5.removeMessages(r0)     // Catch:{ all -> 0x0049 }
            android.os.Handler r5 = r3.h     // Catch:{ all -> 0x0049 }
            r1 = 20000(0x4e20, double:9.8813E-320)
            r5.sendEmptyMessageDelayed(r0, r1)     // Catch:{ all -> 0x0049 }
            r5 = 1
            r3.e = r5     // Catch:{ all -> 0x0049 }
            java.lang.String r5 = "SdCard"
            java.lang.String r0 = "doSyncData"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r5, r0)     // Catch:{ all -> 0x0049 }
            r3.a((com.xiaomi.smarthome.device.api.Callback<java.lang.Void>) r4)     // Catch:{ all -> 0x0049 }
            monitor-exit(r3)
            return
        L_0x0049:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.model.BaseFileLoadManager.a(com.xiaomi.smarthome.device.api.Callback, boolean):void");
    }

    public void d() {
        this.j.sendBroadcast(new Intent(f()));
    }

    /* access modifiers changed from: protected */
    public synchronized void e() {
        LogUtil.a(Tag.d, "notify success");
        this.h.removeMessages(2);
        this.d = true;
        this.e = false;
        d();
    }

    /* access modifiers changed from: protected */
    public synchronized void a(int i2, String str) {
        LogUtil.b(Tag.d, "notifyFailed:" + i2 + " info:" + str);
        this.h.removeMessages(2);
        this.e = false;
    }
}
