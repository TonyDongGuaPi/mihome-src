package com.xiaomi.smarthome.library.common.imagecache;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import com.facebook.imagepipeline.request.BasePostprocessor;

public class CircleColorLineProcessor extends BasePostprocessor {

    /* renamed from: a  reason: collision with root package name */
    private int f18624a;
    private int b;
    private Paint c = new Paint();

    public CircleColorLineProcessor(int i, int i2) {
        this.b = i;
        this.f18624a = i2;
        this.c.setColor(this.b);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth((float) i2);
        this.c.setStyle(Paint.Style.STROKE);
    }

    public void process(Bitmap bitmap, Bitmap bitmap2) {
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        Canvas canvas = new Canvas(bitmap);
        bitmap.setHasAlpha(true);
        bitmap.eraseColor(0);
        Path path = new Path();
        float f = (float) width;
        float f2 = (float) height;
        path.addCircle((f - 1.0f) / 2.0f, (f2 - 1.0f) / 2.0f, Math.min(f, f2) / 2.0f, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(0, 0, width, height), (Paint) null);
    }
}
