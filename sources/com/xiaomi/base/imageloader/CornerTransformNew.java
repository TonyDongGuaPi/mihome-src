package com.xiaomi.base.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class CornerTransformNew extends BitmapTransformation {
    private float c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;

    public void a(@NonNull MessageDigest messageDigest) {
    }

    public CornerTransformNew(float f2) {
        this.c = f2;
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = z4;
    }

    /* access modifiers changed from: protected */
    public Bitmap a(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        a(bitmap, i, i2);
        Bitmap a2 = bitmapPool.a(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        a2.setHasAlpha(true);
        Canvas canvas = new Canvas(a2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        a(canvas, paint);
        return a2;
    }

    private void a(Canvas canvas, Paint paint) {
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), this.c, this.c, paint);
        if (this.d) {
            canvas.drawRect(0.0f, 0.0f, this.c, this.c, paint);
        }
        if (this.e) {
            canvas.drawRect(((float) canvas.getWidth()) - this.c, 0.0f, (float) canvas.getWidth(), this.c, paint);
        }
        if (this.f) {
            canvas.drawRect(0.0f, ((float) canvas.getHeight()) - this.c, this.c, (float) canvas.getHeight(), paint);
        }
        if (this.g) {
            canvas.drawRect(((float) canvas.getWidth()) - this.c, ((float) canvas.getHeight()) - this.c, (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
        }
    }

    private void a(Bitmap bitmap, int i, int i2) {
        int i3;
        if (i > i2) {
            i3 = (int) (((float) bitmap.getWidth()) * (((float) i2) / ((float) i)));
            if (i3 > bitmap.getHeight()) {
                i3 = bitmap.getHeight();
            }
        } else if (i < i2) {
            float f2 = (float) i;
            float f3 = (float) i2;
            int height = bitmap.getHeight();
            if (((int) (((float) bitmap.getHeight()) * (f2 / f3))) > bitmap.getWidth()) {
                i3 = (int) (((float) bitmap.getWidth()) * (f3 / f2));
            } else {
                i3 = height;
            }
        } else {
            i3 = bitmap.getHeight();
        }
        this.c *= ((float) i3) / ((float) i2);
    }
}
