package com.alipay.deviceid.module.x;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.deviceid.module.rpc.mrpc.core.HttpException;
import com.alipay.zoloz.android.phone.mrpc.core.HttpManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ay implements bm {
    private static ay g;
    private static final ThreadFactory i = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f885a = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f885a.getAndIncrement());
            thread.setPriority(4);
            return thread;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    Context f883a;
    at b = at.a("android");
    long c;
    long d;
    long e;
    int f;
    private ThreadPoolExecutor h = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), i, new ThreadPoolExecutor.CallerRunsPolicy());

    private ay(Context context) {
        this.f883a = context;
        try {
            this.h.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        CookieSyncManager.createInstance(this.f883a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public static final ay a(Context context) {
        if (g != null) {
            return g;
        }
        return b(context);
    }

    private static final synchronized ay b(Context context) {
        synchronized (ay.class) {
            if (g != null) {
                ay ayVar = g;
                return ayVar;
            }
            ay ayVar2 = new ay(context);
            g = ayVar2;
            return ayVar2;
        }
    }

    public final Future<bf> a(be beVar) {
        long j;
        if (bd.a(this.f883a)) {
            String str = HttpManager.TAG + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.h.getActiveCount());
            objArr[1] = Long.valueOf(this.h.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.h.getTaskCount());
            long j2 = 0;
            if (this.e == 0) {
                j = 0;
            } else {
                j = ((this.c * 1000) / this.e) >> 10;
            }
            objArr[3] = Long.valueOf(j);
            if (this.f != 0) {
                j2 = this.d / ((long) this.f);
            }
            objArr[4] = Long.valueOf(j2);
            objArr[5] = Long.valueOf(this.c);
            objArr[6] = Long.valueOf(this.d);
            objArr[7] = Long.valueOf(this.e);
            objArr[8] = Integer.valueOf(this.f);
            String.format(str, objArr);
        }
        final bb bbVar = new bb(this, (az) beVar);
        AnonymousClass1 r10 = new FutureTask<bf>(bbVar) {
            /* access modifiers changed from: protected */
            public final void done() {
                az a2 = bbVar.a();
                if (a2.a() == null) {
                    super.done();
                    return;
                }
                try {
                    get();
                    if (isCancelled() || a2.f) {
                        a2.f = true;
                        if (!isCancelled() || !isDone()) {
                            cancel(false);
                        }
                    }
                } catch (InterruptedException e) {
                    new StringBuilder().append(e);
                } catch (ExecutionException e2) {
                    if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                        new StringBuilder().append(e2);
                        return;
                    }
                    HttpException httpException = (HttpException) e2.getCause();
                    httpException.getCode();
                    httpException.getMsg();
                } catch (CancellationException unused) {
                    a2.f = true;
                } catch (Throwable th) {
                    throw new RuntimeException("An error occured while executing http request", th);
                }
            }
        };
        this.h.execute(r10);
        return r10;
    }
}
