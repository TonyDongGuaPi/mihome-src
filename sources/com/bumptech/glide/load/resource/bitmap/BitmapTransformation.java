package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public abstract class BitmapTransformation implements Transformation<Bitmap> {
    /* access modifiers changed from: protected */
    public abstract Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2);

    @NonNull
    public final Resource<Bitmap> a(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i, int i2) {
        if (Util.a(i, i2)) {
            BitmapPool b = Glide.b(context).b();
            Bitmap d = resource.d();
            if (i == Integer.MIN_VALUE) {
                i = d.getWidth();
            }
            if (i2 == Integer.MIN_VALUE) {
                i2 = d.getHeight();
            }
            Bitmap a2 = a(b, d, i, i2);
            if (d.equals(a2)) {
                return resource;
            }
            return BitmapResource.a(a2, b);
        }
        throw new IllegalArgumentException("Cannot apply transformation on width: " + i + " or height: " + i2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
    }
}
