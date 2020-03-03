package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.facebook.common.util.UriUtil;

public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final ResourceDrawableDecoder f5005a;
    private final BitmapPool b;

    public ResourceBitmapDecoder(ResourceDrawableDecoder resourceDrawableDecoder, BitmapPool bitmapPool) {
        this.f5005a = resourceDrawableDecoder;
        this.b = bitmapPool;
    }

    public boolean a(@NonNull Uri uri, @NonNull Options options) {
        return UriUtil.QUALIFIED_RESOURCE_SCHEME.equals(uri.getScheme());
    }

    @Nullable
    public Resource<Bitmap> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        Resource<Drawable> a2 = this.f5005a.a(uri, i, i2, options);
        if (a2 == null) {
            return null;
        }
        return DrawableToBitmapConverter.a(this.b, a2.d(), i, i2);
    }
}
