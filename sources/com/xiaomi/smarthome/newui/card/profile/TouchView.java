package com.xiaomi.smarthome.newui.card.profile;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.xiaomi.smarthome.library.common.util.BitmapUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.profile.YeelightControlView;
import java.util.Timer;
import java.util.TimerTask;

public class TouchView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final float f20574a = 0.3f;
    private static final float b = 0.4f;
    private static final float c = 1.0f;
    private ControlMode d = ControlMode.SUNSHINE;
    private Matrix e;
    private Paint f;
    /* access modifiers changed from: private */
    public boolean g = false;
    private float h = 0.0f;
    private float i = 0.0f;
    private Bitmap j;
    /* access modifiers changed from: private */
    public int k = 255;
    private float l = 0.0f;
    /* access modifiers changed from: private */
    public float m = 0.0f;
    protected Context mContext;
    protected int mHeight;
    protected int mWidth;
    private ValueAnimator n = null;
    private ValueAnimator o = null;
    private Bitmap p;
    private boolean q = false;
    private float r = 0.0f;
    private float s = 0.0f;

    public enum ControlMode {
        SWITCH,
        COLOR,
        SUNSHINE,
        COLORFLOW,
        NIGHTLIGHT
    }

    public TouchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchView(Context context) {
        super(context);
    }

    public void setCurrentMode(ControlMode controlMode) {
        this.d = controlMode;
        invalidate();
    }

    public ControlMode getCurrentMode() {
        return this.d;
    }

    public void drawCircle(float f2, float f3) {
        this.h = f2;
        this.i = f3;
        this.k = 250;
        this.g = true;
        invalidate();
    }

    public void startDrawCircle() {
        this.m = f20574a;
        if (this.o != null) {
            this.o.cancel();
            this.o = null;
        }
        this.n = ValueAnimator.ofFloat(new float[]{0.0f, this.m});
        this.n.setDuration(300);
        this.n.setInterpolator(new DecelerateInterpolator());
        this.n.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = TouchView.this.m = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            }
        });
        this.n.start();
    }

    public void dismissCircle() {
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
        this.o = ValueAnimator.ofFloat(new float[]{this.m, 0.0f});
        this.o.setDuration(500);
        this.o.setInterpolator(new DecelerateInterpolator());
        this.o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = TouchView.this.m = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            }
        });
        this.o.start();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                int unused = TouchView.this.k = TouchView.this.k - 25;
                if (TouchView.this.k <= 5) {
                    boolean unused2 = TouchView.this.g = false;
                    cancel();
                    timer.cancel();
                }
            }
        }, 0, 30);
    }

    public void setCircleRadio(YeelightControlView.TouchDirection touchDirection) {
        switch (touchDirection) {
            case HORIZONTAL:
                this.m = f20574a;
                return;
            case VERTICAL:
                this.m = ((1.0f - (this.i / ((float) this.mHeight))) * 0.099999994f) + f20574a;
                return;
            default:
                return;
        }
    }

    public void recycleBitmap() {
        if (this.j != null) {
            this.j.recycle();
            this.j = null;
        }
        if (this.p != null) {
            this.p.recycle();
            this.p = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.mWidth = View.MeasureSpec.getSize(i2);
        this.mHeight = View.MeasureSpec.getSize(i3);
        a();
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a();
        switch (this.d) {
            case COLORFLOW:
                a(canvas);
                return;
            case SUNSHINE:
                if (this.p != null) {
                    this.e.reset();
                    this.e.preScale(((float) this.mWidth) / this.r, ((float) this.mHeight) / this.s);
                    Paint paint = new Paint();
                    if (this.q) {
                        paint.setAlpha(100);
                    } else {
                        paint.setAlpha(200);
                    }
                    canvas.drawBitmap(this.p, this.e, paint);
                    paint.setAlpha(200);
                }
                a(canvas);
                return;
            case COLOR:
                a(canvas);
                return;
            case SWITCH:
                a(canvas);
                return;
            case NIGHTLIGHT:
                a(canvas);
                return;
            default:
                return;
        }
    }

    private void a() {
        this.f = new Paint();
        this.e = new Matrix();
        if (this.j == null) {
            if (this.q) {
                setCycleBitmap();
            } else {
                this.j = BitmapUtils.a(getResources(), R.drawable.touch_dot, 50, 50);
                this.l = (float) this.j.getWidth();
            }
        }
        if (this.p == null) {
            this.p = BitmapUtils.a(getResources(), R.drawable.sunshine_mask, this.mWidth, this.mHeight, 10);
            this.r = (float) this.p.getWidth();
            this.s = (float) this.p.getHeight();
        }
    }

    private void a(Canvas canvas) {
        if (this.g) {
            this.e.reset();
            float f2 = ((float) this.mWidth) * this.m;
            this.e.preScale(f2 / this.l, f2 / this.l);
            int i2 = (int) (f2 / 2.0f);
            this.e.postTranslate((float) (((int) this.h) - i2), (float) (((int) this.i) - i2));
            this.f.setAlpha(this.k);
            if (this.j != null) {
                canvas.drawBitmap(this.j, this.e, this.f);
            }
            invalidate();
        }
    }

    public void setCycleBitmap() {
        int i2 = (int) ((((float) getResources().getDisplayMetrics().widthPixels) / 1080.0f) * 550.0f);
        this.j = BitmapUtils.a(getResources(), R.drawable.touch_bg, i2, i2);
        this.l = (float) this.j.getWidth();
        this.q = true;
    }
}
