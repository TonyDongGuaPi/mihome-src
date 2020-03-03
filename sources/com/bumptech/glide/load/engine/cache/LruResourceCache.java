package com.bumptech.glide.load.engine.cache;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.util.LruCache;

public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private MemoryCache.ResourceRemovedListener f4913a;

    @Nullable
    public /* synthetic */ Resource a(@NonNull Key key) {
        return (Resource) super.d(key);
    }

    @Nullable
    public /* bridge */ /* synthetic */ Resource b(@NonNull Key key, @Nullable Resource resource) {
        return (Resource) super.b(key, resource);
    }

    public LruResourceCache(long j) {
        super(j);
    }

    public void a(@NonNull MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.f4913a = resourceRemovedListener;
    }

    /* access modifiers changed from: protected */
    public void a(@NonNull Key key, @Nullable Resource<?> resource) {
        if (this.f4913a != null && resource != null) {
            this.f4913a.b(resource);
        }
    }

    /* access modifiers changed from: protected */
    public int a(@Nullable Resource<?> resource) {
        if (resource == null) {
            return super.a(null);
        }
        return resource.e();
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 40) {
            c();
        } else if (i >= 20 || i == 15) {
            a(b() / 2);
        }
    }
}
