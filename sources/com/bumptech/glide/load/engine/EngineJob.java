package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.Pools;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

class EngineJob<R> implements DecodeJob.Callback<R>, FactoryPools.Poolable {
    private static final EngineResourceFactory e = new EngineResourceFactory();

    /* renamed from: a  reason: collision with root package name */
    final ResourceCallbacksAndExecutors f4873a;
    DataSource b;
    GlideException c;
    EngineResource<?> d;
    private final StateVerifier f;
    private final Pools.Pool<EngineJob<?>> g;
    private final EngineResourceFactory h;
    private final EngineJobListener i;
    private final GlideExecutor j;
    private final GlideExecutor k;
    private final GlideExecutor l;
    private final GlideExecutor m;
    private final AtomicInteger n;
    private Key o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private Resource<?> t;
    private boolean u;
    private boolean v;
    private DecodeJob<R> w;
    private volatile boolean x;

    EngineJob(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener, Pools.Pool<EngineJob<?>> pool) {
        this(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, engineJobListener, pool, e);
    }

    @VisibleForTesting
    EngineJob(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener, Pools.Pool<EngineJob<?>> pool, EngineResourceFactory engineResourceFactory) {
        this.f4873a = new ResourceCallbacksAndExecutors();
        this.f = StateVerifier.a();
        this.n = new AtomicInteger();
        this.j = glideExecutor;
        this.k = glideExecutor2;
        this.l = glideExecutor3;
        this.m = glideExecutor4;
        this.i = engineJobListener;
        this.g = pool;
        this.h = engineResourceFactory;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized EngineJob<R> a(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
        this.o = key;
        this.p = z;
        this.q = z2;
        this.r = z3;
        this.s = z4;
        return this;
    }

    public synchronized void b(DecodeJob<R> decodeJob) {
        GlideExecutor glideExecutor;
        this.w = decodeJob;
        if (decodeJob.a()) {
            glideExecutor = this.j;
        } else {
            glideExecutor = h();
        }
        glideExecutor.execute(decodeJob);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(ResourceCallback resourceCallback, Executor executor) {
        this.f.b();
        this.f4873a.a(resourceCallback, executor);
        if (this.u) {
            a(1);
            executor.execute(new CallResourceReady(resourceCallback));
        } else if (this.v) {
            a(1);
            executor.execute(new CallLoadFailed(resourceCallback));
        } else {
            Preconditions.a(!this.x, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(ResourceCallback resourceCallback) {
        try {
            resourceCallback.a(this.d, this.b);
        } catch (Throwable th) {
            throw new CallbackException(th);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(ResourceCallback resourceCallback) {
        try {
            resourceCallback.a(this.c);
        } catch (Throwable th) {
            throw new CallbackException(th);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void c(ResourceCallback resourceCallback) {
        boolean z;
        this.f.b();
        this.f4873a.a(resourceCallback);
        if (this.f4873a.a()) {
            b();
            if (!this.u) {
                if (!this.v) {
                    z = false;
                    if (z && this.n.get() == 0) {
                        j();
                    }
                }
            }
            z = true;
            j();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.s;
    }

    private GlideExecutor h() {
        if (this.q) {
            return this.l;
        }
        return this.r ? this.m : this.k;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!i()) {
            this.x = true;
            this.w.b();
            this.i.a(this, this.o);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean c() {
        return this.x;
    }

    private boolean i() {
        return this.v || this.u || this.x;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r4.i.a(r4, r0, r2);
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        if (r0.hasNext() == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        r1 = r0.next();
        r1.b.execute(new com.bumptech.glide.load.engine.EngineJob.CallResourceReady(r4, r1.f4876a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0064, code lost:
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.bumptech.glide.util.pool.StateVerifier r0 = r4.f     // Catch:{ all -> 0x0078 }
            r0.b()     // Catch:{ all -> 0x0078 }
            boolean r0 = r4.x     // Catch:{ all -> 0x0078 }
            if (r0 == 0) goto L_0x0014
            com.bumptech.glide.load.engine.Resource<?> r0 = r4.t     // Catch:{ all -> 0x0078 }
            r0.f()     // Catch:{ all -> 0x0078 }
            r4.j()     // Catch:{ all -> 0x0078 }
            monitor-exit(r4)     // Catch:{ all -> 0x0078 }
            return
        L_0x0014:
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r0 = r4.f4873a     // Catch:{ all -> 0x0078 }
            boolean r0 = r0.a()     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x0070
            boolean r0 = r4.u     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x0068
            com.bumptech.glide.load.engine.EngineJob$EngineResourceFactory r0 = r4.h     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.Resource<?> r1 = r4.t     // Catch:{ all -> 0x0078 }
            boolean r2 = r4.p     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineResource r0 = r0.a(r1, r2)     // Catch:{ all -> 0x0078 }
            r4.d = r0     // Catch:{ all -> 0x0078 }
            r0 = 1
            r4.u = r0     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r1 = r4.f4873a     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r1 = r1.d()     // Catch:{ all -> 0x0078 }
            int r2 = r1.b()     // Catch:{ all -> 0x0078 }
            int r2 = r2 + r0
            r4.a((int) r2)     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.Key r0 = r4.o     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineResource<?> r2 = r4.d     // Catch:{ all -> 0x0078 }
            monitor-exit(r4)     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineJobListener r3 = r4.i
            r3.a(r4, r0, r2)
            java.util.Iterator r0 = r1.iterator()
        L_0x004b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0064
            java.lang.Object r1 = r0.next()
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbackAndExecutor r1 = (com.bumptech.glide.load.engine.EngineJob.ResourceCallbackAndExecutor) r1
            java.util.concurrent.Executor r2 = r1.b
            com.bumptech.glide.load.engine.EngineJob$CallResourceReady r3 = new com.bumptech.glide.load.engine.EngineJob$CallResourceReady
            com.bumptech.glide.request.ResourceCallback r1 = r1.f4876a
            r3.<init>(r1)
            r2.execute(r3)
            goto L_0x004b
        L_0x0064:
            r4.f()
            return
        L_0x0068:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = "Already have resource"
            r0.<init>(r1)     // Catch:{ all -> 0x0078 }
            throw r0     // Catch:{ all -> 0x0078 }
        L_0x0070:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = "Received a resource without any callbacks to notify"
            r0.<init>(r1)     // Catch:{ all -> 0x0078 }
            throw r0     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0078 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.EngineJob.e():void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(int i2) {
        Preconditions.a(i(), "Not yet complete!");
        if (this.n.getAndAdd(i2) == 0 && this.d != null) {
            this.d.g();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void f() {
        this.f.b();
        Preconditions.a(i(), "Not yet complete!");
        int decrementAndGet = this.n.decrementAndGet();
        Preconditions.a(decrementAndGet >= 0, "Can't decrement below 0");
        if (decrementAndGet == 0) {
            if (this.d != null) {
                this.d.h();
            }
            j();
        }
    }

    private synchronized void j() {
        if (this.o != null) {
            this.f4873a.c();
            this.o = null;
            this.d = null;
            this.t = null;
            this.v = false;
            this.x = false;
            this.u = false;
            this.w.a(false);
            this.w = null;
            this.c = null;
            this.b = null;
            this.g.release(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void a(Resource<R> resource, DataSource dataSource) {
        synchronized (this) {
            this.t = resource;
            this.b = dataSource;
        }
        e();
    }

    public void a(GlideException glideException) {
        synchronized (this) {
            this.c = glideException;
        }
        g();
    }

    public void a(DecodeJob<?> decodeJob) {
        h().execute(decodeJob);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        r4.i.a(r4, r1, (com.bumptech.glide.load.engine.EngineResource<?>) null);
        r0 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r0.hasNext() == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r1 = r0.next();
        r1.b.execute(new com.bumptech.glide.load.engine.EngineJob.CallLoadFailed(r4, r1.f4876a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.bumptech.glide.util.pool.StateVerifier r0 = r4.f     // Catch:{ all -> 0x0066 }
            r0.b()     // Catch:{ all -> 0x0066 }
            boolean r0 = r4.x     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x000f
            r4.j()     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)     // Catch:{ all -> 0x0066 }
            return
        L_0x000f:
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r0 = r4.f4873a     // Catch:{ all -> 0x0066 }
            boolean r0 = r0.a()     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x005e
            boolean r0 = r4.v     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0056
            r0 = 1
            r4.v = r0     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.Key r1 = r4.o     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r2 = r4.f4873a     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r2 = r2.d()     // Catch:{ all -> 0x0066 }
            int r3 = r2.b()     // Catch:{ all -> 0x0066 }
            int r3 = r3 + r0
            r4.a((int) r3)     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.engine.EngineJobListener r0 = r4.i
            r3 = 0
            r0.a(r4, r1, r3)
            java.util.Iterator r0 = r2.iterator()
        L_0x0039:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0052
            java.lang.Object r1 = r0.next()
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbackAndExecutor r1 = (com.bumptech.glide.load.engine.EngineJob.ResourceCallbackAndExecutor) r1
            java.util.concurrent.Executor r2 = r1.b
            com.bumptech.glide.load.engine.EngineJob$CallLoadFailed r3 = new com.bumptech.glide.load.engine.EngineJob$CallLoadFailed
            com.bumptech.glide.request.ResourceCallback r1 = r1.f4876a
            r3.<init>(r1)
            r2.execute(r3)
            goto L_0x0039
        L_0x0052:
            r4.f()
            return
        L_0x0056:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0066 }
            java.lang.String r1 = "Already failed once"
            r0.<init>(r1)     // Catch:{ all -> 0x0066 }
            throw r0     // Catch:{ all -> 0x0066 }
        L_0x005e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0066 }
            java.lang.String r1 = "Received an exception without any callbacks to notify"
            r0.<init>(r1)     // Catch:{ all -> 0x0066 }
            throw r0     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0066 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.EngineJob.g():void");
    }

    @NonNull
    public StateVerifier d_() {
        return this.f;
    }

    private class CallLoadFailed implements Runnable {
        private final ResourceCallback b;

        CallLoadFailed(ResourceCallback resourceCallback) {
            this.b = resourceCallback;
        }

        public void run() {
            synchronized (EngineJob.this) {
                if (EngineJob.this.f4873a.b(this.b)) {
                    EngineJob.this.b(this.b);
                }
                EngineJob.this.f();
            }
        }
    }

    private class CallResourceReady implements Runnable {
        private final ResourceCallback b;

        CallResourceReady(ResourceCallback resourceCallback) {
            this.b = resourceCallback;
        }

        public void run() {
            synchronized (EngineJob.this) {
                if (EngineJob.this.f4873a.b(this.b)) {
                    EngineJob.this.d.g();
                    EngineJob.this.a(this.b);
                    EngineJob.this.c(this.b);
                }
                EngineJob.this.f();
            }
        }
    }

    static final class ResourceCallbacksAndExecutors implements Iterable<ResourceCallbackAndExecutor> {

        /* renamed from: a  reason: collision with root package name */
        private final List<ResourceCallbackAndExecutor> f4877a;

        ResourceCallbacksAndExecutors() {
            this(new ArrayList(2));
        }

        ResourceCallbacksAndExecutors(List<ResourceCallbackAndExecutor> list) {
            this.f4877a = list;
        }

        /* access modifiers changed from: package-private */
        public void a(ResourceCallback resourceCallback, Executor executor) {
            this.f4877a.add(new ResourceCallbackAndExecutor(resourceCallback, executor));
        }

        /* access modifiers changed from: package-private */
        public void a(ResourceCallback resourceCallback) {
            this.f4877a.remove(c(resourceCallback));
        }

        /* access modifiers changed from: package-private */
        public boolean b(ResourceCallback resourceCallback) {
            return this.f4877a.contains(c(resourceCallback));
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.f4877a.isEmpty();
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.f4877a.size();
        }

        /* access modifiers changed from: package-private */
        public void c() {
            this.f4877a.clear();
        }

        /* access modifiers changed from: package-private */
        public ResourceCallbacksAndExecutors d() {
            return new ResourceCallbacksAndExecutors(new ArrayList(this.f4877a));
        }

        private static ResourceCallbackAndExecutor c(ResourceCallback resourceCallback) {
            return new ResourceCallbackAndExecutor(resourceCallback, Executors.b());
        }

        @NonNull
        public Iterator<ResourceCallbackAndExecutor> iterator() {
            return this.f4877a.iterator();
        }
    }

    static final class ResourceCallbackAndExecutor {

        /* renamed from: a  reason: collision with root package name */
        final ResourceCallback f4876a;
        final Executor b;

        ResourceCallbackAndExecutor(ResourceCallback resourceCallback, Executor executor) {
            this.f4876a = resourceCallback;
            this.b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ResourceCallbackAndExecutor) {
                return this.f4876a.equals(((ResourceCallbackAndExecutor) obj).f4876a);
            }
            return false;
        }

        public int hashCode() {
            return this.f4876a.hashCode();
        }
    }

    @VisibleForTesting
    static class EngineResourceFactory {
        EngineResourceFactory() {
        }

        public <R> EngineResource<R> a(Resource<R> resource, boolean z) {
            return new EngineResource<>(resource, z, true);
        }
    }
}
