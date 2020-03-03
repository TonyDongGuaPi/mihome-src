package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.Map;

public class Engine implements EngineJobListener, EngineResource.ResourceListener, MemoryCache.ResourceRemovedListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4866a = "Engine";
    private static final int b = 150;
    private static final boolean c = Log.isLoggable(f4866a, 2);
    private final Jobs d;
    private final EngineKeyFactory e;
    private final MemoryCache f;
    private final EngineJobFactory g;
    private final ResourceRecycler h;
    private final LazyDiskCacheProvider i;
    private final DecodeJobFactory j;
    private final ActiveResources k;

    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, boolean z) {
        this(memoryCache, factory, glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, (Jobs) null, (EngineKeyFactory) null, (ActiveResources) null, (EngineJobFactory) null, (DecodeJobFactory) null, (ResourceRecycler) null, z);
    }

    @VisibleForTesting
    Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, Jobs jobs, EngineKeyFactory engineKeyFactory, ActiveResources activeResources, EngineJobFactory engineJobFactory, DecodeJobFactory decodeJobFactory, ResourceRecycler resourceRecycler, boolean z) {
        this.f = memoryCache;
        DiskCache.Factory factory2 = factory;
        this.i = new LazyDiskCacheProvider(factory);
        ActiveResources activeResources2 = activeResources == null ? new ActiveResources(z) : activeResources;
        this.k = activeResources2;
        activeResources2.a((EngineResource.ResourceListener) this);
        this.e = engineKeyFactory == null ? new EngineKeyFactory() : engineKeyFactory;
        this.d = jobs == null ? new Jobs() : jobs;
        this.g = engineJobFactory == null ? new EngineJobFactory(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, this) : engineJobFactory;
        this.j = decodeJobFactory == null ? new DecodeJobFactory(this.i) : decodeJobFactory;
        this.h = resourceRecycler == null ? new ResourceRecycler() : resourceRecycler;
        memoryCache.a((MemoryCache.ResourceRemovedListener) this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <R> com.bumptech.glide.load.engine.Engine.LoadStatus a(com.bumptech.glide.GlideContext r31, java.lang.Object r32, com.bumptech.glide.load.Key r33, int r34, int r35, java.lang.Class<?> r36, java.lang.Class<R> r37, com.bumptech.glide.Priority r38, com.bumptech.glide.load.engine.DiskCacheStrategy r39, java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r40, boolean r41, boolean r42, com.bumptech.glide.load.Options r43, boolean r44, boolean r45, boolean r46, boolean r47, com.bumptech.glide.request.ResourceCallback r48, java.util.concurrent.Executor r49) {
        /*
            r30 = this;
            r1 = r30
            r0 = r44
            r8 = r48
            r9 = r49
            monitor-enter(r30)
            boolean r2 = c     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x0012
            long r2 = com.bumptech.glide.util.LogTime.a()     // Catch:{ all -> 0x00c5 }
            goto L_0x0014
        L_0x0012:
            r2 = 0
        L_0x0014:
            r10 = r2
            com.bumptech.glide.load.engine.EngineKeyFactory r12 = r1.e     // Catch:{ all -> 0x00c5 }
            r13 = r32
            r14 = r33
            r15 = r34
            r16 = r35
            r17 = r40
            r18 = r36
            r19 = r37
            r20 = r43
            com.bumptech.glide.load.engine.EngineKey r12 = r12.a(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x00c5 }
            com.bumptech.glide.load.engine.EngineResource r2 = r1.a((com.bumptech.glide.load.Key) r12, (boolean) r0)     // Catch:{ all -> 0x00c5 }
            r3 = 0
            if (r2 == 0) goto L_0x0042
            com.bumptech.glide.load.DataSource r0 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00c5 }
            r8.a(r2, r0)     // Catch:{ all -> 0x00c5 }
            boolean r0 = c     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x0040
            java.lang.String r0 = "Loaded resource from active resources"
            a((java.lang.String) r0, (long) r10, (com.bumptech.glide.load.Key) r12)     // Catch:{ all -> 0x00c5 }
        L_0x0040:
            monitor-exit(r30)
            return r3
        L_0x0042:
            com.bumptech.glide.load.engine.EngineResource r2 = r1.b(r12, r0)     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x0058
            com.bumptech.glide.load.DataSource r0 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00c5 }
            r8.a(r2, r0)     // Catch:{ all -> 0x00c5 }
            boolean r0 = c     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x0056
            java.lang.String r0 = "Loaded resource from cache"
            a((java.lang.String) r0, (long) r10, (com.bumptech.glide.load.Key) r12)     // Catch:{ all -> 0x00c5 }
        L_0x0056:
            monitor-exit(r30)
            return r3
        L_0x0058:
            com.bumptech.glide.load.engine.Jobs r2 = r1.d     // Catch:{ all -> 0x00c5 }
            r15 = r47
            com.bumptech.glide.load.engine.EngineJob r2 = r2.a((com.bumptech.glide.load.Key) r12, (boolean) r15)     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x0075
            r2.a((com.bumptech.glide.request.ResourceCallback) r8, (java.util.concurrent.Executor) r9)     // Catch:{ all -> 0x00c5 }
            boolean r0 = c     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x006e
            java.lang.String r0 = "Added to existing load"
            a((java.lang.String) r0, (long) r10, (com.bumptech.glide.load.Key) r12)     // Catch:{ all -> 0x00c5 }
        L_0x006e:
            com.bumptech.glide.load.engine.Engine$LoadStatus r0 = new com.bumptech.glide.load.engine.Engine$LoadStatus     // Catch:{ all -> 0x00c5 }
            r0.<init>(r8, r2)     // Catch:{ all -> 0x00c5 }
            monitor-exit(r30)
            return r0
        L_0x0075:
            com.bumptech.glide.load.engine.Engine$EngineJobFactory r2 = r1.g     // Catch:{ all -> 0x00c5 }
            r3 = r12
            r4 = r44
            r5 = r45
            r6 = r46
            r7 = r47
            com.bumptech.glide.load.engine.EngineJob r0 = r2.a(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00c5 }
            com.bumptech.glide.load.engine.Engine$DecodeJobFactory r13 = r1.j     // Catch:{ all -> 0x00c5 }
            r14 = r31
            r15 = r32
            r16 = r12
            r17 = r33
            r18 = r34
            r19 = r35
            r20 = r36
            r21 = r37
            r22 = r38
            r23 = r39
            r24 = r40
            r25 = r41
            r26 = r42
            r27 = r47
            r28 = r43
            r29 = r0
            com.bumptech.glide.load.engine.DecodeJob r2 = r13.a(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)     // Catch:{ all -> 0x00c5 }
            com.bumptech.glide.load.engine.Jobs r3 = r1.d     // Catch:{ all -> 0x00c5 }
            r3.a((com.bumptech.glide.load.Key) r12, (com.bumptech.glide.load.engine.EngineJob<?>) r0)     // Catch:{ all -> 0x00c5 }
            r0.a((com.bumptech.glide.request.ResourceCallback) r8, (java.util.concurrent.Executor) r9)     // Catch:{ all -> 0x00c5 }
            r0.b(r2)     // Catch:{ all -> 0x00c5 }
            boolean r2 = c     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x00be
            java.lang.String r2 = "Started new load"
            a((java.lang.String) r2, (long) r10, (com.bumptech.glide.load.Key) r12)     // Catch:{ all -> 0x00c5 }
        L_0x00be:
            com.bumptech.glide.load.engine.Engine$LoadStatus r2 = new com.bumptech.glide.load.engine.Engine$LoadStatus     // Catch:{ all -> 0x00c5 }
            r2.<init>(r8, r0)     // Catch:{ all -> 0x00c5 }
            monitor-exit(r30)
            return r2
        L_0x00c5:
            r0 = move-exception
            monitor-exit(r30)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.Engine.a(com.bumptech.glide.GlideContext, java.lang.Object, com.bumptech.glide.load.Key, int, int, java.lang.Class, java.lang.Class, com.bumptech.glide.Priority, com.bumptech.glide.load.engine.DiskCacheStrategy, java.util.Map, boolean, boolean, com.bumptech.glide.load.Options, boolean, boolean, boolean, boolean, com.bumptech.glide.request.ResourceCallback, java.util.concurrent.Executor):com.bumptech.glide.load.engine.Engine$LoadStatus");
    }

    private static void a(String str, long j2, Key key) {
        Log.v(f4866a, str + " in " + LogTime.a(j2) + "ms, key: " + key);
    }

    @Nullable
    private EngineResource<?> a(Key key, boolean z) {
        if (!z) {
            return null;
        }
        EngineResource<?> b2 = this.k.b(key);
        if (b2 != null) {
            b2.g();
        }
        return b2;
    }

    private EngineResource<?> b(Key key, boolean z) {
        if (!z) {
            return null;
        }
        EngineResource<?> a2 = a(key);
        if (a2 != null) {
            a2.g();
            this.k.a(key, a2);
        }
        return a2;
    }

    private EngineResource<?> a(Key key) {
        Resource<?> a2 = this.f.a(key);
        if (a2 == null) {
            return null;
        }
        if (a2 instanceof EngineResource) {
            return (EngineResource) a2;
        }
        return new EngineResource<>(a2, true, true);
    }

    public void a(Resource<?> resource) {
        if (resource instanceof EngineResource) {
            ((EngineResource) resource).h();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public synchronized void a(EngineJob<?> engineJob, Key key, EngineResource<?> engineResource) {
        if (engineResource != null) {
            try {
                engineResource.a(key, this);
                if (engineResource.b()) {
                    this.k.a(key, engineResource);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.d.b(key, engineJob);
    }

    public synchronized void a(EngineJob<?> engineJob, Key key) {
        this.d.b(key, engineJob);
    }

    public void b(@NonNull Resource<?> resource) {
        this.h.a(resource);
    }

    public synchronized void a(Key key, EngineResource<?> engineResource) {
        this.k.a(key);
        if (engineResource.b()) {
            this.f.b(key, engineResource);
        } else {
            this.h.a(engineResource);
        }
    }

    public void a() {
        this.i.a().a();
    }

    @VisibleForTesting
    public void b() {
        this.g.a();
        this.i.b();
        this.k.b();
    }

    public class LoadStatus {
        private final EngineJob<?> b;
        private final ResourceCallback c;

        LoadStatus(ResourceCallback resourceCallback, EngineJob<?> engineJob) {
            this.c = resourceCallback;
            this.b = engineJob;
        }

        public void a() {
            synchronized (Engine.this) {
                this.b.c(this.c);
            }
        }
    }

    private static class LazyDiskCacheProvider implements DecodeJob.DiskCacheProvider {

        /* renamed from: a  reason: collision with root package name */
        private final DiskCache.Factory f4871a;
        private volatile DiskCache b;

        LazyDiskCacheProvider(DiskCache.Factory factory) {
            this.f4871a = factory;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public synchronized void b() {
            if (this.b != null) {
                this.b.a();
            }
        }

        public DiskCache a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.f4871a.a();
                    }
                    if (this.b == null) {
                        this.b = new DiskCacheAdapter();
                    }
                }
            }
            return this.b;
        }
    }

    @VisibleForTesting
    static class DecodeJobFactory {

        /* renamed from: a  reason: collision with root package name */
        final DecodeJob.DiskCacheProvider f4867a;
        final Pools.Pool<DecodeJob<?>> b = FactoryPools.b(150, new FactoryPools.Factory<DecodeJob<?>>() {
            /* renamed from: a */
            public DecodeJob<?> b() {
                return new DecodeJob<>(DecodeJobFactory.this.f4867a, DecodeJobFactory.this.b);
            }
        });
        private int c;

        DecodeJobFactory(DecodeJob.DiskCacheProvider diskCacheProvider) {
            this.f4867a = diskCacheProvider;
        }

        /* access modifiers changed from: package-private */
        public <R> DecodeJob<R> a(GlideContext glideContext, Object obj, EngineKey engineKey, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options, DecodeJob.Callback<R> callback) {
            DecodeJob decodeJob = (DecodeJob) Preconditions.a(this.b.acquire());
            int i3 = this.c;
            int i4 = i3;
            this.c = i3 + 1;
            return decodeJob.a(glideContext, obj, engineKey, key, i, i2, cls, cls2, priority, diskCacheStrategy, map, z, z2, z3, options, callback, i4);
        }
    }

    @VisibleForTesting
    static class EngineJobFactory {

        /* renamed from: a  reason: collision with root package name */
        final GlideExecutor f4869a;
        final GlideExecutor b;
        final GlideExecutor c;
        final GlideExecutor d;
        final EngineJobListener e;
        final Pools.Pool<EngineJob<?>> f = FactoryPools.b(150, new FactoryPools.Factory<EngineJob<?>>() {
            /* renamed from: a */
            public EngineJob<?> b() {
                return new EngineJob(EngineJobFactory.this.f4869a, EngineJobFactory.this.b, EngineJobFactory.this.c, EngineJobFactory.this.d, EngineJobFactory.this.e, EngineJobFactory.this.f);
            }
        });

        EngineJobFactory(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener) {
            this.f4869a = glideExecutor;
            this.b = glideExecutor2;
            this.c = glideExecutor3;
            this.d = glideExecutor4;
            this.e = engineJobListener;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public void a() {
            Executors.a(this.f4869a);
            Executors.a(this.b);
            Executors.a(this.c);
            Executors.a(this.d);
        }

        /* access modifiers changed from: package-private */
        public <R> EngineJob<R> a(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((EngineJob) Preconditions.a(this.f.acquire())).a(key, z, z2, z3, z4);
        }
    }
}
