package com.mics.widget.stickyball.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.mics.R;
import com.mics.widget.stickyball.utils.DimensionUtil;

public class DotIndicatorView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    public static final ISelectedView NORMAL_BALL = null;

    /* renamed from: a  reason: collision with root package name */
    private static final int f7843a = Color.parseColor("#a9a9a9");
    private static final int b = Color.parseColor("#444444");
    private static int c;
    private static int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private Paint n;
    private Paint o;
    private PointF[] p;
    private ISelectedView q;
    private RelativeLayout.LayoutParams r;
    private DotIndicatorInfo s;
    private int t;

    public void onPageScrollStateChanged(int i2) {
    }

    public void onPageScrolled(int i2, float f2, int i3) {
    }

    public DotIndicatorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public DotIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotIndicatorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.t = 0;
        setBackgroundColor(0);
        c = DimensionUtil.b(getContext(), 10.0f);
        d = DimensionUtil.b(getContext(), 3.0f);
        a(attributeSet);
        a();
        setClipChildren(false);
        setClipToPadding(false);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.DotIndicatorView);
            this.f = obtainStyledAttributes.getColor(R.styleable.DotIndicatorView_color_selected, b);
            this.e = obtainStyledAttributes.getColor(R.styleable.DotIndicatorView_color_unselected, f7843a);
            this.g = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DotIndicatorView_dot_center_distance, c);
            this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DotIndicatorView_dot_radius, d);
            this.i = obtainStyledAttributes.getInt(R.styleable.DotIndicatorView_dot_count, 1);
            obtainStyledAttributes.recycle();
        }
        this.j = getPaddingLeft();
        this.k = getPaddingTop();
        this.l = getPaddingRight();
        this.m = getPaddingBottom();
    }

    private void a() {
        this.n = new Paint(1);
        this.n.setColor(this.f);
        this.o = new Paint(1);
        this.o.setColor(this.e);
        a(this.i);
        this.r = new RelativeLayout.LayoutParams(-1, -1);
    }

    public void setCurrentItem(int i2) {
        this.t = i2 % this.i;
        this.s.i(this.t);
        if (this.q != null) {
            this.q.a(this.t);
        }
    }

    public void setSelectedView(ISelectedView iSelectedView) {
        if (iSelectedView == NORMAL_BALL) {
            iSelectedView = new SelectedNormalBall(getContext());
        }
        this.q = iSelectedView;
        addView(iSelectedView.a((View) this), this.r);
        if (this.s == null) {
            this.s = new DotIndicatorInfo();
        }
        this.s.a(this.f);
        this.s.b(this.g);
        this.s.d(this.i);
        this.s.c(this.h);
        this.s.a(this.p);
        this.s.i(this.t);
        this.s.e(this.j);
        this.s.f(this.k);
        this.s.g(this.l);
        this.s.h(this.m);
        iSelectedView.a(this.s);
    }

    public void setColorUnSelected(int i2) {
        this.e = i2;
        invalidate();
    }

    public void setColorSelected(int i2) {
        this.f = i2;
        if (!(this.s == null || this.q == null)) {
            this.s.a(this.e);
            this.q.a(this.s);
        }
        invalidate();
    }

    public void setDotCenterDistance(int i2) {
        this.g = i2;
        if (!(this.s == null || this.q == null)) {
            this.s.b(i2);
            this.q.a(this.s);
        }
        invalidate();
    }

    public void setDotRadius(int i2) {
        this.h = i2;
        if (!(this.s == null || this.q == null)) {
            this.s.c(i2);
            this.q.a(this.s);
        }
        invalidate();
    }

    public void setDotCount(int i2) {
        if (this.i != i2) {
            this.i = i2;
            a(i2);
            if (!(this.s == null || this.q == null)) {
                this.s.d(i2);
                this.s.a(this.p);
                this.q.a(this.s);
            }
            invalidate();
            requestLayout();
        }
    }

    private void a(int i2) {
        this.p = new PointF[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.p[i3] = new PointF((float) (this.j + (this.g * i3) + this.h), (float) (this.k + this.h));
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PointF pointF : this.p) {
            canvas.drawCircle(pointF.x, pointF.y, (float) this.h, this.o);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int c2 = c(i2);
        int b2 = b(i3);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(b2, 1073741824));
        setMeasuredDimension(c2, b2);
    }

    private int b(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 0) ? this.k + this.m + (this.h * 2) : View.MeasureSpec.getSize(i2);
    }

    private int c(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 0) ? this.j + this.l + (this.h * 2) + ((this.i - 1) * this.g) : View.MeasureSpec.getSize(i2);
    }

    public void onPageSelected(int i2) {
        setCurrentItem(i2);
    }
}
