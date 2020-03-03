package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;

public abstract class CustomTarget<T> implements Target<T> {

    /* renamed from: a  reason: collision with root package name */
    private final int f5065a;
    private final int b;
    @Nullable
    private Request d;

    public void b(@Nullable Drawable drawable) {
    }

    public final void b(@NonNull SizeReadyCallback sizeReadyCallback) {
    }

    public void c(@Nullable Drawable drawable) {
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public CustomTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public CustomTarget(int i, int i2) {
        if (Util.a(i, i2)) {
            this.f5065a = i;
            this.b = i2;
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + i + " and height: " + i2);
    }

    public final void a(@NonNull SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.a(this.f5065a, this.b);
    }

    public final void a(@Nullable Request request) {
        this.d = request;
    }

    @Nullable
    public final Request a() {
        return this.d;
    }
}
