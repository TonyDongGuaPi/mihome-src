package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;

class EngineResource<Z> implements Resource<Z> {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f4878a;
    private final boolean b;
    private final Resource<Z> c;
    private ResourceListener d;
    private Key e;
    private int f;
    private boolean g;

    interface ResourceListener {
        void a(Key key, EngineResource<?> engineResource);
    }

    EngineResource(Resource<Z> resource, boolean z, boolean z2) {
        this.c = (Resource) Preconditions.a(resource);
        this.f4878a = z;
        this.b = z2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Key key, ResourceListener resourceListener) {
        this.e = key;
        this.d = resourceListener;
    }

    /* access modifiers changed from: package-private */
    public Resource<Z> a() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f4878a;
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
        if (this.f > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (!this.g) {
            this.g = true;
            if (this.b) {
                this.c.f();
            }
        } else {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void g() {
        if (!this.g) {
            this.f++;
        } else {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        synchronized (this.d) {
            synchronized (this) {
                if (this.f > 0) {
                    int i = this.f - 1;
                    this.f = i;
                    if (i == 0) {
                        this.d.a(this.e, this);
                    }
                } else {
                    throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
                }
            }
        }
    }

    public synchronized String toString() {
        return "EngineResource{isCacheable=" + this.f4878a + ", listener=" + this.d + ", key=" + this.e + ", acquired=" + this.f + ", isRecycled=" + this.g + ", resource=" + this.c + Operators.BLOCK_END;
    }
}
