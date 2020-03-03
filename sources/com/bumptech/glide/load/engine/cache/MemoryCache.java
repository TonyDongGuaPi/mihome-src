package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;

public interface MemoryCache {

    public interface ResourceRemovedListener {
        void b(@NonNull Resource<?> resource);
    }

    long a();

    @Nullable
    Resource<?> a(@NonNull Key key);

    void a(float f);

    void a(int i);

    void a(@NonNull ResourceRemovedListener resourceRemovedListener);

    long b();

    @Nullable
    Resource<?> b(@NonNull Key key, @Nullable Resource<?> resource);

    void c();
}
