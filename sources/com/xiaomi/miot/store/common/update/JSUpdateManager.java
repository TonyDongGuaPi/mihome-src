package com.xiaomi.miot.store.common.update;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.xiaomi.miot.store.utils.Utils;
import com.xiaomi.youpin.log.LogUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class JSUpdateManager implements IJSUpdate {

    /* renamed from: a  reason: collision with root package name */
    static boolean f11404a = true;
    private static final String b = "JSUpdateManager";
    private static final String c = "work_thread";
    private static final int d = 1;
    private static final int e = 2;
    private static final Semaphore l = new Semaphore(1);
    private WorkHandlerThread f = new WorkHandlerThread(c);
    /* access modifiers changed from: private */
    public Handler g;
    /* access modifiers changed from: private */
    public Config h;
    /* access modifiers changed from: private */
    public IPackageLoader i;
    /* access modifiers changed from: private */
    public IPackageLoader j;
    /* access modifiers changed from: private */
    public int k;

    static /* synthetic */ int d(JSUpdateManager jSUpdateManager) {
        int i2 = jSUpdateManager.k;
        jSUpdateManager.k = i2 + 1;
        return i2;
    }

    public JSUpdateManager(Config config, IPackageLoader iPackageLoader, IPackageLoader iPackageLoader2) {
        this.h = config;
        this.f.start();
        this.g = new Handler(this.f.getLooper(), this.f);
        this.i = iPackageLoader;
        this.j = iPackageLoader2;
    }

    public void a() {
        this.g.removeCallbacksAndMessages((Object) null);
        this.f.quit();
    }

    public void a(Context context, Callback callback) {
        if (callback != null) {
            if (context == null) {
                callback.a((Map<String, String>) null);
                return;
            }
            HashMap hashMap = new HashMap();
            if (this.h.a()) {
                callback.a((Map<String, String>) null);
                return;
            }
            boolean b2 = b();
            if (Thread.currentThread().isInterrupted()) {
                LogUtils.d(b, "acquire permit success but current thread has already been interrupted, so return.");
                if (b2) {
                    c();
                }
                callback.a((Map<String, String>) null);
                return;
            }
            this.k = 0;
            long currentTimeMillis = System.currentTimeMillis() - this.h.c();
            UpdateRequest updateRequest = new UpdateRequest(this.h.b(), this.h.i(), this.h.d(), callback);
            File file = new File(updateRequest.c);
            file.mkdirs();
            if (f11404a) {
                f11404a = false;
                Utils.a(file, updateRequest.f11406a);
            }
            File file2 = new File(new File(file, String.format("%sD", new Object[]{updateRequest.f11406a})), updateRequest.b);
            if (this.h.f() || !file2.exists()) {
                LogUtils.d(b, "need to download a whole file,isForceUpdate:" + this.h.f() + ",bundle exists:" + file2.exists());
                updateRequest.f11406a = null;
            }
            if (TextUtils.isEmpty(updateRequest.f11406a) || this.h.g()) {
                LogUtils.d(b, "download bundle from assets.");
                this.g.obtainMessage(2, updateRequest).sendToTarget();
                return;
            }
            LogUtils.d(b, "download bundle from server.");
            LogUtils.d(b, String.format("old eTag: %s, cache dir: %s", new Object[]{updateRequest.f11406a, file.getParent()}));
            if (TextUtils.isEmpty(updateRequest.f11406a) || currentTimeMillis >= this.h.h()) {
                if (this.h.j() != ReloadStrategy.APP_START && !TextUtils.isEmpty(updateRequest.f11406a)) {
                    hashMap.put(Constants.f11398a, file2.getPath());
                    hashMap.put(Constants.b, updateRequest.f11406a);
                    hashMap.put(Constants.d, String.valueOf(this.h.c()));
                    callback.a(hashMap);
                    LogUtils.d(b, "reloadstrategy:" + this.h.j() + ",callback to ui.");
                }
                this.g.obtainMessage(1, updateRequest).sendToTarget();
                return;
            }
            hashMap.put(Constants.f11398a, file2.getPath());
            hashMap.put(Constants.b, updateRequest.f11406a);
            hashMap.put(Constants.d, String.valueOf(this.h.c()));
            callback.a(hashMap);
            LogUtils.d(b, "time between now and last update time is shorter than the update period:" + this.h.h() + ",so this time will not check update.");
            c();
        }
    }

    private class WorkHandlerThread extends HandlerThread implements Handler.Callback {
        public WorkHandlerThread(String str) {
            super(str);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x005c, code lost:
            r5 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0074, code lost:
            if (android.text.TextUtils.equals(r0.f11406a, r1.f11407a) != false) goto L_0x005c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
            if (r1 == null) goto L_0x005c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0087, code lost:
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0088, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0089, code lost:
            if (r1 == null) goto L_0x0098;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0095, code lost:
            if (com.xiaomi.miot.store.common.update.JSUpdateManager.b(r9.f11405a).g() == false) goto L_0x0098;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0097, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0098, code lost:
            r5 = new com.xiaomi.miot.store.common.update.JSPackageHandler().a(r0, r1, r5);
            com.xiaomi.youpin.log.LogUtils.d(com.xiaomi.miot.store.common.update.JSUpdateManager.b, "handle js package finish with result:" + r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b8, code lost:
            if (r5 != 1) goto L_0x00f9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ba, code lost:
            com.xiaomi.miot.store.common.update.JSUpdateManager.d(r9.f11405a);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00cf, code lost:
            if (com.xiaomi.miot.store.common.update.JSUpdateManager.e(r9.f11405a) > com.xiaomi.miot.store.common.update.JSUpdateManager.b(r9.f11405a).k()) goto L_0x00f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d1, code lost:
            com.xiaomi.youpin.log.LogUtils.d(com.xiaomi.miot.store.common.update.JSUpdateManager.b, "handle js failed so retry.");
            com.xiaomi.miot.store.common.RNAppStoreApiManager.a().j().handleHiddenException("", (java.lang.Throwable) null);
            com.xiaomi.miot.store.common.update.JSUpdateManager.f(r9.f11405a).obtainMessage(1, r0).sendToTarget();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00f3, code lost:
            com.xiaomi.miot.store.common.update.JSUpdateManager.g(r9.f11405a);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fb, code lost:
            if (r10.what != 2) goto L_0x0126;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fd, code lost:
            if (r1 == null) goto L_0x0103;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ff, code lost:
            r0.f11406a = r1.f11407a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0103, code lost:
            if (r1 == null) goto L_0x0118;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x010f, code lost:
            if (com.xiaomi.miot.store.common.update.JSUpdateManager.b(r9.f11405a).g() != false) goto L_0x0112;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0112, code lost:
            com.xiaomi.miot.store.common.update.JSUpdateManager.g(r9.f11405a);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0118, code lost:
            com.xiaomi.miot.store.common.update.JSUpdateManager.f(r9.f11405a).obtainMessage(1, r0).sendToTarget();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0125, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0126, code lost:
            com.xiaomi.miot.store.common.update.JSUpdateManager.g(r9.f11405a);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0137, code lost:
            if (com.xiaomi.miot.store.common.update.JSUpdateManager.b(r9.f11405a).j() != com.xiaomi.miot.store.common.update.ReloadStrategy.APP_START) goto L_0x0154;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0139, code lost:
            if (r5 == 2) goto L_0x014f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x013b, code lost:
            if (r5 != 1) goto L_0x0154;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x014d, code lost:
            if (com.xiaomi.miot.store.common.update.JSUpdateManager.e(r9.f11405a) <= com.xiaomi.miot.store.common.update.JSUpdateManager.b(r9.f11405a).k()) goto L_0x0154;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x014f, code lost:
            com.xiaomi.miot.store.common.update.JSUpdateManager.a(r9.f11405a, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0154, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
            if (com.xiaomi.miot.store.common.update.JSUpdateManager.b(r9.f11405a).j() != com.xiaomi.miot.store.common.update.ReloadStrategy.APP_START) goto L_0x0088;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean handleMessage(android.os.Message r10) {
            /*
                r9 = this;
                java.lang.Object r0 = r10.obj
                com.xiaomi.miot.store.common.update.UpdateRequest r0 = (com.xiaomi.miot.store.common.update.UpdateRequest) r0
                int r1 = r10.what
                r2 = 0
                r3 = 0
                r4 = 1
                switch(r1) {
                    case 1: goto L_0x003c;
                    case 2: goto L_0x000e;
                    default: goto L_0x000c;
                }
            L_0x000c:
                goto L_0x0087
            L_0x000e:
                com.xiaomi.miot.store.common.update.JSUpdateManager r1 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.IPackageLoader r1 = r1.j
                if (r1 == 0) goto L_0x0023
                com.xiaomi.miot.store.common.update.JSUpdateManager r1 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.IPackageLoader r1 = r1.j
                java.lang.String r5 = r0.c
                com.xiaomi.miot.store.common.update.UpdateResponse r1 = r1.a(r5, r2)
                goto L_0x0024
            L_0x0023:
                r1 = r2
            L_0x0024:
                java.lang.String r5 = "JSUpdateManager"
                java.lang.String r6 = "download bundle from assets finished!"
                com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r5, (java.lang.String) r6)
                if (r1 == 0) goto L_0x005c
                com.xiaomi.miot.store.common.update.JSUpdateManager r5 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r5 = r5.h
                com.xiaomi.miot.store.common.update.ReloadStrategy r5 = r5.j()
                com.xiaomi.miot.store.common.update.ReloadStrategy r6 = com.xiaomi.miot.store.common.update.ReloadStrategy.APP_START
                if (r5 != r6) goto L_0x0088
                goto L_0x005c
            L_0x003c:
                com.xiaomi.miot.store.common.update.JSUpdateManager r1 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.IPackageLoader r1 = r1.i
                if (r1 != 0) goto L_0x0045
                goto L_0x0087
            L_0x0045:
                com.xiaomi.miot.store.common.update.JSUpdateManager r1 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.IPackageLoader r1 = r1.i
                java.lang.String r5 = r0.c
                java.lang.String r6 = r0.f11406a
                com.xiaomi.miot.store.common.update.UpdateResponse r1 = r1.a(r5, r6)
                java.lang.String r5 = "JSUpdateManager"
                java.lang.String r6 = "download bundle from server finished!"
                com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r5, (java.lang.String) r6)
                if (r1 != 0) goto L_0x005e
            L_0x005c:
                r5 = 0
                goto L_0x0089
            L_0x005e:
                com.xiaomi.miot.store.common.update.JSUpdateManager r5 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r5 = r5.h
                com.xiaomi.miot.store.common.update.ReloadStrategy r5 = r5.j()
                com.xiaomi.miot.store.common.update.ReloadStrategy r6 = com.xiaomi.miot.store.common.update.ReloadStrategy.APP_START
                if (r5 == r6) goto L_0x0076
                java.lang.String r5 = r0.f11406a
                java.lang.String r6 = r1.f11407a
                boolean r5 = android.text.TextUtils.equals(r5, r6)
                if (r5 != 0) goto L_0x005c
            L_0x0076:
                com.xiaomi.miot.store.common.update.JSUpdateManager r5 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r5 = r5.h
                com.xiaomi.miot.store.common.update.ReloadStrategy r5 = r5.j()
                com.xiaomi.miot.store.common.update.ReloadStrategy r6 = com.xiaomi.miot.store.common.update.ReloadStrategy.APP_START
                if (r5 != r6) goto L_0x0088
                if (r1 != 0) goto L_0x0088
                goto L_0x005c
            L_0x0087:
                r1 = r2
            L_0x0088:
                r5 = 1
            L_0x0089:
                if (r1 == 0) goto L_0x0098
                com.xiaomi.miot.store.common.update.JSUpdateManager r6 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r6 = r6.h
                boolean r6 = r6.g()
                if (r6 == 0) goto L_0x0098
                r5 = 1
            L_0x0098:
                com.xiaomi.miot.store.common.update.JSPackageHandler r6 = new com.xiaomi.miot.store.common.update.JSPackageHandler
                r6.<init>()
                int r5 = r6.a(r0, r1, r5)
                java.lang.String r6 = "JSUpdateManager"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r8 = "handle js package finish with result:"
                r7.append(r8)
                r7.append(r5)
                java.lang.String r7 = r7.toString()
                com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r6, (java.lang.String) r7)
                r6 = 2
                if (r5 != r4) goto L_0x00f9
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.JSUpdateManager.d(r10)
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                int r10 = r10.k
                com.xiaomi.miot.store.common.update.JSUpdateManager r1 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r1 = r1.h
                int r1 = r1.k()
                if (r10 > r1) goto L_0x00f3
                java.lang.String r10 = "JSUpdateManager"
                java.lang.String r1 = "handle js failed so retry."
                com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r10, (java.lang.String) r1)
                com.xiaomi.miot.store.common.RNAppStoreApiManager r10 = com.xiaomi.miot.store.common.RNAppStoreApiManager.a()
                com.xiaomi.miot.store.api.RNStoreApiProvider r10 = r10.j()
                java.lang.String r1 = ""
                r10.handleHiddenException(r1, r2)
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                android.os.Handler r10 = r10.g
                android.os.Message r10 = r10.obtainMessage(r4, r0)
                r10.sendToTarget()
                goto L_0x012b
            L_0x00f3:
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                r10.c()
                goto L_0x012b
            L_0x00f9:
                int r10 = r10.what
                if (r10 != r6) goto L_0x0126
                if (r1 == 0) goto L_0x0103
                java.lang.String r10 = r1.f11407a
                r0.f11406a = r10
            L_0x0103:
                if (r1 == 0) goto L_0x0118
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r10 = r10.h
                boolean r10 = r10.g()
                if (r10 != 0) goto L_0x0112
                goto L_0x0118
            L_0x0112:
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                r10.c()
                goto L_0x0125
            L_0x0118:
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                android.os.Handler r10 = r10.g
                android.os.Message r10 = r10.obtainMessage(r4, r0)
                r10.sendToTarget()
            L_0x0125:
                return r4
            L_0x0126:
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                r10.c()
            L_0x012b:
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r10 = r10.h
                com.xiaomi.miot.store.common.update.ReloadStrategy r10 = r10.j()
                com.xiaomi.miot.store.common.update.ReloadStrategy r1 = com.xiaomi.miot.store.common.update.ReloadStrategy.APP_START
                if (r10 != r1) goto L_0x0154
                if (r5 == r6) goto L_0x014f
                if (r5 != r4) goto L_0x0154
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                int r10 = r10.k
                com.xiaomi.miot.store.common.update.JSUpdateManager r1 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                com.xiaomi.miot.store.common.update.Config r1 = r1.h
                int r1 = r1.k()
                if (r10 <= r1) goto L_0x0154
            L_0x014f:
                com.xiaomi.miot.store.common.update.JSUpdateManager r10 = com.xiaomi.miot.store.common.update.JSUpdateManager.this
                r10.a((com.xiaomi.miot.store.common.update.UpdateRequest) r0)
            L_0x0154:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.common.update.JSUpdateManager.WorkHandlerThread.handleMessage(android.os.Message):boolean");
        }
    }

    private boolean b() {
        LogUtils.d(b, "acquire permit to upload js,avaiable num:" + l.availablePermits());
        try {
            l.acquire();
            LogUtils.d(b, "acquire permit success!");
            return true;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            LogUtils.e(b, "acquire permit, but a interrupt exception happened.");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        l.drainPermits();
        l.release();
    }

    /* access modifiers changed from: private */
    public void a(UpdateRequest updateRequest) {
        HashMap hashMap = new HashMap();
        File file = new File(new File(updateRequest.c, String.format("%sD", new Object[]{updateRequest.f11406a})), updateRequest.b);
        if (!TextUtils.isEmpty(updateRequest.f11406a) && file.exists()) {
            hashMap.put(Constants.f11398a, file.getPath());
            hashMap.put(Constants.b, updateRequest.f11406a);
            hashMap.put(Constants.d, String.valueOf(this.h.c()));
            updateRequest.d.a(hashMap);
        } else if (this.j != null) {
            if (new JSPackageHandler().a(updateRequest, this.j.a(updateRequest.c, (String) null), true) != 0) {
                hashMap.put(Constants.f11398a, (Object) null);
                updateRequest.d.a(hashMap);
            }
        }
    }
}
