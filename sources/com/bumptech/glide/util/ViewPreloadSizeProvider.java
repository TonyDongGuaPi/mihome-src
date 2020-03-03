package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import java.util.Arrays;

public class ViewPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback {

    /* renamed from: a  reason: collision with root package name */
    private int[] f5107a;
    private SizeViewTarget b;

    public ViewPreloadSizeProvider() {
    }

    public ViewPreloadSizeProvider(@NonNull View view) {
        this.b = new SizeViewTarget(view, this);
    }

    @Nullable
    public int[] a(@NonNull T t, int i, int i2) {
        if (this.f5107a == null) {
            return null;
        }
        return Arrays.copyOf(this.f5107a, this.f5107a.length);
    }

    public void a(int i, int i2) {
        this.f5107a = new int[]{i, i2};
        this.b = null;
    }

    public void a(@NonNull View view) {
        if (this.f5107a == null && this.b == null) {
            this.b = new SizeViewTarget(view, this);
        }
    }

    private static final class SizeViewTarget extends ViewTarget<View, Object> {
        public void a(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }

        SizeViewTarget(@NonNull View view, @NonNull SizeReadyCallback sizeReadyCallback) {
            super(view);
            a(sizeReadyCallback);
        }
    }
}
