package com.alipay.zoloz.android.phone.mrpc.core;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpManager implements Transport {
    public static final String TAG = "HttpManager";

    /* renamed from: a  reason: collision with root package name */
    private static HttpManager f1185a;
    private static final ThreadFactory h = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f1186a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "com.alipay.zoloz.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f1186a.getAndIncrement());
            thread.setPriority(4);
            return thread;
        }
    };
    private ThreadPoolExecutor b;
    private AndroidHttpClient c;
    private long d;
    private long e;
    private long f;
    private int g;
    Context mContext;

    public HttpManager(Context context) {
        this.mContext = context;
        a();
    }

    public static final HttpManager getInstance(Context context) {
        if (f1185a != null) {
            return f1185a;
        }
        return a(context);
    }

    private static final synchronized HttpManager a(Context context) {
        synchronized (HttpManager.class) {
            if (f1185a != null) {
                HttpManager httpManager = f1185a;
                return httpManager;
            }
            f1185a = new HttpManager(context);
            HttpManager httpManager2 = f1185a;
            return httpManager2;
        }
    }

    private void a() {
        this.c = AndroidHttpClient.newInstance("android");
        this.b = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), h, new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            this.b.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        CookieSyncManager.createInstance(this.mContext);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public Future<Response> execute(Request request) {
        if (request instanceof HttpUrlRequest) {
            if (MiscUtils.isDebugger(this.mContext)) {
                Log.i(TAG, dumpPerf());
            }
            FutureTask<Response> a2 = a(generateWorker((HttpUrlRequest) request));
            this.b.execute(a2);
            return a2;
        }
        throw new RuntimeException("request send error.");
    }

    private FutureTask<Response> a(final HttpWorker httpWorker) {
        return new FutureTask<Response>(httpWorker) {
            /* access modifiers changed from: protected */
            public void done() {
                HttpUrlRequest request = httpWorker.getRequest();
                TransportCallback callback = request.getCallback();
                if (callback == null) {
                    super.done();
                    return;
                }
                try {
                    Response response = (Response) get();
                    if (!isCancelled()) {
                        if (!request.isCanceled()) {
                            if (response != null) {
                                callback.onPostExecute(request, response);
                                return;
                            }
                            return;
                        }
                    }
                    request.cancel();
                    if (!isCancelled() || !isDone()) {
                        cancel(false);
                    }
                    callback.onCancelled(request);
                } catch (InterruptedException e) {
                    callback.onFailed(request, 7, e + "");
                } catch (ExecutionException e2) {
                    if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                        callback.onFailed(request, 6, e2 + "");
                        return;
                    }
                    HttpException httpException = (HttpException) e2.getCause();
                    callback.onFailed(request, httpException.getCode(), httpException.getMsg());
                } catch (CancellationException unused) {
                    request.cancel();
                    callback.onCancelled(request);
                } catch (Throwable th) {
                    throw new RuntimeException("An error occured while executing http request", th);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public HttpWorker generateWorker(HttpUrlRequest httpUrlRequest) {
        return new HttpWorker(this, httpUrlRequest);
    }

    public AndroidHttpClient getHttpClient() {
        return this.c;
    }

    public void addDataSize(long j) {
        this.d += j;
    }

    public void addConnectTime(long j) {
        this.e += j;
        this.g++;
    }

    public void addSocketTime(long j) {
        this.f += j;
    }

    public long getAverageSpeed() {
        if (this.f == 0) {
            return 0;
        }
        return ((this.d * 1000) / this.f) >> 10;
    }

    public long getAverageConnectTime() {
        if (this.g == 0) {
            return 0;
        }
        return this.e / ((long) this.g);
    }

    public String dumpPerf() {
        return String.format(TAG + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times", new Object[]{Integer.valueOf(this.b.getActiveCount()), Long.valueOf(this.b.getCompletedTaskCount()), Long.valueOf(this.b.getTaskCount()), Long.valueOf(getAverageSpeed()), Long.valueOf(getAverageConnectTime()), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.f), Integer.valueOf(this.g)});
    }

    public void close() {
        if (this.b != null) {
            this.b.shutdown();
            this.b = null;
        }
        if (this.c != null) {
            this.c.close();
        }
        this.c = null;
    }
}
