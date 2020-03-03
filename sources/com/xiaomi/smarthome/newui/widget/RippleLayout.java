package com.xiaomi.smarthome.newui.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.Iterator;

public class RippleLayout extends FrameLayout implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20881a = 4;
    private static final int b = 1500;
    private static final int c = -16776961;
    private static final int d = -16777216;
    private static final int e = 0;
    private static final int f = 50;
    private static final float g = 6.0f;
    private static final float h = 12.0f;
    private int A = 2;
    private int B;
    /* access modifiers changed from: private */
    public Paint C = new Paint();
    /* access modifiers changed from: private */
    public Paint D = new Paint();
    /* access modifiers changed from: private */
    public Paint E = new Paint();
    private TextView F;
    private String G;
    private String H;
    private AnimListener I;
    private boolean J = false;
    private Shader K;
    private Shader L;
    private Shader M;
    private Shader N;
    private AnimatorSet O = new AnimatorSet();
    private ArrayList<Animator> P = new ArrayList<>();
    private FrameLayout.LayoutParams Q;
    private TouchListener R;
    private int i = -16776961;
    private int j = -16776961;
    private int k;
    private int l;
    private int m = -16777216;
    private float n;
    /* access modifiers changed from: private */
    public float o = 50.0f;
    private int p = 1500;
    /* access modifiers changed from: private */
    public float q = g;
    private int r;
    private int s;
    private float t = 0.0f;
    /* access modifiers changed from: private */
    public float u;
    private float v = h;
    private boolean w = false;
    private int x;
    private boolean y = false;
    private boolean z = false;

    public interface AnimListener {
        void a();

        void b();
    }

    public interface TouchListener {
        void g();

        void h();
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public void finish() {
        stopRippleAnimation();
    }

    public RippleLayout(Context context) {
        super(context);
        a(context, (AttributeSet) null);
    }

    public RippleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RippleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    public void setTouchListener(TouchListener touchListener) {
        this.R = touchListener;
    }

    public boolean isRippleAnimationRunning() {
        return this.w;
    }

    private void a() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).invalidate();
        }
    }

    public void setSending(boolean z2) {
        this.y = z2;
        if (z2) {
            this.F.setText(this.H);
            if (this.L != null) {
                this.C.setShader(this.L);
            }
            if (this.N != null) {
                this.E.setShader(this.N);
            }
        } else {
            this.F.setText(this.G);
            if (this.K != null) {
                this.C.setShader(this.K);
            }
            if (this.M != null) {
                this.E.setShader(this.M);
            }
        }
        a();
        this.F.invalidate();
    }

    public void startRippleAnimation() {
        if (!isRippleAnimationRunning()) {
            Iterator<Animator> it = this.P.iterator();
            while (it.hasNext()) {
                it.next().start();
            }
            this.w = true;
        }
        if (this.I != null) {
            this.I.a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            float width = (float) (getWidth() / 2);
            float height = (float) (getHeight() / 2);
            if (Math.sqrt((double) ((Math.abs(motionEvent.getX() - width) * Math.abs(motionEvent.getX() - width)) + (Math.abs(motionEvent.getY() - height) * Math.abs(motionEvent.getY() - height)))) <= ((double) this.o)) {
                this.J = true;
                if (this.R != null) {
                    this.R.g();
                }
                return true;
            }
        }
        if (motionEvent.getAction() == 1 && this.J) {
            float width2 = (float) (getWidth() / 2);
            float height2 = (float) (getHeight() / 2);
            if (Math.sqrt((double) ((Math.abs(motionEvent.getX() - width2) * Math.abs(motionEvent.getX() - width2)) + (Math.abs(motionEvent.getY() - height2) * Math.abs(motionEvent.getY() - height2)))) <= ((double) this.o)) {
                if (this.R != null) {
                    this.R.h();
                }
                this.J = false;
                return true;
            }
        }
        if (motionEvent.getAction() != 3) {
            return super.onTouchEvent(motionEvent);
        }
        this.J = false;
        return true;
    }

    public void stopRippleAnimation() {
        this.z = false;
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            if (attributeSet != null) {
                b(context, attributeSet);
            }
            e();
            d();
            b();
        }
    }

    private void a(RippleView rippleView, int i2) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(rippleView, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f}), PropertyValuesHolder.ofFloat("radius", new float[]{rippleView.b(), this.u + (this.v * ((float) ((this.r - 1) - i2)))})});
        ofPropertyValuesHolder.setDuration((long) this.p);
        ofPropertyValuesHolder.setRepeatCount(-1);
        ofPropertyValuesHolder.setRepeatMode(1);
        ofPropertyValuesHolder.addListener(this);
        this.P.add(ofPropertyValuesHolder);
    }

    private void b() {
        f();
        c();
        for (int i2 = 0; i2 < this.r; i2++) {
            RippleView rippleView = new RippleView(this, getContext());
            rippleView.b((this.o - (this.v * ((float) i2))) - this.q);
            addView(rippleView, this.Q);
            a(rippleView, i2);
        }
        addView(new CircleButton(getContext(), this.o));
        this.F = new TextView(getContext());
        this.F.setTextSize(this.n);
        this.F.setTextColor(this.m);
        this.F.setText(this.G);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.gravity = 17;
        this.F.setLayoutParams(layoutParams);
        addView(this.F);
    }

    public void setAnimListener(AnimListener animListener) {
        this.I = animListener;
    }

    private void c() {
        this.O.setDuration((long) this.p);
        this.O.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void d() {
        float f2 = this.u;
        float f3 = this.o + this.v;
        int i2 = 0;
        while (this.q + f3 < f2) {
            f3 += this.v;
            i2++;
        }
        if (i2 < this.s) {
            this.r = i2;
        } else {
            this.r = this.s;
        }
        int i3 = (int) ((this.u + this.q) * 2.0f);
        this.Q = new FrameLayout.LayoutParams(i3, i3);
        this.Q.setMargins(0, 0, 0, 0);
        this.Q.gravity = 17;
    }

    private void e() {
        this.C.setAntiAlias(true);
        this.C.setStyle(Paint.Style.FILL);
        this.C.setColor(this.i);
        this.D.setAntiAlias(true);
        this.D.setStyle(Paint.Style.FILL);
        this.D.setColor(-1);
        this.E.setAntiAlias(true);
        this.E.setStyle(Paint.Style.FILL);
    }

    private void f() {
        this.x = (int) ((((float) this.p) * this.v) / (this.u - this.o));
    }

    private void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleLayout);
        this.i = obtainStyledAttributes.getColor(2, -16776961);
        this.k = obtainStyledAttributes.getColor(3, -16776961);
        this.j = obtainStyledAttributes.getColor(4, -16776961);
        this.l = obtainStyledAttributes.getColor(5, -16776961);
        this.m = obtainStyledAttributes.getColor(7, -16777216);
        this.t = obtainStyledAttributes.getDimension(1, 0.0f);
        this.q = obtainStyledAttributes.getDimension(13, g);
        this.o = obtainStyledAttributes.getDimension(0, 50.0f);
        this.p = obtainStyledAttributes.getInt(14, 1500);
        this.s = obtainStyledAttributes.getInt(6, 4);
        this.u = obtainStyledAttributes.getDimension(11, 75.0f);
        this.n = obtainStyledAttributes.getDimension(10, context.getResources().getDimension(R.dimen.font_size_8sp));
        this.G = obtainStyledAttributes.getString(8);
        this.H = obtainStyledAttributes.getString(9);
        this.v = obtainStyledAttributes.getDimension(12, this.q * 3.0f);
        obtainStyledAttributes.recycle();
    }

    public void onAnimationRepeat(Animator animator) {
        if (this.z) {
            animator.cancel();
            this.B++;
            if (this.B >= this.A) {
                g();
            }
        }
    }

    private void g() {
        if (this.K != null) {
            this.C.setShader(this.K);
        }
        this.B = 0;
        this.w = false;
        a();
        if (this.I != null) {
            this.I.b();
        }
    }

    private class RippleView extends View {
        private float b;
        private float c;
        private Paint d;
        private Paint e;

        public float a() {
            return this.b;
        }

        public void a(float f) {
            this.b = f;
            invalidate();
        }

        public float b() {
            return this.c;
        }

        public void b(float f) {
            this.c = f;
            if (f <= RippleLayout.this.o) {
                this.b = RippleLayout.this.q;
            } else {
                this.b = RippleLayout.this.q * (1.0f - ((f - RippleLayout.this.o) / (RippleLayout.this.u - RippleLayout.this.o)));
            }
            invalidate();
        }

        public RippleView(Context context, boolean z) {
            super(context);
            this.b = RippleLayout.this.q;
            this.c = RippleLayout.this.o;
            if (z) {
                Paint access$300 = RippleLayout.this.C;
                this.e = access$300;
                this.d = access$300;
                return;
            }
            this.e = RippleLayout.this.C;
            this.d = RippleLayout.this.D;
        }

        public RippleView(RippleLayout rippleLayout, Context context) {
            this(context, false);
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            float min = (float) (Math.min(getWidth(), getHeight()) / 2);
            canvas.drawCircle(min, min, this.c + this.b, this.e);
            canvas.drawCircle(min, min, this.c, this.d);
        }
    }

    class CircleButton extends View {

        /* renamed from: a  reason: collision with root package name */
        float f20882a;

        CircleButton(Context context, float f) {
            super(context);
            this.f20882a = f;
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            float min = (float) (Math.min(getWidth(), getHeight()) / 2);
            canvas.drawCircle(min, min, this.f20882a, RippleLayout.this.E);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6 = i2;
        int i7 = i3;
        super.onSizeChanged(i2, i3, i4, i5);
        if (i6 > 0 && i7 > 0) {
            float f2 = (float) i6;
            float f3 = (float) i7;
            this.K = new LinearGradient(0.0f, 0.0f, f2, f3, this.k, this.i, Shader.TileMode.CLAMP);
            int i8 = this.l;
            float f4 = f2;
            this.L = new LinearGradient(0.0f, 0.0f, f2, f3, i8, this.j, Shader.TileMode.CLAMP);
            float f5 = f4 / 2.0f;
            float f6 = f5 - this.o;
            float f7 = f3 / 2.0f;
            float f8 = f7 - this.o;
            float f9 = f5 + this.o;
            float f10 = f7 + this.o;
            int i9 = this.k;
            this.M = new LinearGradient(f6, f8, f9, f10, i9, this.i, Shader.TileMode.CLAMP);
            this.N = new LinearGradient(f5 - this.o, f7 - this.o, f5 + this.o, f7 + this.o, this.l, this.j, Shader.TileMode.CLAMP);
            if (this.y) {
                this.C.setShader(this.L);
                this.E.setShader(this.N);
                return;
            }
            this.C.setShader(this.K);
            this.E.setShader(this.M);
        }
    }
}
