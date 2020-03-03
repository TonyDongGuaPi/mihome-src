package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.File;

public class BitmapDrawableEncoder implements ResourceEncoder<BitmapDrawable> {

    /* renamed from: a  reason: collision with root package name */
    private final BitmapPool f4992a;
    private final ResourceEncoder<Bitmap> b;

    public BitmapDrawableEncoder(BitmapPool bitmapPool, ResourceEncoder<Bitmap> resourceEncoder) {
        this.f4992a = bitmapPool;
        this.b = resourceEncoder;
    }

    public boolean a(@NonNull Resource<BitmapDrawable> resource, @NonNull File file, @NonNull Options options) {
        return this.b.a(new BitmapResource(resource.d().getBitmap(), this.f4992a), file, options);
    }

    @NonNull
    public EncodeStrategy a(@NonNull Options options) {
        return this.b.a(options);
    }
}
