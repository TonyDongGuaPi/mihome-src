package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

final class LockedResource<Z> implements Resource<Z>, FactoryPools.Poolable {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.Pool<LockedResource<?>> f4882a = FactoryPools.b(20, new FactoryPools.Factory<LockedResource<?>>() {
        /* renamed from: a */
        public LockedResource<?> b() {
            return new LockedResource<>();
        }
    });
    private final StateVerifier b = StateVerifier.a();
    private Resource<Z> c;
    private boolean d;
    private boolean e;

    @NonNull
    static <Z> LockedResource<Z> a(Resource<Z> resource) {
        LockedResource<Z> lockedResource = (LockedResource) Preconditions.a(f4882a.acquire());
        lockedResource.b(resource);
        return lockedResource;
    }

    LockedResource() {
    }

    private void b(Resource<Z> resource) {
        this.e = false;
        this.d = true;
        this.c = resource;
    }

    private void b() {
        this.c = null;
        f4882a.release(this);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        this.b.b();
        if (this.d) {
            this.d = false;
            if (this.e) {
                f();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }

    @NonNull
    public Class<Z> c() {
        return this.c.c();
    }

    @NonNull
    public Z d() {
        return this.c.d();
    }

    public int e() {
        return this.c.e();
    }

    public synchronized void f() {
        this.b.b();
        this.e = true;
        if (!this.d) {
            this.c.f();
            b();
        }
    }

    @NonNull
    public StateVerifier d_() {
        return this.b;
    }
}
