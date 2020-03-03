package com.xiaomiyoupin.ypdimage.transformation;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class RoundedEachCorner extends BitmapTransformation {
    private static final String c = "com.xiaomiyoupin.ypdimage.transformation.RoundedEachCorner";
    private static final byte[] d = c.getBytes(b);
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;

    public RoundedEachCorner() {
    }

    public RoundedEachCorner(int i2) {
        Preconditions.a(i2 >= 0, "roundingRadius must be greater than 0.");
        a(i2);
    }

    public void a(int i2) {
        this.e = i2;
        b(i2);
        c(i2);
        e(i2);
        d(i2);
    }

    public void b(int i2) {
        Preconditions.a(i2 >= 0, "roundingRadius must be greater than 0.");
        this.f = i2;
    }

    public void c(int i2) {
        Preconditions.a(i2 >= 0, "roundingRadius must be greater than 0.");
        this.g = i2;
    }

    public void d(int i2) {
        Preconditions.a(i2 >= 0, "roundingRadius must be greater than 0.");
        this.h = i2;
    }

    public void e(int i2) {
        Preconditions.a(i2 >= 0, "roundingRadius must be greater than 0.");
        this.i = i2;
    }

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i2, int i3) {
        return YPDTransformationUtils.a(bitmapPool, bitmap, this.f, this.g, this.i, this.h);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RoundedEachCorner)) {
            return false;
        }
        RoundedEachCorner roundedEachCorner = (RoundedEachCorner) obj;
        if (this.e == roundedEachCorner.e && this.h == roundedEachCorner.h && this.i == roundedEachCorner.i && this.f == roundedEachCorner.f && this.g == roundedEachCorner.g) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Util.b(c.hashCode(), Util.b(this.i, Util.b(this.h, Util.b(this.g, Util.b(this.f, Util.b(this.e))))));
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(d);
        messageDigest.update(ByteBuffer.allocate(4).putInt(Util.b(this.i, Util.b(this.h, Util.b(this.g, Util.b(this.f, Util.b(this.e)))))).array());
    }
}
