package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.http.RequestHandle;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteAsyncApiHelper {
    /* access modifiers changed from: private */
    public static final String b = "com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper";
    private static volatile RemoteAsyncApiHelper f;

    /* renamed from: a  reason: collision with root package name */
    public RemoteAsyncApiPushListener f16381a = new RemoteAsyncApiPushListener();
    /* access modifiers changed from: private */
    public Map<Long, RemoteAsyncCacheData> c = new HashMap();
    /* access modifiers changed from: private */
    public final Object d = new Object();
    /* access modifiers changed from: private */
    public RetryManager e = new RetryManager();

    public static class RemoteAsyncCacheData {

        /* renamed from: a  reason: collision with root package name */
        RemoteAsyncResponse f16387a;
        RemoteAsyncResult b;
        String c;
        int d;
        Object e;
        WeakReference<AsyncResponseCallback> f;
    }

    public static class RemoteAsyncResult {

        /* renamed from: a  reason: collision with root package name */
        JSONObject f16390a;
    }

    /* access modifiers changed from: private */
    public static int c(long j) {
        return (int) (j ^ (j >>> 32));
    }

    private RemoteAsyncApiHelper() {
    }

    public static RemoteAsyncApiHelper a() {
        if (f == null) {
            synchronized (RemoteAsyncApiHelper.class) {
                if (f == null) {
                    f = new RemoteAsyncApiHelper();
                }
            }
        }
        return f;
    }

    static class RemoteAsyncResponse {

        /* renamed from: a  reason: collision with root package name */
        long f16389a;
        int b;
        int c;

        RemoteAsyncResponse() {
        }
    }

    private RequestHandle a(Context context, String[] strArr, final int i, Object obj, final AsyncResponseCallback<JSONObject> asyncResponseCallback, final Callback<JSONObject> callback) {
        AnonymousClass1 r6 = new RemoteAsyncResponseCallback() {
            /* renamed from: a */
            public void onSuccess(final RemoteAsyncResponse remoteAsyncResponse) {
                if (this.f == null || this.f.f16388a) {
                    Log.d(RemoteAsyncApiHelper.b, "callRemoteAsync onSuccess request canceled");
                } else if (remoteAsyncResponse == null) {
                    onFailure(new Error(0, "result is null"));
                } else {
                    RemoteAsyncCacheData remoteAsyncCacheData = new RemoteAsyncCacheData();
                    remoteAsyncCacheData.f16387a = remoteAsyncResponse;
                    remoteAsyncCacheData.d = i;
                    remoteAsyncCacheData.f = new WeakReference<>(asyncResponseCallback);
                    synchronized (RemoteAsyncApiHelper.this.d) {
                        this.e = remoteAsyncResponse.f16389a;
                        RemoteAsyncApiHelper.this.c.put(Long.valueOf(remoteAsyncResponse.f16389a), remoteAsyncCacheData);
                        RemoteAsyncApiHelper.this.e.a(remoteAsyncCacheData, (RetryManager.RetryCallback) new RetryManager.RetryCallback() {
                            /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
                                return;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
                                return;
                             */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void a() {
                                /*
                                    r4 = this;
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncRequestHandleWraper r0 = r0.f
                                    if (r0 == 0) goto L_0x0053
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncRequestHandleWraper r0 = r0.f
                                    boolean r0 = r0.f16388a
                                    if (r0 == 0) goto L_0x000f
                                    goto L_0x0053
                                L_0x000f:
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this
                                    java.lang.Object r0 = r0.d
                                    monitor-enter(r0)
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r1 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this     // Catch:{ all -> 0x0050 }
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r1 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x0050 }
                                    java.util.Map r1 = r1.c     // Catch:{ all -> 0x0050 }
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncResponse r2 = r6     // Catch:{ all -> 0x0050 }
                                    long r2 = r2.f16389a     // Catch:{ all -> 0x0050 }
                                    java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0050 }
                                    java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x0050 }
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncCacheData r1 = (com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncCacheData) r1     // Catch:{ all -> 0x0050 }
                                    if (r1 == 0) goto L_0x004e
                                    java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r2 = r1.f     // Catch:{ all -> 0x0050 }
                                    if (r2 != 0) goto L_0x0035
                                    goto L_0x004e
                                L_0x0035:
                                    java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r2 = r1.f     // Catch:{ all -> 0x0050 }
                                    if (r2 != 0) goto L_0x003b
                                    r2 = 0
                                    goto L_0x0043
                                L_0x003b:
                                    java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r2 = r1.f     // Catch:{ all -> 0x0050 }
                                    java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0050 }
                                    com.xiaomi.smarthome.framework.api.AsyncResponseCallback r2 = (com.xiaomi.smarthome.framework.api.AsyncResponseCallback) r2     // Catch:{ all -> 0x0050 }
                                L_0x0043:
                                    if (r2 == 0) goto L_0x004c
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncResult r1 = r1.b     // Catch:{ all -> 0x0050 }
                                    org.json.JSONObject r1 = r1.f16390a     // Catch:{ all -> 0x0050 }
                                    r2.a(r1)     // Catch:{ all -> 0x0050 }
                                L_0x004c:
                                    monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                                    return
                                L_0x004e:
                                    monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                                    return
                                L_0x0050:
                                    r1 = move-exception
                                    monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                                    throw r1
                                L_0x0053:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.AnonymousClass1.a():void");
                            }

                            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
                                if (r1.f != null) goto L_0x0039;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
                                r0 = null;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
                                r0 = (com.xiaomi.smarthome.framework.api.AsyncResponseCallback) r1.f.get();
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
                                if (r0 == null) goto L_?;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
                                r0.a(r5, r6);
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
                                return;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
                                return;
                             */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void a(int r5, java.lang.Object r6) {
                                /*
                                    r4 = this;
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncRequestHandleWraper r0 = r0.f
                                    if (r0 == 0) goto L_0x004a
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncRequestHandleWraper r0 = r0.f
                                    boolean r0 = r0.f16388a
                                    if (r0 == 0) goto L_0x000f
                                    goto L_0x004a
                                L_0x000f:
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this
                                    java.lang.Object r0 = r0.d
                                    monitor-enter(r0)
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$1 r1 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.this     // Catch:{ all -> 0x0047 }
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r1 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x0047 }
                                    java.util.Map r1 = r1.c     // Catch:{ all -> 0x0047 }
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncResponse r2 = r6     // Catch:{ all -> 0x0047 }
                                    long r2 = r2.f16389a     // Catch:{ all -> 0x0047 }
                                    java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0047 }
                                    java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x0047 }
                                    com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncCacheData r1 = (com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncCacheData) r1     // Catch:{ all -> 0x0047 }
                                    if (r1 != 0) goto L_0x0032
                                    monitor-exit(r0)     // Catch:{ all -> 0x0047 }
                                    return
                                L_0x0032:
                                    monitor-exit(r0)     // Catch:{ all -> 0x0047 }
                                    java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r0 = r1.f
                                    if (r0 != 0) goto L_0x0039
                                    r0 = 0
                                    goto L_0x0041
                                L_0x0039:
                                    java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r0 = r1.f
                                    java.lang.Object r0 = r0.get()
                                    com.xiaomi.smarthome.framework.api.AsyncResponseCallback r0 = (com.xiaomi.smarthome.framework.api.AsyncResponseCallback) r0
                                L_0x0041:
                                    if (r0 == 0) goto L_0x0046
                                    r0.a(r5, r6)
                                L_0x0046:
                                    return
                                L_0x0047:
                                    r5 = move-exception
                                    monitor-exit(r0)     // Catch:{ all -> 0x0047 }
                                    throw r5
                                L_0x004a:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass1.AnonymousClass1.a(int, java.lang.Object):void");
                            }
                        });
                    }
                    if (callback != null) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("sid", remoteAsyncResponse.f16389a);
                            jSONObject.put(Constants.Name.INTERVAL, remoteAsyncResponse.b);
                            jSONObject.put("max_retry", remoteAsyncResponse.c);
                            callback.onSuccess(jSONObject);
                        } catch (JSONException unused) {
                        }
                    }
                }
            }

            public void onFailure(Error error) {
                String c2 = RemoteAsyncApiHelper.b;
                Log.d(c2, "callRemoteAsync failed " + error);
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(error.a(), error.b() != null ? error.b() : "");
                }
            }
        };
        RemoteAsyncRequestHandleWraper remoteAsyncRequestHandleWraper = new RemoteAsyncRequestHandleWraper(RemoteAsyncApi.a().a(context, strArr, i, obj, r6));
        remoteAsyncRequestHandleWraper.c = r6;
        remoteAsyncRequestHandleWraper.c.f = remoteAsyncRequestHandleWraper;
        return remoteAsyncRequestHandleWraper;
    }

    public RequestHandle a(Context context, String[] strArr, int i, Object obj, final Callback<JSONObject> callback, Callback<JSONObject> callback2) {
        if (callback == null) {
            return null;
        }
        return a(context, strArr, i, obj, (AsyncResponseCallback<JSONObject>) new AsyncResponseCallback<JSONObject>() {
            public void a(JSONObject jSONObject) {
                callback.onSuccess(jSONObject);
            }

            public void a(int i) {
                callback.onFailure(i, "");
            }

            public void a(int i, Object obj) {
                callback.onFailure(i, obj != null ? obj.toString() : "");
            }
        }, callback2);
    }

    public RequestHandle a(Context context, String[] strArr, int i, Object obj, Callback<JSONObject> callback) {
        return a(context, strArr, i, obj, callback, (Callback<JSONObject>) null);
    }

    public void b() {
        synchronized (this.d) {
            this.c.clear();
        }
        this.e.a();
        f = null;
    }

    public abstract class RemoteAsyncResponseCallback extends AsyncCallback<RemoteAsyncResponse, Error> {
        long e = -1;
        RemoteAsyncRequestHandleWraper f = null;

        public RemoteAsyncResponseCallback() {
        }
    }

    public class RemoteAsyncRequestHandleWraper extends RequestHandle {

        /* renamed from: a  reason: collision with root package name */
        volatile boolean f16388a = false;
        AsyncHandle b;
        RemoteAsyncResponseCallback c;

        public RemoteAsyncRequestHandleWraper(AsyncHandle asyncHandle) {
            super((Call) null);
            this.b = asyncHandle;
            this.c = null;
        }

        public void a() {
            super.a();
            this.f16388a = true;
            this.c.f = null;
            if (this.b != null) {
                this.b.cancel();
                this.b = null;
                if (this.c != null && this.c.e >= 0) {
                    synchronized (RemoteAsyncApiHelper.this.d) {
                        RemoteAsyncCacheData remoteAsyncCacheData = (RemoteAsyncCacheData) RemoteAsyncApiHelper.this.c.remove(Long.valueOf(this.c.e));
                        if (!(remoteAsyncCacheData == null || remoteAsyncCacheData.f16387a == null)) {
                            RemoteAsyncApiHelper.this.e.a(remoteAsyncCacheData.f16387a.f16389a);
                        }
                    }
                    this.c = null;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(final long j) {
        synchronized (this.d) {
            if (this.c.get(Long.valueOf(j)) != null) {
                RemoteAsyncApi.a().a(SHApplication.getAppContext(), j, 0, new AsyncCallback<RemoteAsyncResult, Error>() {
                    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
                        return;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
                        return;
                     */
                    /* renamed from: a */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onSuccess(com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncResult r6) {
                        /*
                            r5 = this;
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ Exception -> 0x0041 }
                            java.lang.Object r0 = r0.d     // Catch:{ Exception -> 0x0041 }
                            monitor-enter(r0)     // Catch:{ Exception -> 0x0041 }
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r1 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x003e }
                            java.util.Map r1 = r1.c     // Catch:{ all -> 0x003e }
                            long r2 = r8     // Catch:{ all -> 0x003e }
                            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x003e }
                            java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x003e }
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncCacheData r1 = (com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncCacheData) r1     // Catch:{ all -> 0x003e }
                            if (r1 == 0) goto L_0x003c
                            java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r2 = r1.f     // Catch:{ all -> 0x003e }
                            if (r2 != 0) goto L_0x0020
                            goto L_0x003c
                        L_0x0020:
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r2 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x003e }
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RetryManager r2 = r2.e     // Catch:{ all -> 0x003e }
                            long r3 = r8     // Catch:{ all -> 0x003e }
                            r2.a((long) r3)     // Catch:{ all -> 0x003e }
                            java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r1 = r1.f     // Catch:{ all -> 0x003e }
                            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x003e }
                            com.xiaomi.smarthome.framework.api.AsyncResponseCallback r1 = (com.xiaomi.smarthome.framework.api.AsyncResponseCallback) r1     // Catch:{ all -> 0x003e }
                            if (r1 == 0) goto L_0x003a
                            org.json.JSONObject r6 = r6.f16390a     // Catch:{ all -> 0x003e }
                            r1.a(r6)     // Catch:{ all -> 0x003e }
                        L_0x003a:
                            monitor-exit(r0)     // Catch:{ all -> 0x003e }
                            goto L_0x0041
                        L_0x003c:
                            monitor-exit(r0)     // Catch:{ all -> 0x003e }
                            return
                        L_0x003e:
                            r6 = move-exception
                            monitor-exit(r0)     // Catch:{ all -> 0x003e }
                            throw r6     // Catch:{ Exception -> 0x0041 }
                        L_0x0041:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass3.onSuccess(com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncResult):void");
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
                        return;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onFailure(com.xiaomi.smarthome.frame.Error r5) {
                        /*
                            r4 = this;
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this
                            java.lang.Object r0 = r0.d
                            monitor-enter(r0)
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r1 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x0042 }
                            java.util.Map r1 = r1.c     // Catch:{ all -> 0x0042 }
                            long r2 = r8     // Catch:{ all -> 0x0042 }
                            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0042 }
                            java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x0042 }
                            com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncCacheData r1 = (com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncCacheData) r1     // Catch:{ all -> 0x0042 }
                            if (r1 == 0) goto L_0x0040
                            java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r2 = r1.f     // Catch:{ all -> 0x0042 }
                            if (r2 != 0) goto L_0x0020
                            goto L_0x0040
                        L_0x0020:
                            java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r1 = r1.f     // Catch:{ all -> 0x0042 }
                            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0042 }
                            com.xiaomi.smarthome.framework.api.AsyncResponseCallback r1 = (com.xiaomi.smarthome.framework.api.AsyncResponseCallback) r1     // Catch:{ all -> 0x0042 }
                            if (r1 == 0) goto L_0x003e
                            int r2 = r5.a()     // Catch:{ all -> 0x0042 }
                            java.lang.String r3 = r5.b()     // Catch:{ all -> 0x0042 }
                            if (r3 == 0) goto L_0x0039
                            java.lang.String r5 = r5.b()     // Catch:{ all -> 0x0042 }
                            goto L_0x003b
                        L_0x0039:
                            java.lang.String r5 = ""
                        L_0x003b:
                            r1.a(r2, r5)     // Catch:{ all -> 0x0042 }
                        L_0x003e:
                            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
                            return
                        L_0x0040:
                            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
                            return
                        L_0x0042:
                            r5 = move-exception
                            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
                            throw r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.AnonymousClass3.onFailure(com.xiaomi.smarthome.frame.Error):void");
                    }
                });
            }
        }
    }

    public static class RetryManager {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Handler f16391a;
        private Map<Long, AsyncHandle> b = new HashMap();
        private final Object c = new Object();

        public interface RetryCallback {
            void a();

            void a(int i, Object obj);
        }

        private class RemoteAsyncHandlerThread extends MessageHandlerThread {
            public RemoteAsyncHandlerThread(String str) {
                super(str);
            }
        }

        public RetryManager() {
            RemoteAsyncHandlerThread remoteAsyncHandlerThread = new RemoteAsyncHandlerThread("retry_thread");
            remoteAsyncHandlerThread.start();
            this.f16391a = new Handler(remoteAsyncHandlerThread.getLooper()) {
                public void handleMessage(Message message) {
                    if (message.obj instanceof RetryInfo) {
                        RetryManager.this.a((RetryInfo) message.obj);
                    }
                }
            };
        }

        public void a() {
            synchronized (this.c) {
                this.b.clear();
            }
            this.f16391a.removeCallbacksAndMessages((Object) null);
            this.f16391a.getLooper().quit();
        }

        public void a(RemoteAsyncCacheData remoteAsyncCacheData, RetryCallback retryCallback) {
            if (remoteAsyncCacheData != null && remoteAsyncCacheData.f16387a != null) {
                b(remoteAsyncCacheData, retryCallback);
            } else if (retryCallback != null) {
                retryCallback.a(0, "data is null or data.response is null");
            }
        }

        public void a(long j) {
            this.f16391a.removeMessages(RemoteAsyncApiHelper.c(j));
            synchronized (this.c) {
                AsyncHandle remove = this.b.remove(Long.valueOf(j));
                if (remove != null) {
                    remove.cancel();
                }
            }
        }

        /* access modifiers changed from: private */
        public void a(final RetryInfo retryInfo) {
            if (retryInfo.d > 0) {
                retryInfo.d--;
                AsyncHandle a2 = RemoteAsyncApi.a().a(SHApplication.getAppContext(), retryInfo.b, retryInfo.f16395a.f16387a.c - retryInfo.d, new AsyncCallback<RemoteAsyncResult, Error>() {
                    /* renamed from: a */
                    public void onSuccess(RemoteAsyncResult remoteAsyncResult) {
                        if (remoteAsyncResult != null && retryInfo.f16395a != null) {
                            retryInfo.f16395a.b = remoteAsyncResult;
                            if (retryInfo.c != null) {
                                retryInfo.c.a();
                            }
                        } else if (retryInfo.c != null) {
                            retryInfo.c.a(0, "result is null or info.data is null");
                        }
                    }

                    public void onFailure(Error error) {
                        String c = RemoteAsyncApiHelper.b;
                        Log.d(c, "getApiResult onFailure shError=" + error.a());
                        if (error.a() == ErrorCode.ERROR_RETRY_ERROR.getCode()) {
                            Message obtainMessage = RetryManager.this.f16391a.obtainMessage();
                            obtainMessage.obj = retryInfo;
                            obtainMessage.what = RemoteAsyncApiHelper.c(retryInfo.b);
                            RetryManager.this.f16391a.sendMessageDelayed(obtainMessage, (long) (retryInfo.f16395a.f16387a.b * 1000));
                        } else if (retryInfo.c != null) {
                            retryInfo.c.a(error == null ? 0 : error.a(), error == null ? "Unknown error" : error.b());
                        }
                    }
                });
                synchronized (this.c) {
                    this.b.put(Long.valueOf(retryInfo.b), a2);
                }
            } else if (retryInfo.c == null) {
            } else {
                if (retryInfo.f16395a == null || retryInfo.f16395a.f16387a == null) {
                    retryInfo.c.a(0, "leftRetry is zero");
                    return;
                }
                retryInfo.c.a(0, "not retrieved the result in " + (retryInfo.f16395a.f16387a.c * retryInfo.f16395a.f16387a.b) + " seconds. leftRetry is zero");
            }
        }

        private class RetryInfo {

            /* renamed from: a  reason: collision with root package name */
            RemoteAsyncCacheData f16395a;
            long b;
            RetryCallback c;
            int d;

            private RetryInfo() {
            }
        }

        private void b(RemoteAsyncCacheData remoteAsyncCacheData, RetryCallback retryCallback) {
            if (remoteAsyncCacheData.f16387a.c > 0 && remoteAsyncCacheData.f16387a.f16389a >= 0) {
                RetryInfo retryInfo = new RetryInfo();
                retryInfo.f16395a = remoteAsyncCacheData;
                retryInfo.d = remoteAsyncCacheData.f16387a.c;
                retryInfo.c = retryCallback;
                retryInfo.b = remoteAsyncCacheData.f16387a.f16389a;
                Message obtainMessage = this.f16391a.obtainMessage();
                obtainMessage.obj = retryInfo;
                obtainMessage.what = RemoteAsyncApiHelper.c(retryInfo.b);
                this.f16391a.sendMessageDelayed(obtainMessage, (long) (remoteAsyncCacheData.f16387a.b * 1000));
            } else if (retryCallback != null) {
                retryCallback.a(0, "invalid sid or maxRetry");
            }
        }
    }

    private class RemoteAsyncApiPushListener extends PushListener {
        public boolean b(String str, String str2) {
            return false;
        }

        private RemoteAsyncApiPushListener() {
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:23|24|25|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0095, code lost:
            return true;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x008d */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(java.lang.String r7, java.lang.String r8) {
            /*
                r6 = this;
                java.lang.String r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.b
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "RemoteAsyncApiPushListener onReceiveMessage:messageId="
                r1.append(r2)
                r1.append(r7)
                java.lang.String r7 = ",data="
                r1.append(r7)
                r1.append(r8)
                java.lang.String r7 = r1.toString()
                android.util.Log.d(r0, r7)
                boolean r7 = android.text.TextUtils.isEmpty(r8)
                r0 = 0
                if (r7 == 0) goto L_0x0028
                return r0
            L_0x0028:
                r7 = 1
                org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0099 }
                r1.<init>(r8)     // Catch:{ Exception -> 0x0099 }
                java.lang.String r8 = "sid"
                long r2 = r1.optLong(r8)     // Catch:{ Exception -> 0x0099 }
                r4 = 0
                int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r8 >= 0) goto L_0x003b
                return r0
            L_0x003b:
                com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r8 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ Exception -> 0x0099 }
                java.lang.Object r8 = r8.d     // Catch:{ Exception -> 0x0099 }
                monitor-enter(r8)     // Catch:{ Exception -> 0x0099 }
                com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x0096 }
                java.util.Map r0 = r0.c     // Catch:{ all -> 0x0096 }
                java.lang.Long r4 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0096 }
                java.lang.Object r0 = r0.remove(r4)     // Catch:{ all -> 0x0096 }
                com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RemoteAsyncCacheData r0 = (com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncCacheData) r0     // Catch:{ all -> 0x0096 }
                if (r0 == 0) goto L_0x0094
                java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r4 = r0.f     // Catch:{ all -> 0x0096 }
                if (r4 != 0) goto L_0x0059
                goto L_0x0094
            L_0x0059:
                com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r4 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x0096 }
                com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper$RetryManager r4 = r4.e     // Catch:{ all -> 0x0096 }
                r4.a((long) r2)     // Catch:{ all -> 0x0096 }
                java.lang.ref.WeakReference<com.xiaomi.smarthome.framework.api.AsyncResponseCallback> r0 = r0.f     // Catch:{ all -> 0x0096 }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0096 }
                com.xiaomi.smarthome.framework.api.AsyncResponseCallback r0 = (com.xiaomi.smarthome.framework.api.AsyncResponseCallback) r0     // Catch:{ all -> 0x0096 }
                if (r0 == 0) goto L_0x0092
                java.lang.String r4 = "result"
                org.json.JSONObject r4 = r1.optJSONObject(r4)     // Catch:{ all -> 0x0096 }
                if (r4 == 0) goto L_0x0078
                r0.a(r4)     // Catch:{ all -> 0x0096 }
                goto L_0x0092
            L_0x0078:
                java.lang.String r4 = "result"
                java.lang.String r1 = r1.optString(r4)     // Catch:{ all -> 0x0096 }
                boolean r4 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0096 }
                if (r4 != 0) goto L_0x0092
                org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x008d }
                r4.<init>(r1)     // Catch:{ Exception -> 0x008d }
                r0.a(r4)     // Catch:{ Exception -> 0x008d }
                goto L_0x0092
            L_0x008d:
                com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper r0 = com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.this     // Catch:{ all -> 0x0096 }
                r0.b((long) r2)     // Catch:{ all -> 0x0096 }
            L_0x0092:
                monitor-exit(r8)     // Catch:{ all -> 0x0096 }
                goto L_0x0099
            L_0x0094:
                monitor-exit(r8)     // Catch:{ all -> 0x0096 }
                return r7
            L_0x0096:
                r0 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x0096 }
                throw r0     // Catch:{ Exception -> 0x0099 }
            L_0x0099:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper.RemoteAsyncApiPushListener.a(java.lang.String, java.lang.String):boolean");
        }
    }
}
