package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.transition.Transition;

public interface Target<R> extends LifecycleListener {
    public static final int c = Integer.MIN_VALUE;

    @Nullable
    Request a();

    void a(@Nullable Drawable drawable);

    void a(@Nullable Request request);

    void a(@NonNull SizeReadyCallback sizeReadyCallback);

    void a(@NonNull R r, @Nullable Transition<? super R> transition);

    void b(@Nullable Drawable drawable);

    void b(@NonNull SizeReadyCallback sizeReadyCallback);

    void c(@Nullable Drawable drawable);
}
