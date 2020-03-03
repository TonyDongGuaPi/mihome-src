package com.xiaomiyoupin.ypdimage.transformation;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;

public class FitXY extends BitmapTransformation {
    private static final String c = "com.xiaomiyoupin.ypdimage.transformation.FitXY";
    private static final byte[] d = c.getBytes(b);

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return YPDTransformationUtils.b(bitmapPool, bitmap, i, i2);
    }

    public boolean equals(Object obj) {
        return obj instanceof FitXY;
    }

    public int hashCode() {
        return Util.b(c.hashCode());
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(d);
    }
}
