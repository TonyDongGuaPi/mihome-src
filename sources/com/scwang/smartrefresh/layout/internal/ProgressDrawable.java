package com.scwang.smartrefresh.layout.internal;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

public class ProgressDrawable extends PaintDrawable implements ValueAnimator.AnimatorUpdateListener, Animatable {

    /* renamed from: a  reason: collision with root package name */
    protected int f8799a = 0;
    protected int b = 0;
    protected int c = 0;
    protected ValueAnimator d = ValueAnimator.ofInt(new int[]{30, 3600});
    protected Path e = new Path();

    public ProgressDrawable() {
        this.d.setDuration(10000);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.setRepeatCount(-1);
        this.d.setRepeatMode(1);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.c = (((Integer) valueAnimator.getAnimatedValue()).intValue() / 30) * 30;
        invalidateSelf();
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int max = Math.max(1, width / 20);
        if (!(this.f8799a == width && this.b == height)) {
            this.e.reset();
            float f = (float) (width - max);
            int i = height / 2;
            float f2 = (float) i;
            float f3 = (float) max;
            this.e.addCircle(f, f2, f3, Path.Direction.CW);
            float f4 = (float) (width - (max * 5));
            this.e.addRect(f4, (float) (i - max), f, (float) (i + max), Path.Direction.CW);
            this.e.addCircle(f4, f2, f3, Path.Direction.CW);
            this.f8799a = width;
            this.b = height;
        }
        canvas.save();
        float f5 = (float) (width / 2);
        float f6 = (float) (height / 2);
        canvas.rotate((float) this.c, f5, f6);
        for (int i2 = 0; i2 < 12; i2++) {
            this.m.setAlpha((i2 + 5) * 17);
            canvas.rotate(30.0f, f5, f6);
            canvas.drawPath(this.e, this.m);
        }
        canvas.restore();
    }

    public void start() {
        if (!this.d.isRunning()) {
            this.d.addUpdateListener(this);
            this.d.start();
        }
    }

    public void stop() {
        if (this.d.isRunning()) {
            this.d.removeAllListeners();
            this.d.removeAllUpdateListeners();
            this.d.cancel();
        }
    }

    public boolean isRunning() {
        return this.d.isRunning();
    }
}
