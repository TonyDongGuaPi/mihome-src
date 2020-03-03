package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public final class RoundedCorners extends BitmapTransformation {
    private static final String c = "com.bumptech.glide.load.resource.bitmap.RoundedCorners";
    private static final byte[] d = c.getBytes(b);
    private final int e;

    public RoundedCorners(int i) {
        Preconditions.a(i > 0, "roundingRadius must be greater than 0.");
        this.e = i;
    }

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return TransformationUtils.b(bitmapPool, bitmap, this.e);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RoundedCorners) || this.e != ((RoundedCorners) obj).e) {
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
