package com.xiaomi.smarthome.newui.onekey_delete;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;

public class PaperShredderView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20699a = 13;
    private static final int b = -1;
    private final Bitmap c;
    private final Bitmap d;
    private final Rect e;
    private final Rect f;
    private final Paint g;
    private final RectF h;
    private final RectF i;
    /* access modifiers changed from: private */
    public float j;
    /* access modifiers changed from: private */
    public float k;
    private ValueAnimator l;

    public PaperShredderView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PaperShredderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PaperShredderView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = BitmapFactory.decodeResource(getResources(), R.drawable.paper_shredder_back);
        this.d = BitmapFactory.decodeResource(getResources(), R.drawable.paper_shredder_front);
        this.e = new Rect(0, 0, this.c.getWidth(), this.c.getHeight());
        this.f = new Rect(0, 0, this.d.getWidth(), this.d.getHeight());
        this.g = new Paint();
        this.h = new RectF();
        this.i = new RectF();
        a();
    }

    private void a() {
        this.g.setAntiAlias(true);
        this.g.setColor(-1);
        this.g.setStyle(Paint.Style.FILL);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        this.g.setColor(-1);
        a(canvas);
        b(canvas);
        d(canvas);
        c(canvas);
        canvas.restore();
    }

    private void a(Canvas canvas) {
        canvas.drawBitmap(this.c, this.e, new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), (Paint) null);
    }

    private void b(Canvas canvas) {
        this.g.setColor(-1);
        this.g.setAlpha(255);
        int measuredWidth = getMeasuredWidth();
        float measuredHeight = (float) getMeasuredHeight();
        float f2 = ((this.k * measuredHeight) / 3.0f) * 0.8f;
        this.h.top = f2;
        this.h.bottom = (measuredHeight / 3.0f) + f2;
        float f3 = (float) (measuredWidth / 4);
        this.h.left = f3;
        this.h.right = ((float) measuredWidth) - f3;
        float f4 = (float) (measuredWidth / 50);
        canvas.drawRoundRect(this.h, f4, f4, this.g);
    }

    private void c(Canvas canvas) {
        float measuredHeight = (float) getMeasuredHeight();
        float f2 = measuredHeight / 2.0f;
        float f3 = (measuredHeight / 3.0f) / 2.0f;
        this.i.top = (f2 - f3) - 10.0f;
        this.i.bottom = f2 + f3;
        RectF rectF = this.i;
        float measuredWidth = (float) (getMeasuredWidth() / 20);
        rectF.left = measuredWidth;
        this.i.right = ((float) getMeasuredWidth()) - measuredWidth;
        this.g.setColor(Color.c);
        canvas.drawBitmap(this.d, this.f, this.i, this.g);
    }

    private void d(Canvas canvas) {
        Path path = new Path();
        float width = this.h.width() / 13.0f;
        float f2 = width / 7.0f;
        this.g.setColor(-1);
        this.g.setAlpha((int) ((1.0f - this.j) * 255.0f));
        int i2 = 0;
        while (i2 < 13) {
            path.reset();
            float f3 = ((float) i2) * width;
            path.moveTo(this.h.left + f3 + f2, this.h.bottom);
            i2++;
            float f4 = ((float) i2) * width;
            path.lineTo((this.h.left + f4) - f2, this.h.bottom);
            float height = ((this.h.height() * 2.0f) / 3.0f) + (this.h.height() / 3.0f);
            path.quadTo((this.h.left + f4) - f2, this.h.bottom, (this.h.left + f4) - f2, this.h.bottom + height);
            path.lineTo(this.h.left + f3 + f2, this.h.bottom + height);
            path.quadTo(this.h.left + f3 + f2, this.h.bottom, this.h.left + f3 + f2, this.h.bottom);
            path.close();
            canvas.drawPath(path, this.g);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int b2 = DisplayUtils.b(getContext(), 150.0f);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            setMeasuredDimension(b2, b2);
        } else if (mode == Integer.MIN_VALUE) {
            setMeasuredDimension(b2, size2);
        } else if (mode2 == Integer.MIN_VALUE) {
            setMeasuredDimension(size, b2);
        }
    }

    public void startAnim() {
        stopAnim();
        a(0.0f, 1.0f, 2000);
    }

    public void stopAnim() {
        if (this.l != null) {
            clearAnimation();
            this.l.setRepeatCount(0);
            this.l.cancel();
            this.l.end();
            this.j = 0.0f;
            postInvalidate();
        }
    }

    private ValueAnimator a(float f2, float f3, long j2) {
        this.l = ValueAnimator.ofFloat(new float[]{f2, f3});
        this.l.setDuration(j2);
        this.l.setInterpolator(new DecelerateInterpolator());
        this.l.setRepeatCount(-1);
        this.l.setRepeatMode(1);
        this.l.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = PaperShredderView.this.j = valueAnimator.getAnimatedFraction();
                float unused2 = PaperShredderView.this.k = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PaperShredderView.this.invalidate();
            }
        });
        if (!this.l.isRunning()) {
            this.l.start();
        }
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnim();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim();
    }
}
