package com.xiaomi.smarthome.newui.widget.guide.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.xiaomi.smarthome.module.blur.StackBlurManager;

public class BlurBackgroundDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private Paint f20905a;
    private Bitmap b;
    private Bitmap c;
    private Xfermode d;
    private PorterDuff.Mode e = PorterDuff.Mode.SRC_IN;
    private Rect f;
    private RectF g;
    private PointF h;

    public int getOpacity() {
        return -3;
    }

    public BlurBackgroundDrawable(Bitmap bitmap) {
        this.b = new StackBlurManager().a(bitmap, 10);
        this.f20905a = new Paint(3);
        this.d = new PorterDuffXfermode(this.e);
        this.f = new Rect();
        this.g = new RectF();
    }

    public void a(PointF pointF) {
        this.h = pointF;
    }

    public void a(Drawable drawable, PointF pointF) {
        this.c = a(drawable);
        this.h = pointF;
        invalidateSelf();
    }

    public static Bitmap a(Drawable drawable) {
        drawable.setAlpha(255);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public void draw(@NonNull Canvas canvas) {
        this.f.set(0, 0, canvas.getWidth(), canvas.getHeight());
        int saveLayer = canvas.saveLayer(new RectF(this.f), this.f20905a, 31);
        if (!(this.h == null || this.c == null)) {
            float f2 = this.h.x;
            float f3 = this.h.y;
            this.g.set(f2, f3, ((float) this.c.getWidth()) + f2, ((float) this.c.getHeight()) + f3);
            canvas.drawBitmap(this.c, (Rect) null, this.g, this.f20905a);
        }
        this.f20905a.setXfermode(this.d);
        if (this.b != null && !this.b.isRecycled()) {
            canvas.drawBitmap(this.b, (Rect) null, this.f, this.f20905a);
        }
        this.f20905a.setXfermode((Xfermode) null);
        canvas.restoreToCount(saveLayer);
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        this.f20905a.setAlpha(i);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f20905a.setColorFilter(colorFilter);
    }
}
