package com.xiaomi.jr.richtext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;
import java.lang.ref.WeakReference;

public class CenteredImageSpan extends ImageSpan {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f11021a = false;
    private WeakReference<Drawable> b;
    private float c;

    public CenteredImageSpan(Drawable drawable, float f) {
        super(drawable, 1);
        this.c = f;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Rect bounds = a().getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return bounds.right;
    }

    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
        Drawable a2 = a();
        if (a2 != null) {
            canvas.save();
            float f2 = (float) i4;
            float f3 = (float) (i5 - i3);
            canvas.translate(f, (f2 - (((f2 - (f3 / 2.0f)) * ((float) a(this.c))) / f3)) - (((float) a2.getBounds().height()) / 2.0f));
            a2.draw(canvas);
            canvas.restore();
        }
    }

    private int a(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        return paint.getFontMetricsInt().bottom - paint.getFontMetricsInt().top;
    }

    private Drawable a() {
        WeakReference<Drawable> weakReference = this.b;
        Drawable drawable = weakReference != null ? (Drawable) weakReference.get() : null;
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = getDrawable();
        this.b = new WeakReference<>(drawable2);
        return drawable2;
    }
}
