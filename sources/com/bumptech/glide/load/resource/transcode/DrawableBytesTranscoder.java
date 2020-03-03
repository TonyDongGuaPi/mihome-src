package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public final class DrawableBytesTranscoder implements ResourceTranscoder<Drawable, byte[]> {

    /* renamed from: a  reason: collision with root package name */
    private final BitmapPool f5032a;
    private final ResourceTranscoder<Bitmap, byte[]> b;
    private final ResourceTranscoder<GifDrawable, byte[]> c;

    @NonNull
    private static Resource<GifDrawable> a(@NonNull Resource<Drawable> resource) {
        return resource;
    }

    public DrawableBytesTranscoder(@NonNull BitmapPool bitmapPool, @NonNull ResourceTranscoder<Bitmap, byte[]> resourceTranscoder, @NonNull ResourceTranscoder<GifDrawable, byte[]> resourceTranscoder2) {
        this.f5032a = bitmapPool;
        this.b = resourceTranscoder;
        this.c = resourceTranscoder2;
    }

    @Nullable
    public Resource<byte[]> a(@NonNull Resource<Drawable> resource, @NonNull Options options) {
        Drawable d = resource.d();
        if (d instanceof BitmapDrawable) {
            return this.b.a(BitmapResource.a(((BitmapDrawable) d).getBitmap(), this.f5032a), options);
        }
        if (d instanceof GifDrawable) {
            return this.c.a(a(resource), options);
        }
        return null;
    }
}
