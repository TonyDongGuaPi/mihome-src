package com.bumptech.glide.load.engine;

import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class ActiveResources {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final Map<Key, ResourceWeakReference> f4852a;
    private final boolean b;
    private final Executor c;
    private final ReferenceQueue<EngineResource<?>> d;
    private EngineResource.ResourceListener e;
    private volatile boolean f;
    @Nullable
    private volatile DequeuedResourceCallback g;

    @VisibleForTesting
    interface DequeuedResourceCallback {
        void a();
    }

    ActiveResources(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(@NonNull final Runnable runnable) {
                return new Thread(new Runnable() {
                    public void run() {
                        Process.setThreadPriority(10);
                        runnable.run();
                    }
                }, "glide-active-resources");
            }
        }));
    }

    @VisibleForTesting
    ActiveResources(boolean z, Executor executor) {
        this.f4852a = new HashMap();
        this.d = new ReferenceQueue<>();
        this.b = z;
        this.c = executor;
        executor.execute(new Runnable() {
            public void run() {
                ActiveResources.this.a();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(EngineResource.ResourceListener resourceListener) {
        synchronized (resourceListener) {
            synchronized (this) {
                this.e = resourceListener;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Key key, EngineResource<?> engineResource) {
        ResourceWeakReference put = this.f4852a.put(key, new ResourceWeakReference(key, engineResource, this.d, this.b));
        if (put != null) {
            put.a();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Key key) {
        ResourceWeakReference remove = this.f4852a.remove(key);
        if (remove != null) {
            remove.a();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        return r0;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.bumptech.glide.load.engine.EngineResource<?> b(com.bumptech.glide.load.Key r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map<com.bumptech.glide.load.Key, com.bumptech.glide.load.engine.ActiveResources$ResourceWeakReference> r0 = r1.f4852a     // Catch:{ all -> 0x001b }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x001b }
            com.bumptech.glide.load.engine.ActiveResources$ResourceWeakReference r2 = (com.bumptech.glide.load.engine.ActiveResources.ResourceWeakReference) r2     // Catch:{ all -> 0x001b }
            if (r2 != 0) goto L_0x000e
            r2 = 0
            monitor-exit(r1)
            return r2
        L_0x000e:
            java.lang.Object r0 = r2.get()     // Catch:{ all -> 0x001b }
            com.bumptech.glide.load.engine.EngineResource r0 = (com.bumptech.glide.load.engine.EngineResource) r0     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0019
            r1.a((com.bumptech.glide.load.engine.ActiveResources.ResourceWeakReference) r2)     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r1)
            return r0
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.ActiveResources.b(com.bumptech.glide.load.Key):com.bumptech.glide.load.engine.EngineResource");
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull ResourceWeakReference resourceWeakReference) {
        synchronized (this.e) {
            synchronized (this) {
                this.f4852a.remove(resourceWeakReference.f4855a);
                if (resourceWeakReference.b) {
                    if (resourceWeakReference.c != null) {
                        EngineResource engineResource = new EngineResource(resourceWeakReference.c, true, false);
                        engineResource.a(resourceWeakReference.f4855a, this.e);
                        this.e.a(resourceWeakReference.f4855a, engineResource);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        while (!this.f) {
            try {
                a((ResourceWeakReference) this.d.remove());
                DequeuedResourceCallback dequeuedResourceCallback = this.g;
                if (dequeuedResourceCallback != null) {
                    dequeuedResourceCallback.a();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(DequeuedResourceCallback dequeuedResourceCallback) {
        this.g = dequeuedResourceCallback;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void b() {
        this.f = true;
        if (this.c instanceof ExecutorService) {
            com.bumptech.glide.util.Executors.a((ExecutorService) this.c);
        }
    }

    @VisibleForTesting
    static final class ResourceWeakReference extends WeakReference<EngineResource<?>> {

        /* renamed from: a  reason: collision with root package name */
        final Key f4855a;
        final boolean b;
        @Nullable
        Resource<?> c;

        ResourceWeakReference(@NonNull Key key, @NonNull EngineResource<?> engineResource, @NonNull ReferenceQueue<? super EngineResource<?>> referenceQueue, boolean z) {
            super(engineResource, referenceQueue);
            this.f4855a = (Key) Preconditions.a(key);
            this.c = (!engineResource.b() || !z) ? null : (Resource) Preconditions.a(engineResource.a());
            this.b = engineResource.b();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.c = null;
            clear();
        }
    }
}
