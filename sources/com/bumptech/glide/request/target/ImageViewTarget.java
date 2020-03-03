package com.bumptech.glide.request.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.request.transition.Transition;

public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements Transition.ViewAdapter {
    @Nullable
    private Animatable b;

    /* access modifiers changed from: protected */
    public abstract void a(@Nullable Z z);

    public ImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public ImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Nullable
    public Drawable b() {
        return ((ImageView) this.f5075a).getDrawable();
    }

    public void e(Drawable drawable) {
        ((ImageView) this.f5075a).setImageDrawable(drawable);
    }

    public void b(@Nullable Drawable drawable) {
        super.b(drawable);
        b((Object) null);
        e(drawable);
    }

    public void c(@Nullable Drawable drawable) {
        super.c(drawable);
        b((Object) null);
        e(drawable);
    }

    public void a(@Nullable Drawable drawable) {
        super.a(drawable);
        if (this.b != null) {
            this.b.stop();
        }
        b((Object) null);
        e(drawable);
    }

    public void a(@NonNull Z z, @Nullable Transition<? super Z> transition) {
        if (transition == null || !transition.a(z, this)) {
            b(z);
        } else {
            c(z);
        }
    }

    public void g() {
        if (this.b != null) {
            this.b.start();
        }
    }

    public void h() {
        if (this.b != null) {
            this.b.stop();
        }
    }

    private void b(@Nullable Z z) {
        a(z);
        c(z);
    }

    private void c(@Nullable Z z) {
        if (z instanceof Animatable) {
            this.b = (Animatable) z;
            this.b.start();
            return;
        }
        this.b = null;
    }
}
