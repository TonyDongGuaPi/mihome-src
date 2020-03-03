package com.xiaomi.smarthome.library.common.imagecache;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import com.squareup.picasso.Transformation;
import com.xiaomi.smarthome.library.common.util.CommonUtils;

public class CircleColorLineTransformation implements Transformation {

    /* renamed from: a  reason: collision with root package name */
    private int f18625a;
    private int b;
    private Paint c = new Paint();

    public String key() {
        return "circle";
    }

    public CircleColorLineTransformation(int i, int i2) {
        this.b = i;
        this.f18625a = i2;
        this.c.setColor(this.b);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth((float) i2);
        this.c.setStyle(Paint.Style.STROKE);
    }

    public Bitmap transform(Bitmap bitmap) {
        CommonUtils.a(bitmap != null && !bitmap.isRecycled());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (!bitmap.isMutable()) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            CommonUtils.a(createBitmap, bitmap);
            bitmap.recycle();
            bitmap = createBitmap;
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        Path path = new Path();
        float f = (float) width;
        float f2 = (float) height;
        path.addCircle((f - 1.0f) / 2.0f, (f2 - 1.0f) / 2.0f, Math.min(f, f2) / 2.0f, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, width, height), (Paint) null);
        bitmap.eraseColor(0);
        Canvas canvas2 = new Canvas(bitmap);
        canvas2.drawBitmap(createBitmap2, 0.0f, 0.0f, (Paint) null);
        canvas2.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getWidth() / 2), (float) (bitmap.getWidth() / 2), this.c);
        canvas2.save();
        return bitmap;
    }
}
