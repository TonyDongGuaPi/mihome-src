package com.xiaomi.smarthome.mibrain.viewutil;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.List;

public class CircleWaveView extends View {

    /* renamed from: a  reason: collision with root package name */
    private Context f10713a;
    private Paint b;
    private int c;
    List<Circle> circles = new ArrayList();
    private float d;
    /* access modifiers changed from: private */
    public int e;
    EnterThread enterThread;
    private int f = 1500;
    private int g;
    private int h;
    private final int i = 20;
    /* access modifiers changed from: private */
    public boolean j = true;
    /* access modifiers changed from: private */
    public int k = 30;
    Canvas mCanvas;

    public CircleWaveView(Context context) {
        super(context);
        this.f10713a = context;
        a((AttributeSet) null);
    }

    public CircleWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10713a = context;
        a(attributeSet);
    }

    public CircleWaveView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10713a = context;
        a(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int i4 = 0;
        int paddingLeft = mode == 1073741824 ? (int) (((float) (getPaddingLeft() + size + getPaddingRight())) + this.d) : 0;
        if (mode2 == 1073741824) {
            i4 = (int) (((float) (getPaddingTop() + size2 + getPaddingBottom())) + this.d);
        }
        int max = Math.max(paddingLeft, i4);
        this.e = ((int) (((float) (Math.min(size, size2) / 2)) - (this.d / 2.0f))) / 2;
        setMeasuredDimension(max, max);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.f10713a.obtainStyledAttributes(attributeSet, R.styleable.CircleWaveView);
        this.c = obtainStyledAttributes.getColor(0, Color.h);
        this.d = obtainStyledAttributes.getDimension(1, 1.0f);
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setColor(this.c);
        this.b.setStrokeWidth(this.d);
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setAlpha(255);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        this.g = getMeasuredWidth() / 2;
        this.h = getMeasuredHeight() / 2;
        if (this.j) {
            canvas.drawCircle((float) this.g, (float) this.h, (float) this.e, this.b);
        } else {
            Paint paint = new Paint();
            paint.setStrokeWidth(10.0f);
            paint.setAntiAlias(true);
            paint.setColor(-5978311);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(new RectF((float) (this.g - this.e), (float) (this.g - this.e), (float) (this.g + this.e), (float) (this.g + this.e)), -230.0f, (float) ((this.k * 360) / 100), false, this.b);
        }
        for (int i2 = 0; i2 < this.circles.size(); i2++) {
            if (this.circles.get(i2).c.getAlpha() > 0) {
                this.circles.get(i2).a(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    public void startAnimation() {
        postInvalidate();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.0f, 2.0f});
        ofFloat.setRepeatCount(1);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.0f, 2.0f});
        ofFloat2.setRepeatCount(1);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, 0.0f});
        ofFloat3.setRepeatCount(1);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration((long) this.f);
        alphaAnimation.setRepeatCount(-1);
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setDuration((long) this.f);
        animatorSet.start();
    }

    public void addCircle() {
        final Circle circle = new Circle(this.g, this.h, this.b, this.e);
        this.circles.add(circle);
        new Thread(new Runnable() {
            public void run() {
                int access$000 = CircleWaveView.this.e / 20;
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(30);
                        if (i < 19) {
                            circle.a(access$000, 12, false);
                        } else {
                            circle.a(access$000, 12, true);
                        }
                        CircleWaveView.this.postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void enterRecordStatus() {
        this.j = true;
        this.enterThread = new EnterThread();
        this.b.setColor(getResources().getColor(R.color.gateway_page_selected_text_color));
        postInvalidate();
        this.enterThread.start();
    }

    class EnterThread extends Thread {
        private boolean b = true;

        EnterThread() {
        }

        public void run() {
            while (this.b) {
                try {
                    CircleWaveView.this.addCircle();
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        public void a() {
            this.b = false;
        }
    }

    public void exitRecordStatus(boolean z) {
        if (this.enterThread != null && this.enterThread.isAlive()) {
            this.enterThread.a();
        }
        this.b.setColor(Color.c);
        if (z) {
            this.j = false;
            new Thread(new Runnable() {
                public void run() {
                    while (CircleWaveView.this.k < 100) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int unused = CircleWaveView.this.k = CircleWaveView.this.k + 2;
                        CircleWaveView.this.postInvalidate();
                    }
                    if (CircleWaveView.this.k >= 100) {
                        boolean unused2 = CircleWaveView.this.j = true;
                        int unused3 = CircleWaveView.this.k = 30;
                        CircleWaveView.this.postInvalidate();
                    }
                }
            }).start();
            return;
        }
        this.j = true;
        this.k = 30;
        postInvalidate();
    }
}
