package com.bumptech.glide.load.resource.transcode;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;

public class UnitTranscoder<Z> implements ResourceTranscoder<Z, Z> {

    /* renamed from: a  reason: collision with root package name */
    private static final UnitTranscoder<?> f5035a = new UnitTranscoder<>();

    @Nullable
    public Resource<Z> a(@NonNull Resource<Z> resource, @NonNull Options options) {
        return resource;
    }

    public static <Z> ResourceTranscoder<Z, Z> a() {
        return f5035a;
    }
}
