package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class Rotate extends BitmapTransformation {
    private static final String c = "com.bumptech.glide.load.resource.bitmap.Rotate";
    private static final byte[] d = c.getBytes(b);
    private final int e;

    public Rotate(int i) {
        this.e = i;
    }

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return TransformationUtils.a(bitmap, this.e);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rotate) || this.e != ((Rotate) obj).e) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Util.b(c.hashCode(), Util.b(this.e));
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(d);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.e).array());
    }
}
