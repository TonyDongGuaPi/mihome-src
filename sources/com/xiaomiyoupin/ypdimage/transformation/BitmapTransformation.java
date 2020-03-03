package com.xiaomiyoupin.ypdimage.transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;

abstract class BitmapTransformation implements Transformation<Bitmap> {
    /* access modifiers changed from: protected */
    public abstract Bitmap a(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2);

    public abstract void a(@NonNull MessageDigest messageDigest);

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    BitmapTransformation() {
    }

    @NonNull
    public final Resource<Bitmap> a(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i, int i2) {
        if (Util.a(i, i2)) {
            BitmapPool b = Glide.b(context).b();
            Bitmap d = resource.d();
            if (i == Integer.MIN_VALUE) {
                i = d.getWidth();
            }
            int i3 = i;
            if (i2 == Integer.MIN_VALUE) {
                i2 = d.getHeight();
            }
            Bitmap a2 = a(context.getApplicationContext(), b, d, i3, i2);
            if (d.equals(a2)) {
                return resource;
            }
            return BitmapResource.a(a2, b);
        }
        throw new IllegalArgumentException("Cannot apply transformation on width: " + i + " or height: " + i2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2) {
        bitmap2.setDensity(bitmap.getDensity());
    }
}
