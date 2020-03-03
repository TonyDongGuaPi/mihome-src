package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.List;

public class MiBrainCircleWaveView extends View {

    /* renamed from: a  reason: collision with root package name */
    private Context f10751a;
    private Paint b;
    private int c;
    List<Circle> circles = new ArrayList();
    private float d;
    /* access modifiers changed from: private */
    public int e;
    private int f = 1500;
    private int g;
    private int h;
    private final int i = 20;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public int k = 30;
    Canvas mCanvas;

    public MiBrainCircleWaveView(Context context) {
        super(context);
        this.f10751a = context;
        a((AttributeSet) null);
    }

    public MiBrainCircleWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10751a = context;
        a(attributeSet);
    }

    public MiBrainCircleWaveView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10751a = context;
        a(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        Log.d("CircleWaveView", "onMeasure");
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
        this.e = ((int) (((float) (Math.max(size, size2) / 2)) - (this.d / 2.0f))) / 2;
        setMeasuredDimension(max, max);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.f10751a.obtainStyledAttributes(attributeSet, R.styleable.CircleWaveView);
        this.c = obtainStyledAttributes.getColor(0, Color.h);
        this.d = obtainStyledAttributes.getDimension(1, 1.0f);
        Log.d("CircleWaveView", "mWaveWidth" + this.d);
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setColor(getResources().getColor(R.color.mi_brain_circle_view_green));
        this.b.setStrokeWidth(this.d);
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setAlpha(255);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Log.d("CircleWaveView", "onDraw");
        super.onDraw(canvas);
        this.mCanvas = canvas;
        this.g = getMeasuredWidth() / 2;
        this.h = getMeasuredHeight() / 2;
        if (this.j) {
            canvas.drawCircle((float) this.g, (float) this.h, (float) this.e, this.b);
        }
        Log.d("CircleWaveView", "onDraw getAlpha：" + this.b.getAlpha() + " radius：" + this.e + " drawCircle：" + this.j + "  progress：" + this.k);
        for (int i2 = 0; i2 < this.circles.size(); i2++) {
            if (this.circles.get(i2).c.getAlpha() > 0) {
                Log.d("CircleWaveView", "onDraw----- " + i2 + "getAlpha" + this.circles.get(i2).c.getAlpha() + "radius" + this.circles.get(i2).d);
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

    public void addCircle(int i2) {
        final Circle circle = new Circle(this.g, this.h, this.b, i2);
        this.circles.add(circle);
        new Thread(new Runnable() {
            public void run() {
                int access$000 = MiBrainCircleWaveView.this.e / 20;
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(30);
                        if (i < 19) {
                            circle.a(access$000, 12, false);
                        } else {
                            circle.a(access$000, 12, true);
                        }
                        MiBrainCircleWaveView.this.postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void enterRecordStatus() {
        this.j = true;
        this.b.setColor(getResources().getColor(R.color.mi_brain_circle_view_green));
        postInvalidate();
    }

    public void exitRecordStatus(boolean z) {
        this.b.setColor(Color.c);
        if (z) {
            this.j = false;
            new Thread(new Runnable() {
                public void run() {
                    while (MiBrainCircleWaveView.this.k < 100) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int unused = MiBrainCircleWaveView.this.k = MiBrainCircleWaveView.this.k + 2;
                        MiBrainCircleWaveView.this.postInvalidate();
                    }
                    if (MiBrainCircleWaveView.this.k >= 100) {
                        boolean unused2 = MiBrainCircleWaveView.this.j = true;
                        int unused3 = MiBrainCircleWaveView.this.k = 30;
                        MiBrainCircleWaveView.this.postInvalidate();
                    }
                }
            }).start();
            return;
        }
        this.j = true;
        this.k = 30;
        postInvalidate();
    }

    private int a(float f2) {
        return (int) ((f2 * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
