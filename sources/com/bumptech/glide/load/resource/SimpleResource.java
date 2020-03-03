package com.bumptech.glide.load.resource;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class SimpleResource<T> implements Resource<T> {

    /* renamed from: a  reason: collision with root package name */
    protected final T f4990a;

    public final int e() {
        return 1;
    }

    public void f() {
    }

    public SimpleResource(@NonNull T t) {
        this.f4990a = Preconditions.a(t);
    }

    @NonNull
    public Class<T> c() {
        return this.f4990a.getClass();
    }

    @NonNull
    public final T d() {
        return this.f4990a;
    }
}
