package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;

public interface ModelLoaderFactory<T, Y> {
    @NonNull
    ModelLoader<T, Y> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory);

    void a();
}
