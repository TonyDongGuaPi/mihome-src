package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.Request;

@Deprecated
public abstract class BaseTarget<Z> implements Target<Z> {

    /* renamed from: a  reason: collision with root package name */
    private Request f5064a;

    public void a(@Nullable Drawable drawable) {
    }

    public void b(@Nullable Drawable drawable) {
    }

    public void c(@Nullable Drawable drawable) {
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public void a(@Nullable Request request) {
        this.f5064a = request;
    }

    @Nullable
    public Request a() {
        return this.f5064a;
    }
}
