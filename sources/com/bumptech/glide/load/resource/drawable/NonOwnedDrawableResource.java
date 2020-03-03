package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;

final class NonOwnedDrawableResource extends DrawableResource<Drawable> {
    public void f() {
    }

    @Nullable
    static Resource<Drawable> a(@Nullable Drawable drawable) {
        if (drawable != null) {
            return new NonOwnedDrawableResource(drawable);
        }
        return null;
    }

    private NonOwnedDrawableResource(Drawable drawable) {
        super(drawable);
    }

    @NonNull
    public Class<Drawable> c() {
        return this.f5016a.getClass();
    }

    public int e() {
        return Math.max(1, this.f5016a.getIntrinsicWidth() * this.f5016a.getIntrinsicHeight() * 4);
    }
}
