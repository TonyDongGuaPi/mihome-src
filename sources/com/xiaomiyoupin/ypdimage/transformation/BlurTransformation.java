package com.xiaomiyoupin.ypdimage.transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.taobao.weex.el.parse.Operators;
import com.xiaomiyoupin.ypdimage.transformation.internal.FastBlur;
import java.security.MessageDigest;

public class BlurTransformation extends BitmapTransformation {
    private static final int c = 1;
    private static final String d = "com.xiaomiyoupin.ypdimage.transformation.1";
    private static int e = 25;
    private static int f = 1;
    private int g;
    private int h;

    public BlurTransformation() {
        this(e, f);
    }

    public BlurTransformation(int i) {
        this(i, f);
    }

    public BlurTransformation(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        Bitmap a2 = bitmapPool.a(bitmap.getWidth() / this.h, bitmap.getHeight() / this.h, Bitmap.Config.ARGB_8888);
        a(bitmap, a2);
        Canvas canvas = new Canvas(a2);
        canvas.scale(1.0f / ((float) this.h), 1.0f / ((float) this.h));
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return FastBlur.a(a2, this.g, true);
    }

    public String toString() {
        return "BlurTransformation(radius=" + this.g + ", sampling=" + this.h + Operators.BRACKET_END_STR;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BlurTransformation) {
            BlurTransformation blurTransformation = (BlurTransformation) obj;
            return blurTransformation.g == this.g && blurTransformation.h == this.h;
        }
    }

    public int hashCode() {
        return d.hashCode() + (this.g * 1000) + (this.h * 10);
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update((d + this.g + this.h).getBytes(b));
    }
}
