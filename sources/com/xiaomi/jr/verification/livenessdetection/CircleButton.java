package com.xiaomi.jr.verification.livenessdetection;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.xiaomi.jr.verification.R;

public class CircleButton extends View {
    public static final int STATE_FAILED = 3;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_ROTATE = 1;
    public static final int STATE_SUCCESS = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f11047a = "CircleButton";
    private int b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private final Paint h;
    private int i;
    private int j;
    private ObjectAnimator k;
    float mDegress;

    public CircleButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public CircleButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = 0;
        this.j = 2000;
        this.mDegress = 0.0f;
        this.k = null;
        this.c = BitmapFactory.decodeResource(context.getResources(), R.drawable.scan_first_btn_normal);
        this.d = BitmapFactory.decodeResource(context.getResources(), R.drawable.scan_first_btn_high_light);
        this.e = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_success);
        this.f = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_failed);
        this.g = BitmapFactory.decodeResource(context.getResources(), R.drawable.pointer);
        this.h = new Paint();
        this.h.setColor(getResources().getColor(R.color.bg_arc_color));
        this.h.setAlpha(125);
        this.h.setStrokeJoin(Paint.Join.ROUND);
        this.h.setStrokeCap(Paint.Cap.ROUND);
        this.h.setStrokeWidth(3.0f);
        this.h.setAntiAlias(true);
        this.b = context.getResources().getDimensionPixelOffset(R.dimen.arc_distance);
    }

    public CircleButton setDuration(int i2) {
        this.j = i2;
        return this;
    }

    public CircleButton setNormalAndHighLightResId(int i2, int i3) {
        this.c = BitmapFactory.decodeResource(getResources(), i2);
        this.d = BitmapFactory.decodeResource(getResources(), i3);
        invalidate();
        return this;
    }

    public void setState(int i2) {
        this.i = i2;
        if (i2 == 1) {
            startRatoteAnimation();
        } else {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(this.c.getWidth(), this.c.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        switch (this.i) {
            case 0:
                c(canvas);
                return;
            case 1:
                d(canvas);
                return;
            case 2:
                b(canvas);
                return;
            case 3:
                a(canvas);
                return;
            default:
                return;
        }
    }

    private void a(Canvas canvas) {
        canvas.drawBitmap(this.f, 0.0f, 0.0f, (Paint) null);
    }

    private void b(Canvas canvas) {
        canvas.drawBitmap(this.e, 0.0f, 0.0f, (Paint) null);
    }

    private void c(Canvas canvas) {
        canvas.drawBitmap(this.c, 0.0f, 0.0f, (Paint) null);
    }

    private void d(Canvas canvas) {
        canvas.drawBitmap(this.d, 0.0f, 0.0f, (Paint) null);
        int width = this.d.getWidth();
        int height = this.d.getHeight();
        canvas.drawArc(new RectF((float) this.b, (float) this.b, (float) (width - this.b), (float) (height - this.b)), -90.0f, this.mDegress, true, this.h);
        canvas.save();
        float f2 = (float) (width / 2);
        float f3 = (float) (height / 2);
        canvas.rotate(this.mDegress - 90.0f, f2, f3);
        canvas.drawBitmap(this.g, f2, f3, (Paint) null);
        canvas.restore();
    }

    public float getDegress() {
        return this.mDegress;
    }

    public void setDegress(float f2) {
        this.mDegress = f2;
        invalidate();
    }

    public void startRatoteAnimation() {
        this.i = 1;
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
        this.k = ObjectAnimator.ofFloat(this, "Degress", new float[]{0.0f, 360.0f});
        this.k.setDuration((long) this.j);
        this.k.setInterpolator(new LinearInterpolator());
        this.k.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        });
        this.k.start();
    }
}
