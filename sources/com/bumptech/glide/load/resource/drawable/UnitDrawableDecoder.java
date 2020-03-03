package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

public class UnitDrawableDecoder implements ResourceDecoder<Drawable, Drawable> {
    public boolean a(@NonNull Drawable drawable, @NonNull Options options) {
        return true;
    }

    @Nullable
    public Resource<Drawable> a(@NonNull Drawable drawable, int i, int i2, @NonNull Options options) {
        return NonOwnedDrawableResource.a(drawable);
    }
}
