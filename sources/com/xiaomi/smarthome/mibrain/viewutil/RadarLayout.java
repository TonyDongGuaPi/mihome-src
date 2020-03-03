package com.xiaomi.smarthome.mibrain.viewutil;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
public class RadarLayout extends FrameLayout {
    public static final int INFINITE = -1;

    /* renamed from: a  reason: collision with root package name */
    private static final int f10718a = 4;
    private static final int b = Color.rgb(0, 116, 193);
    private static final int c = 3000;
    private static final int d = -1;
    private static final int e = 2;
    private int f;
    private int g;
    private int h = -1;
    private AnimatorSet i;
    /* access modifiers changed from: private */
    public Paint j;
    /* access modifiers changed from: private */
    public int k;
    private Bitmap l;
    /* access modifiers changed from: private */
    public float m;
    /* access modifiers changed from: private */
    public float n;
    /* access modifiers changed from: private */
    public float o;
    /* access modifiers changed from: private */
    public int p;
    /* access modifiers changed from: private */
    public boolean q;
    /* access modifiers changed from: private */
    public boolean r;
    private float s = 0.0f;
    private float t = 1.0f;
    private float u = 1.0f;
    private float v = 1.0f;
    private boolean w = false;
    private Interpolator x = new LinearInterpolator();
    /* access modifiers changed from: private */
    public Shader y;
    private final Animator.AnimatorListener z = new Animator.AnimatorListener() {
        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
            boolean unused = RadarLayout.this.q = true;
        }

        public void onAnimationEnd(Animator animator) {
            boolean unused = RadarLayout.this.q = false;
        }

        public void onAnimationCancel(Animator animator) {
            boolean unused = RadarLayout.this.q = false;
        }
    };

    public RadarLayout(Context context) {
        super(context);
        a();
    }

    public RadarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public RadarLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        this.k = b;
        this.f = 4;
        this.g = 3000;
        this.h = -1;
        this.r = false;
        this.p = a(2.0f);
        c();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void start() {
        /*
            r1 = this;
            monitor-enter(r1)
            android.animation.AnimatorSet r0 = r1.i     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0011
            boolean r0 = r1.q     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x000a
            goto L_0x0011
        L_0x000a:
            android.animation.AnimatorSet r0 = r1.i     // Catch:{ all -> 0x0013 }
            r0.start()     // Catch:{ all -> 0x0013 }
            monitor-exit(r1)
            return
        L_0x0011:
            monitor-exit(r1)
            return
        L_0x0013:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.mibrain.viewutil.RadarLayout.start():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
            r1 = this;
            monitor-enter(r1)
            android.animation.AnimatorSet r0 = r1.i     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0011
            boolean r0 = r1.q     // Catch:{ all -> 0x0013 }
            if (r0 != 0) goto L_0x000a
            goto L_0x0011
        L_0x000a:
            android.animation.AnimatorSet r0 = r1.i     // Catch:{ all -> 0x0013 }
            r0.end()     // Catch:{ all -> 0x0013 }
            monitor-exit(r1)
            return
        L_0x0011:
            monitor-exit(r1)
            return
        L_0x0013:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.mibrain.viewutil.RadarLayout.stop():void");
    }

    public synchronized boolean isStarted() {
        return this.i != null && this.q;
    }

    public int getCount() {
        return this.f;
    }

    public int getDuration() {
        return this.g;
    }

    public void setCount(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        } else if (i2 != this.f) {
            this.f = i2;
            d();
            invalidate();
        }
    }

    public void setDuration(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        } else if (i2 != this.g) {
            this.g = i2;
            d();
            invalidate();
        }
    }

    public void setScale(float f2, float f3) {
        if (this.s != f2 || this.t != f3) {
            this.s = f2;
            this.t = f3;
            d();
            invalidate();
        }
    }

    public void setColor(int i2) {
        if (this.k != i2) {
            this.k = i2;
            d();
            invalidate();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        if (this.l != bitmap) {
            this.l = bitmap;
            d();
            invalidate();
        }
    }

    public void setUseRing(boolean z2) {
        if (this.r != z2) {
            this.r = z2;
            d();
            invalidate();
        }
    }

    public void setRepeat(int i2) {
        if (this.h != i2) {
            this.h = i2;
            d();
            invalidate();
        }
    }

    public void setAlpha(float f2, float f3) {
        if (f2 != this.u || f3 != this.v) {
            this.u = f2;
            this.v = f3;
            d();
            invalidate();
        }
    }

    public void setBackToOrigin(boolean z2) {
        if (this.w != z2) {
            this.w = z2;
            d();
            invalidate();
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        if (!(interpolator instanceof LinearInterpolator)) {
            this.x = interpolator;
            d();
            invalidate();
        }
    }

    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        this.n = ((float) measuredWidth) * 0.5f;
        this.o = ((float) measuredHeight) * 0.5f;
        this.m = ((float) Math.min(measuredWidth, measuredHeight)) * 0.5f;
    }

    private void b() {
        stop();
        removeAllViews();
    }

    private void c() {
        int i2 = -1;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        if (this.h != -1) {
            i2 = this.h;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.f; i3++) {
            RadarView radarView = new RadarView(getContext());
            radarView.setScaleX(0.0f);
            radarView.setScaleY(0.0f);
            radarView.setAlpha(1.0f);
            if (this.l != null) {
                this.y = new BitmapShader(this.l, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            addView(radarView, i3, layoutParams);
            RadarView radarView2 = radarView;
            int i4 = i2;
            long j2 = (long) ((this.g * i3) / this.f);
            arrayList.add(a(radarView2, "scaleX", i4, j2, this.s, this.t));
            arrayList.add(a(radarView2, "scaleY", i4, j2, this.s, this.t));
            arrayList.add(a(radarView2, "alpha", i4, j2, this.u, this.v));
        }
        this.i = new AnimatorSet();
        this.i.playTogether(arrayList);
        this.i.setInterpolator(this.x);
        this.i.setDuration((long) this.g);
        this.i.addListener(this.z);
    }

    private ObjectAnimator a(View view, String str, int i2, long j2, float f2, float f3) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, new float[]{f2, f3});
        ofFloat.setRepeatCount(i2);
        ofFloat.setRepeatMode(1);
        ofFloat.setStartDelay(j2);
        ofFloat.setDuration((long) this.g);
        return ofFloat;
    }

    private void d() {
        boolean isStarted = isStarted();
        b();
        c();
        if (isStarted) {
            start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.l != null) {
            this.l.recycle();
            this.y = null;
        }
    }

    private class RadarView extends View {
        public RadarView(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            if (RadarLayout.this.j == null) {
                Paint unused = RadarLayout.this.j = new Paint();
                RadarLayout.this.j.setColor(RadarLayout.this.k);
                RadarLayout.this.j.setAntiAlias(true);
                RadarLayout.this.j.setStyle(RadarLayout.this.r ? Paint.Style.STROKE : Paint.Style.FILL);
                RadarLayout.this.j.setStrokeWidth(RadarLayout.this.r ? (float) RadarLayout.this.p : 0.0f);
                if (RadarLayout.this.y != null) {
                    RadarLayout.this.j.setShader(RadarLayout.this.y);
                }
            }
            canvas.drawCircle(RadarLayout.this.n, RadarLayout.this.o, RadarLayout.this.r ? RadarLayout.this.m - ((float) RadarLayout.this.p) : RadarLayout.this.m, RadarLayout.this.j);
        }
    }

    private int a(float f2) {
        return (int) ((f2 * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
