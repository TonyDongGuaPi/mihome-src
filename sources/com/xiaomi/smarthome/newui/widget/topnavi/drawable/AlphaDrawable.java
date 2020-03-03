package com.xiaomi.smarthome.newui.widget.topnavi.drawable;

import android.animation.IntEvaluator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AlphaDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20918a = "AlphaDrawable";
    private List<AlphaRange> b = new ArrayList();
    private int c;
    private int d;
    private Path e = new Path();
    private int f;
    private Paint g = new Paint(1);
    private IntEvaluator h = new IntEvaluator();

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.clipPath(this.e);
        for (AlphaRange a2 : this.b) {
            a(canvas, a2);
        }
        canvas.restore();
    }

    private void a(Canvas canvas, AlphaRange alphaRange) {
        if (alphaRange.f20919a == alphaRange.b) {
            this.g.setAlpha(alphaRange.f20919a);
            canvas.drawRect(new Rect(alphaRange.e, 0, alphaRange.e + alphaRange.d, this.f), this.g);
            return;
        }
        for (int i = 0; i <= alphaRange.d; i++) {
            this.g.setAlpha(this.h.evaluate(((float) i) / ((float) alphaRange.d), Integer.valueOf(alphaRange.f20919a), Integer.valueOf(alphaRange.b)).intValue());
            canvas.drawRect((float) (alphaRange.e + i), 0.0f, (float) (alphaRange.e + i + 1), (float) this.f, this.g);
        }
    }

    public void setBounds(@NonNull Rect rect) {
        if (!rect.equals(getBounds())) {
            super.setBounds(rect);
            this.f = rect.height();
            int width = rect.width();
            int i = 0;
            for (AlphaRange next : this.b) {
                next.e = i;
                next.d = (int) (((float) width) * next.c);
                i += next.d;
            }
            this.e.reset();
            this.e.addRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) this.f), new float[]{(float) this.c, (float) this.c, (float) this.d, (float) this.d, (float) this.d, (float) this.d, (float) this.c, (float) this.c}, Path.Direction.CW);
            this.g.setStyle(Paint.Style.FILL);
        }
    }

    public AlphaDrawable a(@ColorInt int i) {
        this.g.setColor(-1);
        return this;
    }

    public AlphaDrawable a(int i, int i2) {
        this.c = i;
        this.d = i2;
        return this;
    }

    public AlphaDrawable a(AlphaRange alphaRange) {
        this.b.add(alphaRange);
        return this;
    }

    public static class AlphaRange {

        /* renamed from: a  reason: collision with root package name */
        int f20919a;
        int b;
        float c;
        int d;
        int e;

        public AlphaRange(int i, int i2, float f) {
            this.f20919a = i;
            this.b = i2;
            this.c = f;
        }
    }
}
