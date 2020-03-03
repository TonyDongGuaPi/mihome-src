package com.mics.widget.stickyball.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import com.mics.widget.stickyball.utils.GeometryUtil;

public class StickyBallView extends View {
    private static final int i = Color.parseColor("#FF4081");

    /* renamed from: a  reason: collision with root package name */
    private Paint f7850a;
    private PointF b;
    private PointF c;
    private PointF d;
    private PointF e;
    private PointF[] f;
    private PointF g;
    private Path h;
    private int j;
    private float k;
    private float l;
    private float m;
    private float n;
    private OnTranslationListener o;

    public interface OnTranslationListener {
        void a(float f, float f2);

        void b(float f, float f2);
    }

    public StickyBallView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StickyBallView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StickyBallView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.j = i;
        this.k = 10.0f;
        this.l = 10.0f;
        this.m = 10.0f;
        this.n = 10.0f;
        a();
    }

    public void setColor(int i2) {
        this.j = i2;
        if (this.f7850a != null) {
            this.f7850a.setColor(i2);
        }
        invalidate();
    }

    private void a() {
        this.b = new PointF(this.k, this.k);
        this.c = new PointF(this.m, this.m);
        this.d = new PointF(this.k, this.k);
        this.e = new PointF(this.m, this.m);
        this.f7850a = new Paint(1);
        this.f7850a.setColor(this.j);
        this.f7850a.setStrokeCap(Paint.Cap.ROUND);
        this.f7850a.setStyle(Paint.Style.FILL);
        a(this.b, this.c);
    }

    private void a(PointF pointF, PointF pointF2) {
        float f2 = pointF.x - pointF2.x;
        PointF pointF3 = pointF2;
        PointF pointF4 = pointF;
        this.f = GeometryUtil.a(pointF3, pointF4, this.n, this.l, (double) ((pointF.y - pointF2.y) / f2));
        this.g = GeometryUtil.a(pointF2, pointF, this.n, this.l);
        if (this.h == null) {
            this.h = new Path();
        }
        this.h.reset();
        this.h.moveTo(this.f[1].x, this.f[1].y);
        this.h.quadTo(this.g.x, this.g.y, this.f[0].x, this.f[0].y);
        this.h.lineTo(this.f[2].x, this.f[2].y);
        this.h.quadTo(this.g.x, this.g.y, this.f[3].x, this.f[3].y);
        this.h.close();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.b.x, this.b.y, this.l, this.f7850a);
        canvas.drawCircle(this.c.x, this.c.y, this.n, this.f7850a);
        canvas.drawPath(this.h, this.f7850a);
    }

    public void setSourceRadius(float f2) {
        this.l = f2;
        a(this.b, this.c);
        invalidate();
    }

    public void setTargetRadius(float f2) {
        this.n = f2;
        a(this.b, this.c);
        invalidate();
    }

    public void setSourceXY(float f2, float f3) {
        this.b.set(f2, f3);
        this.d.set(f2, f3);
        invalidate();
    }

    public void setTargetXY(float f2, float f3) {
        this.c.set(f2, f3);
        this.e.set(f2, f3);
        invalidate();
    }

    public void updateSourceCache() {
        this.d.set(this.b.x, this.b.y);
    }

    public void updateTargetCache() {
        this.e.set(this.c.x, this.c.y);
    }

    public void setSourceTranslationX(float f2) {
        this.b.set(this.d.x + f2, this.b.y);
        if (this.o != null) {
            this.o.a(f2, 0.0f);
        }
        a(this.b, this.c);
        invalidate();
    }

    public void setSourceTranslationY(float f2) {
        this.b.set(this.b.x, this.d.y + f2);
        if (this.o != null) {
            this.o.a(0.0f, f2);
        }
        a(this.b, this.c);
        invalidate();
    }

    public void setTargetTranslationX(float f2) {
        this.c.set(this.e.x + f2, this.c.y);
        if (this.o != null) {
            this.o.b(f2, 0.0f);
        }
        a(this.b, this.c);
        invalidate();
    }

    public void setTargetTranslationY(float f2) {
        this.c.set(this.c.x, this.e.y + f2);
        if (this.o != null) {
            this.o.b(0.0f, f2);
        }
        a(this.b, this.c);
        invalidate();
    }

    public void setOnTranslationListener(OnTranslationListener onTranslationListener) {
        this.o = onTranslationListener;
    }
}
