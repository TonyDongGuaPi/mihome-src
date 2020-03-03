package com.ximalaya.ting.android.sdkdownloader.http;

import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager;
import com.ximalaya.ting.android.sdkdownloader.http.app.RequestTracker;
import com.ximalaya.ting.android.sdkdownloader.http.request.HttpRequest;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;
import com.ximalaya.ting.android.sdkdownloader.task.AbsTask;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import com.ximalaya.ting.android.sdkdownloader.task.Priority;
import com.ximalaya.ting.android.sdkdownloader.task.PriorityExecutor;
import com.ximalaya.ting.android.sdkdownloader.util.IOUtil;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadTask<ResultType> extends AbsTask<ResultType> implements ProgressHandler {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f2354a = (!DownloadTask.class.desiredAssertionStatus());
    private static final int k = 3;
    /* access modifiers changed from: private */
    public static final AtomicInteger l = new AtomicInteger(0);
    private static final HashMap<String, WeakReference<DownloadTask<?>>> m = new HashMap<>(1);
    private static final PriorityExecutor n = new PriorityExecutor(3, false);
    private static final int o = 1;
    private static final int p = 3;
    /* access modifiers changed from: private */
    public RequestParams b;
    /* access modifiers changed from: private */
    public UriRequest c;
    private DownloadTask<ResultType>.RequestWorker d;
    private final Executor e;
    private volatile boolean f = false;
    private final Callback.CommonCallback<ResultType> g;
    private Object h = null;
    private Callback.ProgressCallback i;
    private RequestTracker j;
    private long q;
    private long r = 800;

    public void i() {
    }

    public DownloadTask(RequestParams requestParams, Callback.Cancelable cancelable, Callback.CommonCallback<ResultType> commonCallback) {
        super(cancelable);
        if (!f2354a && requestParams == null) {
            throw new AssertionError();
        } else if (f2354a || commonCallback != null) {
            this.b = requestParams;
            this.g = commonCallback;
            if (commonCallback instanceof Callback.ProgressCallback) {
                this.i = (Callback.ProgressCallback) commonCallback;
            }
            RequestTracker q2 = requestParams.q();
            if (q2 == null && (commonCallback instanceof RequestTracker)) {
                q2 = (RequestTracker) commonCallback;
            }
            if (q2 != null) {
                this.j = new RequestTrackerWrapper(q2);
            }
            if (requestParams.i() != null) {
                this.e = requestParams.i();
            } else {
                this.e = n;
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public UriRequest q() throws Throwable {
        this.b.d();
        HttpRequest httpRequest = new HttpRequest(this.b);
        httpRequest.a(this.g.getClass().getClassLoader());
        httpRequest.a((ProgressHandler) this);
        this.r = (long) this.b.n();
        b(1, httpRequest);
        return httpRequest;
    }

    private void r() {
        synchronized (m) {
            String k2 = this.b.k();
            if (!TextUtils.isEmpty(k2)) {
                WeakReference weakReference = m.get(k2);
                if (weakReference != null) {
                    DownloadTask downloadTask = (DownloadTask) weakReference.get();
                    if (downloadTask != null) {
                        downloadTask.d();
                        downloadTask.t();
                    }
                    m.remove(k2);
                }
                m.put(k2, new WeakReference(this));
            }
            if (m.size() > 3) {
                Iterator<Map.Entry<String, WeakReference<DownloadTask<?>>>> it = m.entrySet().iterator();
                while (it.hasNext()) {
                    WeakReference weakReference2 = (WeakReference) it.next().getValue();
                    if (weakReference2 == null || weakReference2.get() == null) {
                        it.remove();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[ExcHandler: HttpRedirectException (unused com.ximalaya.ting.android.sdkdownloader.exception.HttpRedirectException), SYNTHETIC, Splitter:B:9:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00e4 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ResultType a() throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r9.f()
            if (r0 != 0) goto L_0x00f9
            com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r0 = r9.q()
            r9.c = r0
            r9.r()
            com.ximalaya.ting.android.sdkdownloader.http.RequestParams r0 = r9.b
            com.ximalaya.ting.android.sdkdownloader.http.app.HttpRetryHandler r0 = r0.o()
            if (r0 != 0) goto L_0x001c
            com.ximalaya.ting.android.sdkdownloader.http.app.HttpRetryHandler r0 = new com.ximalaya.ting.android.sdkdownloader.http.app.HttpRetryHandler
            r0.<init>()
        L_0x001c:
            com.ximalaya.ting.android.sdkdownloader.http.RequestParams r1 = r9.b
            int r1 = r1.l()
            r0.a(r1)
            boolean r1 = r9.f()
            if (r1 != 0) goto L_0x00f1
            r1 = 0
            r2 = 1
            r3 = 0
            r5 = r3
            r6 = r5
            r4 = 1
            r7 = 0
        L_0x0032:
            if (r4 == 0) goto L_0x00e8
            boolean r4 = r9.f()     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            if (r4 != 0) goto L_0x008b
            com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r9.c     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            r4.close()     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            r9.s()     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            com.ximalaya.ting.android.sdkdownloader.http.DownloadTask$RequestWorker r4 = new com.ximalaya.ting.android.sdkdownloader.http.DownloadTask$RequestWorker     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            r4.<init>()     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            r9.d = r4     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            com.ximalaya.ting.android.sdkdownloader.http.DownloadTask<ResultType>$RequestWorker r4 = r9.d     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            r4.a()     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            com.ximalaya.ting.android.sdkdownloader.http.DownloadTask<ResultType>$RequestWorker r4 = r9.d     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            java.lang.Throwable r4 = r4.b     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            if (r4 != 0) goto L_0x0073
            com.ximalaya.ting.android.sdkdownloader.http.DownloadTask<ResultType>$RequestWorker r4 = r9.d     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            java.lang.Object r4 = r4.f2357a     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            r9.h = r4     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            java.lang.Object r4 = r9.h     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            boolean r6 = r9.f()     // Catch:{ HttpRedirectException -> 0x0070, Throwable -> 0x006d }
            if (r6 != 0) goto L_0x0065
            r6 = r4
            r4 = 0
            goto L_0x0032
        L_0x0065:
            com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r6 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x0070, Throwable -> 0x006d }
            java.lang.String r8 = "cancelled after request"
            r6.<init>(r8)     // Catch:{ HttpRedirectException -> 0x0070, Throwable -> 0x006d }
            throw r6     // Catch:{ HttpRedirectException -> 0x0070, Throwable -> 0x006d }
        L_0x006d:
            r5 = move-exception
            r6 = r4
            goto L_0x0094
        L_0x0070:
            r6 = r4
            goto L_0x00e5
        L_0x0073:
            com.ximalaya.ting.android.sdkdownloader.http.DownloadTask<ResultType>$RequestWorker r4 = r9.d     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            java.lang.Throwable r4 = r4.b     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
            throw r4     // Catch:{ Throwable -> 0x0078, HttpRedirectException -> 0x00e5 }
        L_0x0078:
            r4 = move-exception
            r9.s()     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            boolean r8 = r9.f()     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            if (r8 == 0) goto L_0x008a
            com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r4 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            java.lang.String r8 = "cancelled during request"
            r4.<init>(r8)     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            throw r4     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
        L_0x008a:
            throw r4     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
        L_0x008b:
            com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r4 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            java.lang.String r8 = "cancelled before request"
            r4.<init>(r8)     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
            throw r4     // Catch:{ HttpRedirectException -> 0x00e5, Throwable -> 0x0093 }
        L_0x0093:
            r5 = move-exception
        L_0x0094:
            com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r9.c
            int r4 = r4.h()
            r8 = 304(0x130, float:4.26E-43)
            if (r4 == r8) goto L_0x00e4
            r8 = 403(0x193, float:5.65E-43)
            if (r4 == r8) goto L_0x00a6
            switch(r4) {
                case 204: goto L_0x00e4;
                case 205: goto L_0x00e4;
                default: goto L_0x00a5;
            }
        L_0x00a5:
            goto L_0x00c8
        L_0x00a6:
            com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r9.c
            com.ximalaya.ting.android.sdkdownloader.http.RequestParams r4 = r4.j()
            r9.b = r4
            com.ximalaya.ting.android.sdkdownloader.http.RequestParams r4 = r9.b     // Catch:{ Throwable -> 0x00c4 }
            int r4 = r4.s()     // Catch:{ Throwable -> 0x00c4 }
            int r8 = com.ximalaya.ting.android.sdkdownloader.http.loader.FileLoader.d     // Catch:{ Throwable -> 0x00c4 }
            if (r4 != r8) goto L_0x00bd
            com.ximalaya.ting.android.sdkdownloader.http.RequestParams r4 = r9.b     // Catch:{ Throwable -> 0x00c4 }
            r4.t()     // Catch:{ Throwable -> 0x00c4 }
        L_0x00bd:
            com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r9.q()     // Catch:{ Throwable -> 0x00c4 }
            r9.c = r4     // Catch:{ Throwable -> 0x00c4 }
            goto L_0x00c8
        L_0x00c4:
            r4 = move-exception
            r4.printStackTrace()
        L_0x00c8:
            boolean r4 = r9.f()
            if (r4 == 0) goto L_0x00da
            boolean r4 = r5 instanceof com.ximalaya.ting.android.sdkdownloader.task.Callback.CancelledException
            if (r4 != 0) goto L_0x00da
            com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r4 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException
            java.lang.String r5 = "canceled by user"
            r4.<init>(r5)
            r5 = r4
        L_0x00da:
            com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r9.c
            int r7 = r7 + 1
            boolean r4 = r0.a(r4, r5, r7)
            goto L_0x0032
        L_0x00e4:
            return r3
        L_0x00e5:
            r4 = 1
            goto L_0x0032
        L_0x00e8:
            if (r5 == 0) goto L_0x00f0
            if (r6 == 0) goto L_0x00ed
            goto L_0x00f0
        L_0x00ed:
            r9.f = r2
            throw r5
        L_0x00f0:
            return r6
        L_0x00f1:
            com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r0 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)
            throw r0
        L_0x00f9:
            com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r0 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException
            java.lang.String r1 = "cancelled before request"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.a():java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void a(int i2, Object... objArr) {
        if (i2 != 1) {
            if (i2 == 3 && this.i != null && objArr.length == 3) {
                try {
                    this.i.a(objArr[0].longValue(), objArr[1].longValue(), objArr[2].booleanValue());
                } catch (Throwable th) {
                    this.g.a(th, true);
                }
            }
        } else if (this.j != null) {
            this.j.a(objArr[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.j != null) {
            this.j.a(this.b);
        }
        if (this.i != null) {
            this.i.a();
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (this.j != null) {
            this.j.b(this.b);
        }
        if (this.i != null) {
            this.i.b();
        }
    }

    /* access modifiers changed from: protected */
    public void a(ResultType resulttype) {
        if (!this.f) {
            if (this.j != null) {
                this.j.a(this.c, resulttype);
            }
            this.g.a(resulttype);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Throwable th, boolean z) {
        if (this.j != null) {
            this.j.a(this.c, th, z);
        }
        this.g.a(th, z);
    }

    /* access modifiers changed from: protected */
    public void a(Callback.RemovedException removedException) {
        if (this.j != null) {
            this.j.b(this.c);
        }
        this.g.a(removedException);
    }

    /* access modifiers changed from: protected */
    public void a(Callback.CancelledException cancelledException) {
        this.g.a(cancelledException);
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.j != null) {
            this.j.d(this.c);
        }
        XmDownloadManager.b().e().c(new Runnable() {
            public void run() {
                DownloadTask.this.t();
            }
        });
        this.g.c();
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.h instanceof Closeable) {
            IOUtil.a((Closeable) this.h);
        }
        this.h = null;
    }

    /* access modifiers changed from: protected */
    public void a(final boolean z) {
        Logger.a((Object) "DownloadTask : cancelWorks 1");
        XmDownloadManager.b().e().c(new Runnable() {
            public void run() {
                Logger.a((Object) "DownloadTask : cancelWorks 2");
                if (z) {
                    DownloadTask.this.s();
                    if (DownloadTask.this.c != null) {
                        try {
                            DownloadTask.this.c.f();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    DownloadTask.this.t();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return this.b.m();
    }

    /* access modifiers changed from: private */
    public void t() {
        s();
        IOUtil.a((Closeable) this.c);
    }

    public Executor k() {
        return this.e;
    }

    public Priority l() {
        return this.b.g();
    }

    public boolean a(long j2, long j3, boolean z) {
        if (f() || n()) {
            return false;
        }
        if (!(this.i == null || this.c == null || j2 <= 0)) {
            if (j2 < j3) {
                j2 = j3;
            }
            if (z) {
                this.q = System.currentTimeMillis();
                b(3, Long.valueOf(j2), Long.valueOf(j3), Boolean.valueOf(this.c.c()));
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.q >= this.r) {
                    this.q = currentTimeMillis;
                    b(3, Long.valueOf(j2), Long.valueOf(j3), Boolean.valueOf(this.c.c()));
                }
            }
        }
        if (f() || n()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.b.toString();
    }

    private final class RequestWorker {

        /* renamed from: a  reason: collision with root package name */
        Object f2357a;
        Throwable b;

        private RequestWorker() {
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(2:67|68) */
        /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
            r5.b = r0;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0025 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x00e4 */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r5 = this;
                r0 = 0
            L_0x0001:
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ Throwable -> 0x0096 }
                int r1 = r1.get()     // Catch:{ Throwable -> 0x0096 }
                r2 = 3
                if (r1 < r2) goto L_0x002c
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x0096 }
                boolean r1 = r1.f()     // Catch:{ Throwable -> 0x0096 }
                if (r1 != 0) goto L_0x002c
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ Throwable -> 0x0096 }
                monitor-enter(r1)     // Catch:{ Throwable -> 0x0096 }
                java.util.concurrent.atomic.AtomicInteger r2 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ InterruptedException -> 0x0027, Throwable -> 0x0025 }
                r3 = 10
                r2.wait(r3)     // Catch:{ InterruptedException -> 0x0027, Throwable -> 0x0025 }
                goto L_0x0025
            L_0x0023:
                r0 = move-exception
                goto L_0x002a
            L_0x0025:
                monitor-exit(r1)     // Catch:{ all -> 0x0023 }
                goto L_0x0001
            L_0x0027:
                r0 = 1
                monitor-exit(r1)     // Catch:{ all -> 0x0023 }
                goto L_0x002c
            L_0x002a:
                monitor-exit(r1)     // Catch:{ all -> 0x0023 }
                throw r0     // Catch:{ Throwable -> 0x0096 }
            L_0x002c:
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ Throwable -> 0x0096 }
                r1.incrementAndGet()     // Catch:{ Throwable -> 0x0096 }
                if (r0 != 0) goto L_0x0076
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x0096 }
                boolean r1 = r1.f()     // Catch:{ Throwable -> 0x0096 }
                if (r1 == 0) goto L_0x003e
                goto L_0x0076
            L_0x003e:
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r0 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x0053 }
                boolean r0 = r0.f()     // Catch:{ Throwable -> 0x0053 }
                if (r0 != 0) goto L_0x0056
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r0 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x0053 }
                com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r0 = r0.c     // Catch:{ Throwable -> 0x0053 }
                java.lang.Object r0 = r0.d()     // Catch:{ Throwable -> 0x0053 }
                r5.f2357a = r0     // Catch:{ Throwable -> 0x0053 }
                goto L_0x0056
            L_0x0053:
                r0 = move-exception
                r5.b = r0     // Catch:{ Throwable -> 0x0096 }
            L_0x0056:
                java.lang.Throwable r0 = r5.b     // Catch:{ Throwable -> 0x0096 }
                if (r0 != 0) goto L_0x0073
                java.util.concurrent.atomic.AtomicInteger r0 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l
                monitor-enter(r0)
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ all -> 0x0070 }
                r1.decrementAndGet()     // Catch:{ all -> 0x0070 }
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ all -> 0x0070 }
                r1.notifyAll()     // Catch:{ all -> 0x0070 }
                monitor-exit(r0)     // Catch:{ all -> 0x0070 }
                goto L_0x00fa
            L_0x0070:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0070 }
                throw r1
            L_0x0073:
                java.lang.Throwable r0 = r5.b     // Catch:{ Throwable -> 0x0096 }
                throw r0     // Catch:{ Throwable -> 0x0096 }
            L_0x0076:
                com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException r1 = new com.ximalaya.ting.android.sdkdownloader.task.Callback$CancelledException     // Catch:{ Throwable -> 0x0096 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0096 }
                r2.<init>()     // Catch:{ Throwable -> 0x0096 }
                java.lang.String r3 = "cancelled before request"
                r2.append(r3)     // Catch:{ Throwable -> 0x0096 }
                if (r0 == 0) goto L_0x0087
                java.lang.String r0 = "(interrupted)"
                goto L_0x0089
            L_0x0087:
                java.lang.String r0 = ""
            L_0x0089:
                r2.append(r0)     // Catch:{ Throwable -> 0x0096 }
                java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x0096 }
                r1.<init>(r0)     // Catch:{ Throwable -> 0x0096 }
                throw r1     // Catch:{ Throwable -> 0x0096 }
            L_0x0094:
                r0 = move-exception
                goto L_0x00fe
            L_0x0096:
                r0 = move-exception
                r5.b = r0     // Catch:{ all -> 0x0094 }
                boolean r1 = r0 instanceof com.ximalaya.ting.android.sdkdownloader.exception.HttpException     // Catch:{ all -> 0x0094 }
                if (r1 == 0) goto L_0x00e6
                r1 = r0
                com.ximalaya.ting.android.sdkdownloader.exception.HttpException r1 = (com.ximalaya.ting.android.sdkdownloader.exception.HttpException) r1     // Catch:{ all -> 0x0094 }
                int r2 = r1.getCode()     // Catch:{ all -> 0x0094 }
                r3 = 301(0x12d, float:4.22E-43)
                if (r2 == r3) goto L_0x00ac
                r3 = 302(0x12e, float:4.23E-43)
                if (r2 != r3) goto L_0x00e6
            L_0x00ac:
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r3 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ all -> 0x0094 }
                com.ximalaya.ting.android.sdkdownloader.http.RequestParams r3 = r3.b     // Catch:{ all -> 0x0094 }
                com.ximalaya.ting.android.sdkdownloader.http.app.RedirectHandler r3 = r3.p()     // Catch:{ all -> 0x0094 }
                if (r3 == 0) goto L_0x00e6
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r4 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r4.c     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.RequestParams r3 = r3.a(r4)     // Catch:{ Throwable -> 0x00e4 }
                if (r3 == 0) goto L_0x00e6
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r4 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.RequestParams unused = r4.b = r3     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r3 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.DownloadTask r4 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.this     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest r4 = r4.q()     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest unused = r3.c = r4     // Catch:{ Throwable -> 0x00e4 }
                com.ximalaya.ting.android.sdkdownloader.exception.HttpRedirectException r3 = new com.ximalaya.ting.android.sdkdownloader.exception.HttpRedirectException     // Catch:{ Throwable -> 0x00e4 }
                java.lang.String r4 = r1.getMessage()     // Catch:{ Throwable -> 0x00e4 }
                java.lang.String r1 = r1.getResult()     // Catch:{ Throwable -> 0x00e4 }
                r3.<init>(r2, r4, r1)     // Catch:{ Throwable -> 0x00e4 }
                r5.b = r3     // Catch:{ Throwable -> 0x00e4 }
                goto L_0x00e6
            L_0x00e4:
                r5.b = r0     // Catch:{ all -> 0x0094 }
            L_0x00e6:
                java.util.concurrent.atomic.AtomicInteger r0 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l
                monitor-enter(r0)
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ all -> 0x00fb }
                r1.decrementAndGet()     // Catch:{ all -> 0x00fb }
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ all -> 0x00fb }
                r1.notifyAll()     // Catch:{ all -> 0x00fb }
                monitor-exit(r0)     // Catch:{ all -> 0x00fb }
            L_0x00fa:
                return
            L_0x00fb:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00fb }
                throw r1
            L_0x00fe:
                java.util.concurrent.atomic.AtomicInteger r1 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l
                monitor-enter(r1)
                java.util.concurrent.atomic.AtomicInteger r2 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ all -> 0x0113 }
                r2.decrementAndGet()     // Catch:{ all -> 0x0113 }
                java.util.concurrent.atomic.AtomicInteger r2 = com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.l     // Catch:{ all -> 0x0113 }
                r2.notifyAll()     // Catch:{ all -> 0x0113 }
                monitor-exit(r1)     // Catch:{ all -> 0x0113 }
                throw r0
            L_0x0113:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0113 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.sdkdownloader.http.DownloadTask.RequestWorker.a():void");
        }
    }
}
