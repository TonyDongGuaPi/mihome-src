package com.xiaomi.youpin.youpin_network.retry;

import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.youpin_network.NetWorkDependency;
import com.xiaomi.youpin.youpin_network.NetworkConfig;
import com.xiaomi.youpin.youpin_network.NetworkConfigManager;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class YouPinAuthHttpsManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final Object f23864a = new Object();
    private static volatile YouPinAuthHttpsManager b;
    private NetworkConfig c = NetworkConfigManager.a().b();
    private NetWorkDependency d = this.c.a();
    private CopyOnWriteArrayList<HttpsEntity> e = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<HttpsEntity> f = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<RNHttpEntity> g = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<RNHttpEntity> h = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public volatile int i = 0;

    private YouPinAuthHttpsManager() {
    }

    public static YouPinAuthHttpsManager a() {
        if (b == null) {
            synchronized (YouPinAuthHttpsManager.class) {
                if (b == null) {
                    b = new YouPinAuthHttpsManager();
                }
            }
        }
        return b;
    }

    public boolean a(boolean z, HttpsEntity httpsEntity) {
        return a(z, httpsEntity, (RNHttpEntity) null);
    }

    public boolean a(boolean z, RNHttpEntity rNHttpEntity) {
        return a(z, (HttpsEntity) null, rNHttpEntity);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0024, code lost:
        if (r7 == null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0026, code lost:
        if (r6 == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0028, code lost:
        r5.f.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002e, code lost:
        r5.e.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0033, code lost:
        if (r8 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0035, code lost:
        if (r6 == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0037, code lost:
        r5.h.add(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003d, code lost:
        r5.g.add(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0042, code lost:
        if (r0 != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0044, code lost:
        r5.d.a(new com.xiaomi.youpin.youpin_network.retry.YouPinAuthHttpsManager.AnonymousClass1(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(boolean r6, com.xiaomi.youpin.youpin_network.retry.HttpsEntity r7, com.xiaomi.youpin.youpin_network.retry.RNHttpEntity r8) {
        /*
            r5 = this;
            r0 = 0
            r1 = 1
            if (r6 != 0) goto L_0x0009
            int r2 = r5.i
            if (r2 == r1) goto L_0x0009
            return r0
        L_0x0009:
            java.lang.Object r2 = f23864a
            monitor-enter(r2)
            if (r6 == 0) goto L_0x0017
            int r3 = r5.i     // Catch:{ all -> 0x0015 }
            if (r3 != 0) goto L_0x0017
            r5.i = r1     // Catch:{ all -> 0x0015 }
            goto L_0x0023
        L_0x0015:
            r6 = move-exception
            goto L_0x0051
        L_0x0017:
            int r3 = r5.i     // Catch:{ all -> 0x0015 }
            r4 = 2
            if (r3 == r4) goto L_0x004f
            int r3 = r5.i     // Catch:{ all -> 0x0015 }
            r4 = 3
            if (r3 != r4) goto L_0x0022
            goto L_0x004f
        L_0x0022:
            r0 = 1
        L_0x0023:
            monitor-exit(r2)     // Catch:{ all -> 0x0015 }
            if (r7 == 0) goto L_0x0033
            if (r6 == 0) goto L_0x002e
            java.util.concurrent.CopyOnWriteArrayList<com.xiaomi.youpin.youpin_network.retry.HttpsEntity> r2 = r5.f
            r2.add(r7)
            goto L_0x0033
        L_0x002e:
            java.util.concurrent.CopyOnWriteArrayList<com.xiaomi.youpin.youpin_network.retry.HttpsEntity> r2 = r5.e
            r2.add(r7)
        L_0x0033:
            if (r8 == 0) goto L_0x0042
            if (r6 == 0) goto L_0x003d
            java.util.concurrent.CopyOnWriteArrayList<com.xiaomi.youpin.youpin_network.retry.RNHttpEntity> r6 = r5.h
            r6.add(r8)
            goto L_0x0042
        L_0x003d:
            java.util.concurrent.CopyOnWriteArrayList<com.xiaomi.youpin.youpin_network.retry.RNHttpEntity> r6 = r5.g
            r6.add(r8)
        L_0x0042:
            if (r0 != 0) goto L_0x004e
            com.xiaomi.youpin.youpin_network.NetWorkDependency r6 = r5.d
            com.xiaomi.youpin.youpin_network.retry.YouPinAuthHttpsManager$1 r7 = new com.xiaomi.youpin.youpin_network.retry.YouPinAuthHttpsManager$1
            r7.<init>()
            r6.a(r7)
        L_0x004e:
            return r1
        L_0x004f:
            monitor-exit(r2)     // Catch:{ all -> 0x0015 }
            return r0
        L_0x0051:
            monitor-exit(r2)     // Catch:{ all -> 0x0015 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_network.retry.YouPinAuthHttpsManager.a(boolean, com.xiaomi.youpin.youpin_network.retry.HttpsEntity, com.xiaomi.youpin.youpin_network.retry.RNHttpEntity):boolean");
    }

    /* access modifiers changed from: private */
    public synchronized void a(boolean z) {
        Iterator<HttpsEntity> it = this.f.iterator();
        while (it.hasNext()) {
            HttpsEntity next = it.next();
            next.a().a(false);
            if (z) {
                a(next);
            } else if (next.h()) {
                for (PipeRequest pipeRequest : next.g()) {
                    RequestAsyncCallback<T, NetError> requestAsyncCallback = pipeRequest.callback;
                    if (requestAsyncCallback != null) {
                        requestAsyncCallback.b(new NetError(-1, "refresh serviceToken fail"));
                    }
                }
            } else {
                RequestAsyncCallback d2 = next.d();
                if (d2 != null) {
                    d2.b(new NetError(-1, "refresh serviceToken fail"));
                }
            }
        }
        Iterator<HttpsEntity> it2 = this.e.iterator();
        while (it2.hasNext()) {
            HttpsEntity next2 = it2.next();
            next2.a().a(false);
            a(next2);
        }
        Iterator<RNHttpEntity> it3 = this.h.iterator();
        while (it3.hasNext()) {
            it3.next().d.a();
        }
        Iterator<RNHttpEntity> it4 = this.g.iterator();
        while (it4.hasNext()) {
            it4.next().d.a();
        }
        this.f.clear();
        this.e.clear();
        this.h.clear();
        this.g.clear();
        this.i = 0;
    }

    /* access modifiers changed from: private */
    public synchronized void c() {
        if (this.d.d()) {
            this.d.a();
        }
    }

    private void a(HttpsEntity httpsEntity) {
        switch (httpsEntity.a().e()) {
            case 1:
                if (httpsEntity.h()) {
                    YouPinHttpsApi.a().a(httpsEntity.a(), httpsEntity.b(), httpsEntity.g());
                    return;
                } else {
                    YouPinHttpsApi.a().a(httpsEntity.i(), httpsEntity.a(), httpsEntity.b(), httpsEntity.c(), httpsEntity.d());
                    return;
                }
            case 2:
                YouPinHttpsApi.a().a(httpsEntity.i(), httpsEntity.a(), httpsEntity.e(), httpsEntity.c(), httpsEntity.d());
                return;
            case 3:
                YouPinHttpsApi.a().a(httpsEntity.a(), httpsEntity.f(), (RequestAsyncCallback<String, NetError>) httpsEntity.d());
                return;
            default:
                return;
        }
    }

    private void a(RNHttpEntity rNHttpEntity) {
        YouPinHttpsApi.a().a(rNHttpEntity.f23863a, rNHttpEntity.b, rNHttpEntity.c, rNHttpEntity.d);
    }
}
