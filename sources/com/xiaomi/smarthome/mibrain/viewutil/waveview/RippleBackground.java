package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class RippleBackground extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10760a = 6;
    private static final int b = 3000;
    private static final float c = 2.0f;
    private static final float d = 0.13f;
    private static final int e = 0;
    private int f;
    /* access modifiers changed from: private */
    public float g;
    private float h;
    private int i;
    private int j;
    private int k;
    private float l;
    private int m;
    private float n;
    /* access modifiers changed from: private */
    public Paint o;
    private boolean p = false;
    private AnimatorSet q;
    private ArrayList<Animator> r;
    private RelativeLayout.LayoutParams s;
    private ArrayList<RippleView> t = new ArrayList<>();

    public RippleBackground(Context context) {
        super(context);
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleBackground);
                this.f = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.rippleColor));
                this.g = obtainStyledAttributes.getDimension(6, (float) DisplayUtils.a((float) c));
                this.h = obtainStyledAttributes.getDimension(2, (float) DisplayUtils.a(32.0f));
                this.i = obtainStyledAttributes.getInt(1, 3000);
                this.j = obtainStyledAttributes.getInt(3, 6);
                this.l = obtainStyledAttributes.getFloat(4, c);
                this.m = obtainStyledAttributes.getInt(7, 0);
                this.n = obtainStyledAttributes.getFloat(5, d);
                obtainStyledAttributes.recycle();
                this.k = this.i / this.j;
                this.o = new Paint();
                this.o.setAntiAlias(true);
                if (this.m == 0) {
                    this.g = 0.0f;
                    this.o.setStyle(Paint.Style.FILL);
                } else {
                    this.o.setStyle(Paint.Style.STROKE);
                }
                this.o.setColor(this.f);
                this.o.setAlpha((int) (this.n * 255.0f));
                this.s = new RelativeLayout.LayoutParams((int) ((this.h + this.g) * c), (int) ((this.h + this.g) * c));
                this.s.addRule(13, -1);
                this.q = new AnimatorSet();
                this.q.setInterpolator(new DecelerateInterpolator());
                this.r = new ArrayList<>();
                for (int i2 = 0; i2 < this.j; i2++) {
                    RippleView rippleView = new RippleView(getContext());
                    addView(rippleView, this.s);
                    this.t.add(rippleView);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(rippleView, "ScaleX", new float[]{1.0f, this.l});
                    ofFloat.setRepeatCount(-1);
                    ofFloat.setRepeatMode(1);
                    ofFloat.setStartDelay((long) (this.k * i2));
                    ofFloat.setDuration((long) this.i);
                    this.r.add(ofFloat);
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(rippleView, "ScaleY", new float[]{1.0f, this.l});
                    ofFloat2.setRepeatCount(-1);
                    ofFloat2.setRepeatMode(1);
                    ofFloat2.setStartDelay((long) (this.k * i2));
                    ofFloat2.setDuration((long) this.i);
                    this.r.add(ofFloat2);
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(rippleView, "Alpha", new float[]{1.0f, 0.0f});
                    ofFloat3.setRepeatCount(-1);
                    ofFloat3.setRepeatMode(1);
                    ofFloat3.setStartDelay((long) (this.k * i2));
                    ofFloat3.setDuration((long) this.i);
                    this.r.add(ofFloat3);
                }
                this.q.playTogether(this.r);
                return;
            }
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
    }

    private class RippleView extends View {
        public RippleView(Context context) {
            super(context);
            setVisibility(4);
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            float min = (float) (Math.min(getWidth(), getHeight()) / 2);
            canvas.drawCircle(min, min, min - RippleBackground.this.g, RippleBackground.this.o);
        }
    }

    public void startRippleAnimation(Animator.AnimatorListener animatorListener) {
        if (!isRippleAnimationRunning()) {
            Iterator<RippleView> it = this.t.iterator();
            while (it.hasNext()) {
                it.next().setVisibility(0);
            }
            if (animatorListener != null) {
                this.q.addListener(animatorListener);
            }
            this.q.start();
            this.p = true;
        }
    }

    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            this.q.end();
            this.p = false;
        }
    }

    public boolean isRippleAnimationRunning() {
        return this.p;
    }
}
