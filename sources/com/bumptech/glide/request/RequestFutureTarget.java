package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<R> implements FutureTarget<R>, RequestListener<R> {

    /* renamed from: a  reason: collision with root package name */
    private static final Waiter f5059a = new Waiter();
    private final int b;
    private final int d;
    private final boolean e;
    private final Waiter f;
    @Nullable
    private R g;
    @Nullable
    private Request h;
    private boolean i;
    private boolean j;
    private boolean k;
    @Nullable
    private GlideException l;

    public void a(@Nullable Drawable drawable) {
    }

    public void b(@Nullable Drawable drawable) {
    }

    public void b(@NonNull SizeReadyCallback sizeReadyCallback) {
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public RequestFutureTarget(int i2, int i3) {
        this(i2, i3, true, f5059a);
    }

    RequestFutureTarget(int i2, int i3, boolean z, Waiter waiter) {
        this.b = i2;
        this.d = i3;
        this.e = z;
        this.f = waiter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean cancel(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isDone()     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x000a
            r3 = 0
            monitor-exit(r2)
            return r3
        L_0x000a:
            r0 = 1
            r2.i = r0     // Catch:{ all -> 0x0022 }
            com.bumptech.glide.request.RequestFutureTarget$Waiter r1 = r2.f     // Catch:{ all -> 0x0022 }
            r1.a(r2)     // Catch:{ all -> 0x0022 }
            if (r3 == 0) goto L_0x0020
            com.bumptech.glide.request.Request r3 = r2.h     // Catch:{ all -> 0x0022 }
            if (r3 == 0) goto L_0x0020
            com.bumptech.glide.request.Request r3 = r2.h     // Catch:{ all -> 0x0022 }
            r3.b()     // Catch:{ all -> 0x0022 }
            r3 = 0
            r2.h = r3     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r2)
            return r0
        L_0x0022:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestFutureTarget.cancel(boolean):boolean");
    }

    public synchronized boolean isCancelled() {
        return this.i;
    }

    public synchronized boolean isDone() {
        return this.i || this.j || this.k;
    }

    public R get() throws InterruptedException, ExecutionException {
        try {
            return a((Long) null);
        } catch (TimeoutException e2) {
            throw new AssertionError(e2);
        }
    }

    public R get(long j2, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(timeUnit.toMillis(j2)));
    }

    public void a(@NonNull SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.a(this.b, this.d);
    }

    public synchronized void a(@Nullable Request request) {
        this.h = request;
    }

    @Nullable
    public synchronized Request a() {
        return this.h;
    }

    public synchronized void c(@Nullable Drawable drawable) {
    }

    public synchronized void a(@NonNull R r, @Nullable Transition<? super R> transition) {
    }

    private synchronized R a(Long l2) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.e && !isDone()) {
            Util.b();
        }
        if (this.i) {
            throw new CancellationException();
        } else if (this.k) {
            throw new ExecutionException(this.l);
        } else if (this.j) {
            return this.g;
        } else {
            if (l2 == null) {
                this.f.a(this, 0);
            } else if (l2.longValue() > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = l2.longValue() + currentTimeMillis;
                while (!isDone() && currentTimeMillis < longValue) {
                    this.f.a(this, longValue - currentTimeMillis);
                    currentTimeMillis = System.currentTimeMillis();
                }
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.k) {
                throw new ExecutionException(this.l);
            } else if (this.i) {
                throw new CancellationException();
            } else if (this.j) {
                return this.g;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public synchronized boolean a(@Nullable GlideException glideException, Object obj, Target<R> target, boolean z) {
        this.k = true;
        this.l = glideException;
        this.f.a(this);
        return false;
    }

    public synchronized boolean a(R r, Object obj, Target<R> target, DataSource dataSource, boolean z) {
        this.j = true;
        this.g = r;
        this.f.a(this);
        return false;
    }

    @VisibleForTesting
    static class Waiter {
        Waiter() {
        }

        /* access modifiers changed from: package-private */
        public void a(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        /* access modifiers changed from: package-private */
        public void a(Object obj) {
            obj.notifyAll();
        }
    }
}
