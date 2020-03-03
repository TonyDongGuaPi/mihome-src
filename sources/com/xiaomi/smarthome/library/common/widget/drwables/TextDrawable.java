package com.xiaomi.smarthome.library.common.widget.drwables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class TextDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private final String f19022a;
    private final Paint b;
    private float c = 0.0f;
    private Paint.FontMetrics d;
    private Paint e;
    private int f = 0;

    public int getOpacity() {
        return -3;
    }

    public TextDrawable(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f19022a = str;
            this.b = new Paint();
            this.b.setColor(-1);
            this.b.setTextSize((float) DisplayUtils.a(this.f19022a.length() < 2 ? 11.0f : 10.0f));
            this.b.setAntiAlias(true);
            this.b.setFakeBoldText(true);
            this.b.setStyle(Paint.Style.FILL);
            this.b.setTextAlign(Paint.Align.LEFT);
            this.d = this.b.getFontMetrics();
            this.c = this.b.measureText(this.f19022a);
            return;
        }
        throw new IllegalArgumentException("text cannot be empty, please specify valid text");
    }

    public void a(int i) {
        this.b.setColor(i);
    }

    public void b(int i) {
        this.f = i;
    }

    public static Path a(float f2, float f3, float f4, float f5, float f6, float f7, boolean z) {
        Path path = new Path();
        if (f6 < 0.0f) {
            f6 = 0.0f;
        }
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        float f8 = f4 - f2;
        float f9 = f5 - f3;
        float f10 = f8 / 2.0f;
        if (f6 > f10) {
            f6 = f10;
        }
        float f11 = f9 / 2.0f;
        if (f7 > f11) {
            f7 = f11;
        }
        float f12 = f8 - (f6 * 2.0f);
        float f13 = f9 - (2.0f * f7);
        path.moveTo(f4, f3 + f7);
        float f14 = -f7;
        float f15 = -f6;
        path.rQuadTo(0.0f, f14, f15, f14);
        path.rLineTo(-f12, 0.0f);
        path.rQuadTo(f15, 0.0f, f15, f7);
        path.rLineTo(0.0f, f13);
        if (z) {
            path.rLineTo(0.0f, f7);
            path.rLineTo(f8, 0.0f);
            path.rLineTo(0.0f, f14);
        } else {
            path.rQuadTo(0.0f, f7, f6, f7);
            path.rLineTo(f12, 0.0f);
            path.rQuadTo(f6, 0.0f, f6, f14);
        }
        path.rLineTo(0.0f, -f13);
        path.close();
        return path;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (!(bounds == null || this.f == 0 || Build.VERSION.SDK_INT < 21)) {
            if (this.e == null) {
                this.e = new Paint();
                this.e.setColor(this.f);
                this.e.setAntiAlias(true);
            }
            Log.d("hzd1", "getBounds height=" + bounds.height());
            if (this.f19022a.length() <= 2) {
                canvas.drawOval((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.e);
            } else {
                float min = (float) Math.min(bounds.width() / 2, bounds.height() / 2);
                canvas.drawPath(a((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, min, min, false), this.e);
            }
        }
        canvas.drawText(this.f19022a, (((float) bounds.width()) - this.c) / 2.0f, (((((float) bounds.height()) - this.d.descent) - this.d.ascent) / 2.0f) + this.d.leading, this.b);
    }

    public void setAlpha(int i) {
        this.b.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
    }
}
