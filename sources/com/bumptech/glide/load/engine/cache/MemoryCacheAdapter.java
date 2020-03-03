package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;

public class MemoryCacheAdapter implements MemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private MemoryCache.ResourceRemovedListener f4914a;

    public long a() {
        return 0;
    }

    @Nullable
    public Resource<?> a(@NonNull Key key) {
        return null;
    }

    public void a(float f) {
    }

    public void a(int i) {
    }

    public long b() {
        return 0;
    }

    public void c() {
    }

    @Nullable
    public Resource<?> b(@NonNull Key key, @Nullable Resource<?> resource) {
        if (resource == null) {
            return null;
        }
        this.f4914a.b(resource);
        return null;
    }

    public void a(@NonNull MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.f4914a = resourceRemovedListener;
    }
}
