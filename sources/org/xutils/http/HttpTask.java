package org.xutils.http;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback;
import org.xutils.common.task.AbsTask;
import org.xutils.common.task.Priority;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.request.UriRequest;
import org.xutils.http.request.UriRequestFactory;
import org.xutils.x;

public class HttpTask<ResultType> extends AbsTask<ResultType> implements ProgressHandler {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f10765a = (!HttpTask.class.desiredAssertionStatus());
    private static final int q = 3;
    /* access modifiers changed from: private */
    public static final AtomicInteger r = new AtomicInteger(0);
    private static final HashMap<String, WeakReference<HttpTask<?>>> s = new HashMap<>(1);
    private static final PriorityExecutor t = new PriorityExecutor(5, true);
    private static final PriorityExecutor u = new PriorityExecutor(5, true);
    private static final int v = 1;
    private static final int w = 2;
    private static final int x = 3;
    /* access modifiers changed from: private */
    public RequestParams b;
    /* access modifiers changed from: private */
    public UriRequest c;
    private HttpTask<ResultType>.RequestWorker d;
    private final Executor e;
    private volatile boolean f = false;
    private final Callback.CommonCallback<ResultType> g;
    private Object h = null;
    private volatile Boolean i = null;
    private final Object j = new Object();
    private Callback.CacheCallback<ResultType> k;
    private Callback.PrepareCallback l;
    private Callback.ProgressCallback m;
    /* access modifiers changed from: private */
    public RequestInterceptListener n;
    private RequestTracker o;
    /* access modifiers changed from: private */
    public Type p;
    private long y;
    private long z = 300;

    public HttpTask(RequestParams requestParams, Callback.Cancelable cancelable, Callback.CommonCallback<ResultType> commonCallback) {
        super(cancelable);
        if (!f10765a && requestParams == null) {
            throw new AssertionError();
        } else if (f10765a || commonCallback != null) {
            this.b = requestParams;
            this.g = commonCallback;
            if (commonCallback instanceof Callback.CacheCallback) {
                this.k = (Callback.CacheCallback) commonCallback;
            }
            if (commonCallback instanceof Callback.PrepareCallback) {
                this.l = (Callback.PrepareCallback) commonCallback;
            }
            if (commonCallback instanceof Callback.ProgressCallback) {
                this.m = (Callback.ProgressCallback) commonCallback;
            }
            if (commonCallback instanceof RequestInterceptListener) {
                this.n = (RequestInterceptListener) commonCallback;
            }
            RequestTracker H = requestParams.H();
            if (H == null) {
                if (commonCallback instanceof RequestTracker) {
                    H = (RequestTracker) commonCallback;
                } else {
                    H = UriRequestFactory.a();
                }
            }
            if (H != null) {
                this.o = new RequestTrackerWrapper(H);
            }
            if (requestParams.y() != null) {
                this.e = requestParams.y();
            } else if (this.k != null) {
                this.e = u;
            } else {
                this.e = t;
            }
        } else {
            throw new AssertionError();
        }
    }

    private void o() {
        Class<?> cls = this.g.getClass();
        if (this.g instanceof Callback.TypedCallback) {
            this.p = ((Callback.TypedCallback) this.g).f();
        } else if (this.g instanceof Callback.PrepareCallback) {
            this.p = ParameterizedTypeUtil.a((Type) cls, (Class<?>) Callback.PrepareCallback.class, 0);
        } else {
            this.p = ParameterizedTypeUtil.a((Type) cls, (Class<?>) Callback.CommonCallback.class, 0);
        }
    }

    /* access modifiers changed from: private */
    public UriRequest p() throws Throwable {
        this.b.n();
        UriRequest a2 = UriRequestFactory.a(this.b, this.p);
        a2.a(this.g.getClass().getClassLoader());
        a2.a((ProgressHandler) this);
        this.z = (long) this.b.E();
        b(1, a2);
        return a2;
    }

    private void q() {
        if (File.class == this.p) {
            synchronized (s) {
                String B = this.b.B();
                if (!TextUtils.isEmpty(B)) {
                    WeakReference weakReference = s.get(B);
                    if (weakReference != null) {
                        HttpTask httpTask = (HttpTask) weakReference.get();
                        if (httpTask != null) {
                            httpTask.a();
                            httpTask.s();
                        }
                        s.remove(B);
                    }
                    s.put(B, new WeakReference(this));
                }
                if (s.size() > 3) {
                    Iterator<Map.Entry<String, WeakReference<HttpTask<?>>>> it = s.entrySet().iterator();
                    while (it.hasNext()) {
                        WeakReference weakReference2 = (WeakReference) it.next().getValue();
                        if (weakReference2 == null || weakReference2.get() == null) {
                            it.remove();
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x00b9 */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:134:? A[ExcHandler: HttpRedirectException (unused org.xutils.ex.HttpRedirectException), SYNTHETIC, Splitter:B:72:0x010a] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01e6 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0103 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ResultType c() throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r9.b()
            if (r0 != 0) goto L_0x021d
            r9.o()
            org.xutils.http.request.UriRequest r0 = r9.p()
            r9.c = r0
            r9.q()
            org.xutils.http.RequestParams r0 = r9.b
            org.xutils.http.app.HttpRetryHandler r0 = r0.F()
            if (r0 != 0) goto L_0x001f
            org.xutils.http.app.HttpRetryHandler r0 = new org.xutils.http.app.HttpRetryHandler
            r0.<init>()
        L_0x001f:
            org.xutils.http.RequestParams r1 = r9.b
            int r1 = r1.C()
            r0.a(r1)
            boolean r1 = r9.b()
            if (r1 != 0) goto L_0x0215
            org.xutils.common.Callback$CacheCallback<ResultType> r1 = r9.k
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x00e1
            org.xutils.http.RequestParams r1 = r9.b
            org.xutils.http.HttpMethod r1 = r1.b()
            boolean r1 = org.xutils.http.HttpMethod.permitsCache(r1)
            if (r1 == 0) goto L_0x00e1
            r9.r()     // Catch:{ Throwable -> 0x0067 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0067 }
            r1.<init>()     // Catch:{ Throwable -> 0x0067 }
            java.lang.String r5 = "load cache: "
            r1.append(r5)     // Catch:{ Throwable -> 0x0067 }
            org.xutils.http.request.UriRequest r5 = r9.c     // Catch:{ Throwable -> 0x0067 }
            java.lang.String r5 = r5.aj_()     // Catch:{ Throwable -> 0x0067 }
            r1.append(r5)     // Catch:{ Throwable -> 0x0067 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0067 }
            org.xutils.common.util.LogUtil.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x0067 }
            org.xutils.http.request.UriRequest r1 = r9.c     // Catch:{ Throwable -> 0x0067 }
            java.lang.Object r1 = r1.e()     // Catch:{ Throwable -> 0x0067 }
            r9.h = r1     // Catch:{ Throwable -> 0x0067 }
            goto L_0x006d
        L_0x0067:
            r1 = move-exception
            java.lang.String r5 = "load disk cache error"
            org.xutils.common.util.LogUtil.e(r5, r1)
        L_0x006d:
            boolean r1 = r9.b()
            if (r1 != 0) goto L_0x00d6
            java.lang.Object r1 = r9.h
            if (r1 == 0) goto L_0x00e1
            org.xutils.common.Callback$PrepareCallback r1 = r9.l
            if (r1 == 0) goto L_0x0098
            org.xutils.common.Callback$PrepareCallback r1 = r9.l     // Catch:{ Throwable -> 0x0089 }
            java.lang.Object r5 = r9.h     // Catch:{ Throwable -> 0x0089 }
            java.lang.Object r1 = r1.c(r5)     // Catch:{ Throwable -> 0x0089 }
            r9.r()
            goto L_0x009a
        L_0x0087:
            r0 = move-exception
            goto L_0x0094
        L_0x0089:
            r1 = move-exception
            java.lang.String r5 = "prepare disk cache error"
            org.xutils.common.util.LogUtil.e(r5, r1)     // Catch:{ all -> 0x0087 }
            r9.r()
            r1 = r4
            goto L_0x009a
        L_0x0094:
            r9.r()
            throw r0
        L_0x0098:
            java.lang.Object r1 = r9.h
        L_0x009a:
            boolean r5 = r9.b()
            if (r5 != 0) goto L_0x00ce
            if (r1 == 0) goto L_0x00e2
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r3]
            r6[r2] = r1
            r9.b(r5, r6)
        L_0x00aa:
            java.lang.Boolean r5 = r9.i
            if (r5 != 0) goto L_0x00c5
            java.lang.Object r5 = r9.j
            monitor-enter(r5)
            java.lang.Object r6 = r9.j     // Catch:{ InterruptedException -> 0x00bb, Throwable -> 0x00b9 }
            r6.wait()     // Catch:{ InterruptedException -> 0x00bb, Throwable -> 0x00b9 }
            goto L_0x00b9
        L_0x00b7:
            r0 = move-exception
            goto L_0x00c3
        L_0x00b9:
            monitor-exit(r5)     // Catch:{ all -> 0x00b7 }
            goto L_0x00aa
        L_0x00bb:
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException     // Catch:{ all -> 0x00b7 }
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)     // Catch:{ all -> 0x00b7 }
            throw r0     // Catch:{ all -> 0x00b7 }
        L_0x00c3:
            monitor-exit(r5)     // Catch:{ all -> 0x00b7 }
            throw r0
        L_0x00c5:
            java.lang.Boolean r5 = r9.i
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x00e2
            return r4
        L_0x00ce:
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)
            throw r0
        L_0x00d6:
            r9.r()
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)
            throw r0
        L_0x00e1:
            r1 = r4
        L_0x00e2:
            java.lang.Boolean r5 = r9.i
            if (r5 != 0) goto L_0x00ec
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            r9.i = r5
        L_0x00ec:
            if (r1 != 0) goto L_0x00f3
            org.xutils.http.request.UriRequest r1 = r9.c
            r1.f()
        L_0x00f3:
            org.xutils.common.Callback$CommonCallback<ResultType> r1 = r9.g
            boolean r1 = r1 instanceof org.xutils.common.Callback.ProxyCacheCallback
            if (r1 == 0) goto L_0x0104
            org.xutils.common.Callback$CommonCallback<ResultType> r1 = r9.g
            org.xutils.common.Callback$ProxyCacheCallback r1 = (org.xutils.common.Callback.ProxyCacheCallback) r1
            boolean r1 = r1.a()
            if (r1 == 0) goto L_0x0104
            return r4
        L_0x0104:
            r5 = r4
            r6 = r5
            r1 = 1
            r7 = 0
        L_0x0108:
            if (r1 == 0) goto L_0x0204
            boolean r1 = r9.b()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r1 != 0) goto L_0x01b4
            org.xutils.http.request.UriRequest r1 = r9.c     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            r1.close()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            r9.r()     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.String r8 = "load: "
            r1.append(r8)     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            org.xutils.http.request.UriRequest r8 = r9.c     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.String r8 = r8.aj_()     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            r1.append(r8)     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            org.xutils.common.util.LogUtil.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            org.xutils.http.HttpTask$RequestWorker r1 = new org.xutils.http.HttpTask$RequestWorker     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            r9.d = r1     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            org.xutils.http.HttpTask<ResultType>$RequestWorker r1 = r9.d     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            r1.a()     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            org.xutils.http.HttpTask<ResultType>$RequestWorker r1 = r9.d     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.Throwable r1 = r1.b     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            if (r1 != 0) goto L_0x019c
            org.xutils.http.HttpTask<ResultType>$RequestWorker r1 = r9.d     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.Object r1 = r1.f10768a     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            r9.h = r1     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            org.xutils.common.Callback$PrepareCallback r1 = r9.l     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r1 == 0) goto L_0x0173
            boolean r1 = r9.b()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r1 != 0) goto L_0x016b
            org.xutils.common.Callback$PrepareCallback r1 = r9.l     // Catch:{ all -> 0x0166 }
            java.lang.Object r8 = r9.h     // Catch:{ all -> 0x0166 }
            java.lang.Object r1 = r1.c(r8)     // Catch:{ all -> 0x0166 }
            r9.r()     // Catch:{ HttpRedirectException -> 0x0163, Throwable -> 0x0160 }
            goto L_0x0175
        L_0x0160:
            r5 = move-exception
            r6 = r1
            goto L_0x01bd
        L_0x0163:
            r6 = r1
            goto L_0x01e7
        L_0x0166:
            r1 = move-exception
            r9.r()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            throw r1     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x016b:
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            java.lang.String r8 = "cancelled before request"
            r1.<init>(r8)     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            throw r1     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x0173:
            java.lang.Object r1 = r9.h     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x0175:
            r6 = r1
            org.xutils.common.Callback$CacheCallback<ResultType> r1 = r9.k     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r1 == 0) goto L_0x018b
            org.xutils.http.RequestParams r1 = r9.b     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            org.xutils.http.HttpMethod r1 = r1.b()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            boolean r1 = org.xutils.http.HttpMethod.permitsCache(r1)     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r1 == 0) goto L_0x018b
            org.xutils.http.request.UriRequest r1 = r9.c     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            r1.p()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x018b:
            boolean r1 = r9.b()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r1 != 0) goto L_0x0194
            r1 = 0
            goto L_0x0108
        L_0x0194:
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            java.lang.String r8 = "cancelled after request"
            r1.<init>(r8)     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            throw r1     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x019c:
            org.xutils.http.HttpTask<ResultType>$RequestWorker r1 = r9.d     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            java.lang.Throwable r1 = r1.b     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
            throw r1     // Catch:{ Throwable -> 0x01a1, HttpRedirectException -> 0x01e7 }
        L_0x01a1:
            r1 = move-exception
            r9.r()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            boolean r8 = r9.b()     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            if (r8 == 0) goto L_0x01b3
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            java.lang.String r8 = "cancelled during request"
            r1.<init>(r8)     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            throw r1     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x01b3:
            throw r1     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x01b4:
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            java.lang.String r8 = "cancelled before request"
            r1.<init>(r8)     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
            throw r1     // Catch:{ HttpRedirectException -> 0x01e7, Throwable -> 0x01bc }
        L_0x01bc:
            r5 = move-exception
        L_0x01bd:
            org.xutils.http.request.UriRequest r1 = r9.c
            int r1 = r1.i()
            r8 = 304(0x130, float:4.26E-43)
            if (r1 == r8) goto L_0x01e6
            switch(r1) {
                case 204: goto L_0x01e6;
                case 205: goto L_0x01e6;
                default: goto L_0x01ca;
            }
        L_0x01ca:
            boolean r1 = r9.b()
            if (r1 == 0) goto L_0x01dc
            boolean r1 = r5 instanceof org.xutils.common.Callback.CancelledException
            if (r1 != 0) goto L_0x01dc
            org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException
            java.lang.String r5 = "canceled by user"
            r1.<init>(r5)
            r5 = r1
        L_0x01dc:
            org.xutils.http.request.UriRequest r1 = r9.c
            int r7 = r7 + 1
            boolean r1 = r0.a(r1, r5, r7)
            goto L_0x0108
        L_0x01e6:
            return r4
        L_0x01e7:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r8 = "Http Redirect:"
            r1.append(r8)
            org.xutils.http.RequestParams r8 = r9.b
            java.lang.String r8 = r8.o()
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            org.xutils.common.util.LogUtil.e(r1)
            r1 = 1
            goto L_0x0108
        L_0x0204:
            if (r5 == 0) goto L_0x0214
            if (r6 != 0) goto L_0x0214
            java.lang.Boolean r0 = r9.i
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0211
            goto L_0x0214
        L_0x0211:
            r9.f = r3
            throw r5
        L_0x0214:
            return r6
        L_0x0215:
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)
            throw r0
        L_0x021d:
            org.xutils.common.Callback$CancelledException r0 = new org.xutils.common.Callback$CancelledException
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.HttpTask.c():java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void a(int i2, Object... objArr) {
        Object obj;
        switch (i2) {
            case 1:
                if (this.o != null) {
                    this.o.a(objArr[0]);
                    return;
                }
                return;
            case 2:
                synchronized (this.j) {
                    try {
                        Object obj2 = objArr[0];
                        if (this.o != null) {
                            this.o.a(this.c, obj2);
                        }
                        this.i = Boolean.valueOf(this.k.a(obj2));
                        obj = this.j;
                    } catch (Throwable th) {
                        try {
                            this.i = false;
                            this.g.a(th, true);
                            obj = this.j;
                        } catch (Throwable th2) {
                            this.j.notifyAll();
                            throw th2;
                        }
                    }
                    obj.notifyAll();
                }
                return;
            case 3:
                if (this.m != null && objArr.length == 3) {
                    try {
                        this.m.a(objArr[0].longValue(), objArr[1].longValue(), objArr[2].booleanValue());
                        return;
                    } catch (Throwable th3) {
                        this.g.a(th3, true);
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.o != null) {
            this.o.a(this.b);
        }
        if (this.m != null) {
            this.m.d();
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.o != null) {
            this.o.b(this.b);
        }
        if (this.m != null) {
            this.m.e();
        }
    }

    /* access modifiers changed from: protected */
    public void a(ResultType resulttype) {
        if (!this.f) {
            if (this.o != null) {
                this.o.b(this.c, resulttype);
            }
            this.g.b(resulttype);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Throwable th, boolean z2) {
        if (this.o != null) {
            this.o.a(this.c, th, z2);
        }
        this.g.a(th, z2);
    }

    /* access modifiers changed from: protected */
    public void a(Callback.CancelledException cancelledException) {
        if (this.o != null) {
            this.o.b(this.c);
        }
        this.g.a(cancelledException);
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (this.o != null) {
            this.o.c(this.c);
        }
        x.c().c(new Runnable() {
            public void run() {
                HttpTask.this.s();
            }
        });
        this.g.c();
    }

    private void r() {
        if (this.h instanceof Closeable) {
            IOUtil.a((Closeable) this.h);
        }
        this.h = null;
    }

    /* access modifiers changed from: protected */
    public void i() {
        x.c().c(new Runnable() {
            public void run() {
                HttpTask.this.s();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return this.b.D();
    }

    /* access modifiers changed from: private */
    public void s() {
        r();
        IOUtil.a((Closeable) this.c);
    }

    public Executor h() {
        return this.e;
    }

    public Priority g() {
        return this.b.t();
    }

    public boolean a(long j2, long j3, boolean z2) {
        if (b() || k()) {
            return false;
        }
        if (!(this.m == null || this.c == null || j2 <= 0)) {
            if (j2 < j3) {
                j2 = j3;
            }
            if (z2) {
                this.y = System.currentTimeMillis();
                b(3, Long.valueOf(j2), Long.valueOf(j3), Boolean.valueOf(this.c.b()));
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.y >= this.z) {
                    this.y = currentTimeMillis;
                    b(3, Long.valueOf(j2), Long.valueOf(j3), Boolean.valueOf(this.c.b()));
                }
            }
        }
        if (b() || k()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.b.toString();
    }

    private final class RequestWorker {

        /* renamed from: a  reason: collision with root package name */
        Object f10768a;
        Throwable b;

        private RequestWorker() {
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(2:72|73) */
        /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
            r5.b = r0;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:72:0x0113 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r5 = this;
                r0 = 0
                java.lang.Class<java.io.File> r1 = java.io.File.class
                org.xutils.http.HttpTask r2 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x00b2 }
                java.lang.reflect.Type r2 = r2.p     // Catch:{ Throwable -> 0x00b2 }
                if (r1 != r2) goto L_0x003d
            L_0x000b:
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ Throwable -> 0x00b2 }
                int r1 = r1.get()     // Catch:{ Throwable -> 0x00b2 }
                r2 = 3
                if (r1 < r2) goto L_0x0036
                org.xutils.http.HttpTask r1 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x00b2 }
                boolean r1 = r1.b()     // Catch:{ Throwable -> 0x00b2 }
                if (r1 != 0) goto L_0x0036
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ Throwable -> 0x00b2 }
                monitor-enter(r1)     // Catch:{ Throwable -> 0x00b2 }
                java.util.concurrent.atomic.AtomicInteger r2 = org.xutils.http.HttpTask.r     // Catch:{ InterruptedException -> 0x0031, Throwable -> 0x002f }
                r3 = 10
                r2.wait(r3)     // Catch:{ InterruptedException -> 0x0031, Throwable -> 0x002f }
                goto L_0x002f
            L_0x002d:
                r0 = move-exception
                goto L_0x0034
            L_0x002f:
                monitor-exit(r1)     // Catch:{ all -> 0x002d }
                goto L_0x000b
            L_0x0031:
                r0 = 1
                monitor-exit(r1)     // Catch:{ all -> 0x002d }
                goto L_0x0036
            L_0x0034:
                monitor-exit(r1)     // Catch:{ all -> 0x002d }
                throw r0     // Catch:{ Throwable -> 0x00b2 }
            L_0x0036:
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ Throwable -> 0x00b2 }
                r1.incrementAndGet()     // Catch:{ Throwable -> 0x00b2 }
            L_0x003d:
                if (r0 != 0) goto L_0x0091
                org.xutils.http.HttpTask r1 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x00b2 }
                boolean r1 = r1.b()     // Catch:{ Throwable -> 0x00b2 }
                if (r1 == 0) goto L_0x0048
                goto L_0x0091
            L_0x0048:
                org.xutils.http.HttpTask r0 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0064 }
                org.xutils.http.request.UriRequest r0 = r0.c     // Catch:{ Throwable -> 0x0064 }
                org.xutils.http.HttpTask r1 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0064 }
                org.xutils.http.app.RequestInterceptListener r1 = r1.n     // Catch:{ Throwable -> 0x0064 }
                r0.a((org.xutils.http.app.RequestInterceptListener) r1)     // Catch:{ Throwable -> 0x0064 }
                org.xutils.http.HttpTask r0 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0064 }
                org.xutils.http.request.UriRequest r0 = r0.c     // Catch:{ Throwable -> 0x0064 }
                java.lang.Object r0 = r0.d()     // Catch:{ Throwable -> 0x0064 }
                r5.f10768a = r0     // Catch:{ Throwable -> 0x0064 }
                goto L_0x0067
            L_0x0064:
                r0 = move-exception
                r5.b = r0     // Catch:{ Throwable -> 0x00b2 }
            L_0x0067:
                java.lang.Throwable r0 = r5.b     // Catch:{ Throwable -> 0x00b2 }
                if (r0 != 0) goto L_0x008e
                java.lang.Class<java.io.File> r0 = java.io.File.class
                org.xutils.http.HttpTask r1 = org.xutils.http.HttpTask.this
                java.lang.reflect.Type r1 = r1.p
                if (r0 != r1) goto L_0x0137
                java.util.concurrent.atomic.AtomicInteger r0 = org.xutils.http.HttpTask.r
                monitor-enter(r0)
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ all -> 0x008b }
                r1.decrementAndGet()     // Catch:{ all -> 0x008b }
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ all -> 0x008b }
                r1.notifyAll()     // Catch:{ all -> 0x008b }
                monitor-exit(r0)     // Catch:{ all -> 0x008b }
                goto L_0x0137
            L_0x008b:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x008b }
                throw r1
            L_0x008e:
                java.lang.Throwable r0 = r5.b     // Catch:{ Throwable -> 0x00b2 }
                throw r0     // Catch:{ Throwable -> 0x00b2 }
            L_0x0091:
                org.xutils.common.Callback$CancelledException r1 = new org.xutils.common.Callback$CancelledException     // Catch:{ Throwable -> 0x00b2 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b2 }
                r2.<init>()     // Catch:{ Throwable -> 0x00b2 }
                java.lang.String r3 = "cancelled before request"
                r2.append(r3)     // Catch:{ Throwable -> 0x00b2 }
                if (r0 == 0) goto L_0x00a2
                java.lang.String r0 = "(interrupted)"
                goto L_0x00a4
            L_0x00a2:
                java.lang.String r0 = ""
            L_0x00a4:
                r2.append(r0)     // Catch:{ Throwable -> 0x00b2 }
                java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x00b2 }
                r1.<init>(r0)     // Catch:{ Throwable -> 0x00b2 }
                throw r1     // Catch:{ Throwable -> 0x00b2 }
            L_0x00af:
                r0 = move-exception
                goto L_0x0138
            L_0x00b2:
                r0 = move-exception
                r5.b = r0     // Catch:{ all -> 0x00af }
                boolean r1 = r0 instanceof org.xutils.ex.HttpException     // Catch:{ all -> 0x00af }
                if (r1 == 0) goto L_0x0115
                r1 = r0
                org.xutils.ex.HttpException r1 = (org.xutils.ex.HttpException) r1     // Catch:{ all -> 0x00af }
                int r2 = r1.getCode()     // Catch:{ all -> 0x00af }
                r3 = 301(0x12d, float:4.22E-43)
                if (r2 == r3) goto L_0x00c8
                r3 = 302(0x12e, float:4.23E-43)
                if (r2 != r3) goto L_0x0115
            L_0x00c8:
                org.xutils.http.HttpTask r3 = org.xutils.http.HttpTask.this     // Catch:{ all -> 0x00af }
                org.xutils.http.RequestParams r3 = r3.b     // Catch:{ all -> 0x00af }
                org.xutils.http.app.RedirectHandler r3 = r3.G()     // Catch:{ all -> 0x00af }
                if (r3 == 0) goto L_0x0115
                org.xutils.http.HttpTask r4 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.request.UriRequest r4 = r4.c     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.RequestParams r3 = r3.a(r4)     // Catch:{ Throwable -> 0x0113 }
                if (r3 == 0) goto L_0x0115
                org.xutils.http.HttpMethod r4 = r3.b()     // Catch:{ Throwable -> 0x0113 }
                if (r4 != 0) goto L_0x00f3
                org.xutils.http.HttpTask r4 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.RequestParams r4 = r4.b     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.HttpMethod r4 = r4.b()     // Catch:{ Throwable -> 0x0113 }
                r3.a((org.xutils.http.HttpMethod) r4)     // Catch:{ Throwable -> 0x0113 }
            L_0x00f3:
                org.xutils.http.HttpTask r4 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.RequestParams unused = r4.b = r3     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.HttpTask r3 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.HttpTask r4 = org.xutils.http.HttpTask.this     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.request.UriRequest r4 = r4.p()     // Catch:{ Throwable -> 0x0113 }
                org.xutils.http.request.UriRequest unused = r3.c = r4     // Catch:{ Throwable -> 0x0113 }
                org.xutils.ex.HttpRedirectException r3 = new org.xutils.ex.HttpRedirectException     // Catch:{ Throwable -> 0x0113 }
                java.lang.String r4 = r1.getMessage()     // Catch:{ Throwable -> 0x0113 }
                java.lang.String r1 = r1.getResult()     // Catch:{ Throwable -> 0x0113 }
                r3.<init>(r2, r4, r1)     // Catch:{ Throwable -> 0x0113 }
                r5.b = r3     // Catch:{ Throwable -> 0x0113 }
                goto L_0x0115
            L_0x0113:
                r5.b = r0     // Catch:{ all -> 0x00af }
            L_0x0115:
                java.lang.Class<java.io.File> r0 = java.io.File.class
                org.xutils.http.HttpTask r1 = org.xutils.http.HttpTask.this
                java.lang.reflect.Type r1 = r1.p
                if (r0 != r1) goto L_0x0137
                java.util.concurrent.atomic.AtomicInteger r0 = org.xutils.http.HttpTask.r
                monitor-enter(r0)
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ all -> 0x0134 }
                r1.decrementAndGet()     // Catch:{ all -> 0x0134 }
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r     // Catch:{ all -> 0x0134 }
                r1.notifyAll()     // Catch:{ all -> 0x0134 }
                monitor-exit(r0)     // Catch:{ all -> 0x0134 }
                goto L_0x0137
            L_0x0134:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0134 }
                throw r1
            L_0x0137:
                return
            L_0x0138:
                java.lang.Class<java.io.File> r1 = java.io.File.class
                org.xutils.http.HttpTask r2 = org.xutils.http.HttpTask.this
                java.lang.reflect.Type r2 = r2.p
                if (r1 != r2) goto L_0x015a
                java.util.concurrent.atomic.AtomicInteger r1 = org.xutils.http.HttpTask.r
                monitor-enter(r1)
                java.util.concurrent.atomic.AtomicInteger r2 = org.xutils.http.HttpTask.r     // Catch:{ all -> 0x0157 }
                r2.decrementAndGet()     // Catch:{ all -> 0x0157 }
                java.util.concurrent.atomic.AtomicInteger r2 = org.xutils.http.HttpTask.r     // Catch:{ all -> 0x0157 }
                r2.notifyAll()     // Catch:{ all -> 0x0157 }
                monitor-exit(r1)     // Catch:{ all -> 0x0157 }
                goto L_0x015a
            L_0x0157:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0157 }
                throw r0
            L_0x015a:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.HttpTask.RequestWorker.a():void");
        }
    }
}
