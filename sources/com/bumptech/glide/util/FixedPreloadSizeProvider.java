package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.ListPreloader;

public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f5100a;

    public FixedPreloadSizeProvider(int i, int i2) {
        this.f5100a = new int[]{i, i2};
    }

    @Nullable
    public int[] a(@NonNull T t, int i, int i2) {
        return this.f5100a;
    }
}
