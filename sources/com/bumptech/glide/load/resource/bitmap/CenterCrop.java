package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class CenterCrop extends BitmapTransformation {
    private static final String c = "com.bumptech.glide.load.resource.bitmap.CenterCrop";
    private static final byte[] d = c.getBytes(b);

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return TransformationUtils.a(bitmapPool, bitmap, i, i2);
    }

    public boolean equals(Object obj) {
        return obj instanceof CenterCrop;
    }

    public int hashCode() {
        return c.hashCode();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(d);
    }
}
