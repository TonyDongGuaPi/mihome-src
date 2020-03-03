package com.xiaomi.base.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import java.security.MessageDigest;

public class CornerTransform implements Transformation<Bitmap> {
    private BitmapPool c;
    private float d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public void a(@NonNull MessageDigest messageDigest) {
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        this.e = z;
        this.f = z2;
        this.g = z3;
        this.h = z4;
    }

    public CornerTransform(Context context, float f2) {
        this.c = Glide.b(context).b();
        this.d = f2;
    }

    @NonNull
    public Resource<Bitmap> a(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i, int i2) {
        int i3;
        int i4;
        Bitmap d2 = resource.d();
        if (i > i2) {
            float f2 = (float) i2;
            float f3 = (float) i;
            i3 = d2.getWidth();
            i4 = (int) (((float) d2.getWidth()) * (f2 / f3));
            if (i4 > d2.getHeight()) {
                i4 = d2.getHeight();
                i3 = (int) (((float) d2.getHeight()) * (f3 / f2));
            }
        } else if (i < i2) {
            float f4 = (float) i;
            float f5 = (float) i2;
            int height = d2.getHeight();
            int height2 = (int) (((float) d2.getHeight()) * (f4 / f5));
            if (height2 > d2.getWidth()) {
                i3 = d2.getWidth();
                i4 = (int) (((float) d2.getWidth()) * (f5 / f4));
            } else {
                int i5 = height;
                i3 = height2;
                i4 = i5;
            }
        } else {
            i3 = d2.getHeight();
            i4 = i3;
        }
        this.d *= ((float) i4) / ((float) i2);
        Bitmap a2 = this.c.a(i3, i4, Bitmap.Config.ARGB_8888);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(a2);
        Paint paint = new Paint();
        paint.setColor(-65536);
        BitmapShader bitmapShader = new BitmapShader(d2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int width = (d2.getWidth() - i3) / 2;
        int height3 = (d2.getHeight() - i4) / 2;
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), this.d, this.d, paint);
        if (this.e) {
            canvas.drawRect(0.0f, 0.0f, this.d, this.d, paint);
        }
        if (this.f) {
            canvas.drawRect(((float) canvas.getWidth()) - this.d, 0.0f, (float) canvas.getWidth(), this.d, paint);
        }
        if (this.g) {
            canvas.drawRect(0.0f, ((float) canvas.getHeight()) - this.d, this.d, (float) canvas.getHeight(), paint);
        }
        if (this.h) {
            canvas.drawRect(((float) canvas.getWidth()) - this.d, ((float) canvas.getHeight()) - this.d, (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
        }
        return BitmapResource.a(a2, this.c);
    }
}
