package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.circlenavigator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.abs.IPagerNavigator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import java.util.ArrayList;
import java.util.List;

public class CircleNavigator extends View implements IPagerNavigator {

    /* renamed from: a  reason: collision with root package name */
    private int f20927a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Interpolator g = new LinearInterpolator();
    private Paint h = new Paint(1);
    private List<PointF> i = new ArrayList();
    private float j;
    private boolean k;
    private OnCircleClickListener l;
    private float m;
    private float n;
    private int o;
    private boolean p = true;

    public interface OnCircleClickListener {
        void onClick(int i);
    }

    public boolean isCompactMode() {
        return false;
    }

    public void onAttachToMagicIndicator() {
    }

    public void onDetachFromMagicIndicator() {
    }

    public void onPageScrollStateChanged(int i2) {
    }

    public void setCompactMode(boolean z) {
    }

    public CircleNavigator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.o = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f20927a = UIUtil.a(context, 3.0d);
        this.d = UIUtil.a(context, 8.0d);
        this.c = UIUtil.a(context, 1.0d);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(a(i2), b(i3));
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            return (this.f * this.f20927a * 2) + ((this.f - 1) * this.d) + getPaddingLeft() + getPaddingRight() + (this.c * 2);
        }
        if (mode != 1073741824) {
            return 0;
        }
        return size;
    }

    private int b(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            return (this.f20927a * 2) + (this.c * 2) + getPaddingTop() + getPaddingBottom();
        }
        if (mode != 1073741824) {
            return 0;
        }
        return size;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.h.setColor(this.b);
        a(canvas);
        b(canvas);
    }

    private void a(Canvas canvas) {
        this.h.setStyle(Paint.Style.STROKE);
        this.h.setStrokeWidth((float) this.c);
        int size = this.i.size();
        for (int i2 = 0; i2 < size; i2++) {
            PointF pointF = this.i.get(i2);
            canvas.drawCircle(pointF.x, pointF.y, (float) this.f20927a, this.h);
        }
    }

    private void b(Canvas canvas) {
        this.h.setStyle(Paint.Style.FILL);
        if (this.i.size() > 0) {
            canvas.drawCircle(this.j, (float) ((int) ((((float) getHeight()) / 2.0f) + 0.5f)), (float) this.f20927a, this.h);
        }
    }

    private void a() {
        this.i.clear();
        if (this.f > 0) {
            int height = (int) ((((float) getHeight()) / 2.0f) + 0.5f);
            int i2 = (this.f20927a * 2) + this.d;
            int paddingLeft = this.f20927a + ((int) ((((float) this.c) / 2.0f) + 0.5f)) + getPaddingLeft();
            for (int i3 = 0; i3 < this.f; i3++) {
                this.i.add(new PointF((float) paddingLeft, (float) height));
                paddingLeft += i2;
            }
            this.j = this.i.get(this.e).x;
        }
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.p && !this.i.isEmpty()) {
            int min = Math.min(this.i.size() - 1, i2);
            int min2 = Math.min(this.i.size() - 1, i2 + 1);
            PointF pointF = this.i.get(min);
            this.j = pointF.x + ((this.i.get(min2).x - pointF.x) * this.g.getInterpolation(f2));
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                if (this.k) {
                    this.m = x;
                    this.n = y;
                    return true;
                }
                break;
            case 1:
                if (this.l != null && Math.abs(x - this.m) <= ((float) this.o) && Math.abs(y - this.n) <= ((float) this.o)) {
                    float f2 = Float.MAX_VALUE;
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.i.size(); i3++) {
                        float abs = Math.abs(this.i.get(i3).x - x);
                        if (abs < f2) {
                            i2 = i3;
                            f2 = abs;
                        }
                    }
                    this.l.onClick(i2);
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onPageSelected(int i2) {
        this.e = i2;
        if (!this.p) {
            this.j = this.i.get(this.e).x;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        a();
    }

    public void notifyDataSetChanged() {
        a();
        invalidate();
    }

    public int getRadius() {
        return this.f20927a;
    }

    public void setRadius(int i2) {
        this.f20927a = i2;
        a();
        invalidate();
    }

    public int getCircleColor() {
        return this.b;
    }

    public void setCircleColor(int i2) {
        this.b = i2;
        invalidate();
    }

    public int getStrokeWidth() {
        return this.c;
    }

    public void setStrokeWidth(int i2) {
        this.c = i2;
        invalidate();
    }

    public int getCircleSpacing() {
        return this.d;
    }

    public void setCircleSpacing(int i2) {
        this.d = i2;
        a();
        invalidate();
    }

    public Interpolator getStartInterpolator() {
        return this.g;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.g = interpolator;
        if (this.g == null) {
            this.g = new LinearInterpolator();
        }
    }

    public int getCircleCount() {
        return this.f;
    }

    public void setCircleCount(int i2) {
        this.f = i2;
    }

    public boolean isTouchable() {
        return this.k;
    }

    public void setTouchable(boolean z) {
        this.k = z;
    }

    public boolean isFollowTouch() {
        return this.p;
    }

    public void setFollowTouch(boolean z) {
        this.p = z;
    }

    public OnCircleClickListener getCircleClickListener() {
        return this.l;
    }

    public void setCircleClickListener(OnCircleClickListener onCircleClickListener) {
        if (!this.k) {
            this.k = true;
        }
        this.l = onCircleClickListener;
    }
}
