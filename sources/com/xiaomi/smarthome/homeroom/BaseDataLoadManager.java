package com.xiaomi.smarthome.homeroom;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataLoadManager<T> {

    /* renamed from: a  reason: collision with root package name */
    private List<AsyncCallback<T, Error>> f17921a = new ArrayList();
    private boolean b = false;

    public interface LoadStartCallback {
        void a();
    }

    /* access modifiers changed from: protected */
    public abstract Intent a(boolean z);

    public boolean a() {
        return this.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r4.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r4 == null) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.frame.AsyncCallback<T, com.xiaomi.smarthome.frame.Error> r3, com.xiaomi.smarthome.homeroom.BaseDataLoadManager.LoadStartCallback r4) {
        /*
            r2 = this;
            java.lang.Class<com.xiaomi.smarthome.homeroom.BaseDataLoadManager> r0 = com.xiaomi.smarthome.homeroom.BaseDataLoadManager.class
            monitor-enter(r0)
            java.util.List<com.xiaomi.smarthome.frame.AsyncCallback<T, com.xiaomi.smarthome.frame.Error>> r1 = r2.f17921a     // Catch:{ all -> 0x0018 }
            r1.add(r3)     // Catch:{ all -> 0x0018 }
            boolean r3 = r2.b     // Catch:{ all -> 0x0018 }
            if (r3 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x000e:
            r3 = 1
            r2.b = r3     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            if (r4 == 0) goto L_0x0017
            r4.a()
        L_0x0017:
            return
        L_0x0018:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.BaseDataLoadManager.a(com.xiaomi.smarthome.frame.AsyncCallback, com.xiaomi.smarthome.homeroom.BaseDataLoadManager$LoadStartCallback):void");
    }

    private void a(Intent intent) {
        if (intent != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
        int i;
        ArrayList arrayList = new ArrayList();
        synchronized (BaseDataLoadManager.class) {
            this.b = false;
            arrayList.addAll(this.f17921a);
            this.f17921a.clear();
        }
        for (i = 0; i < arrayList.size(); i++) {
            AsyncCallback asyncCallback = (AsyncCallback) arrayList.get(i);
            if (asyncCallback != null) {
                asyncCallback.onSuccess(t);
            }
        }
        a(a(true));
    }

    /* access modifiers changed from: protected */
    public void a(Error error) {
        ArrayList arrayList = new ArrayList();
        synchronized (BaseDataLoadManager.class) {
            this.b = false;
            arrayList.addAll(this.f17921a);
            this.f17921a.clear();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            AsyncCallback asyncCallback = (AsyncCallback) arrayList.get(i);
            if (asyncCallback != null) {
                asyncCallback.onFailure(error);
            }
        }
        a(a(false));
    }
}
