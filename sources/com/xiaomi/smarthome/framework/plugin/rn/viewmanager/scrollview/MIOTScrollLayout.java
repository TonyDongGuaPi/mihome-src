package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.scrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.facebook.react.views.view.ReactViewGroup;

public class MIOTScrollLayout extends ReactViewGroup implements View.OnTouchListener, OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    private final int f17625a;
    private View b;
    private int c;
    private MIOTScrollView d;
    private int e;
    private int f;
    private boolean g;
    private int[] h;
    private double[] i;
    private double[] j;
    private int k = -1;

    public MIOTScrollLayout(@NonNull Context context) {
        super(context);
        this.f17625a = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void addView(View view, int i2) {
        if (i2 != 0 || this.c == 0) {
            a(view);
        } else {
            this.b = view;
            this.b.setOnTouchListener(this);
        }
        super.addView(view, i2);
    }

    private void a(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                if (view instanceof MIOTScrollView) {
                    this.d = (MIOTScrollView) view;
                    this.d.setScrollListener(this);
                    return;
                }
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.d == null) {
            return false;
        }
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.f = y;
                break;
            case 1:
            case 3:
                if (this.d != null) {
                    this.d.snapToHeader(y <= this.f);
                }
                this.g = false;
                break;
            case 2:
                int i2 = y - this.f;
                int abs = Math.abs(y - this.f);
                if (this.d != null) {
                    if (!this.g && abs > this.f17625a && ((!this.d.isScrollSnap() && i2 < 0) || (this.d.isScrolledHeader() && i2 > 0))) {
                        this.g = true;
                    }
                    if (this.g) {
                        this.d.smoothScrollBy(0, this.e - y);
                        break;
                    }
                }
                break;
        }
        this.e = y;
        return true;
    }

    public void setHeaderHeight(int i2) {
        this.c = i2;
    }

    public void setScaleIndices(int[] iArr) {
        this.h = iArr;
    }

    public void setStartScales(double[] dArr) {
        this.i = dArr;
    }

    public void setEndScales(double[] dArr) {
        this.j = dArr;
    }

    public void onScrollChange(int i2, int i3) {
        if (!(this.b == null || this.k == i3)) {
            int i4 = this.c - i3;
            int width = this.b.getWidth();
            float f2 = (((float) i4) * 1.0f) / ((float) this.c);
            if (a()) {
                this.b.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
                int left = this.b.getLeft();
                int top = this.b.getTop();
                this.b.layout(left, top, width + left, i4 + top);
                View childAt = ((ViewGroup) this.b).getChildAt(0);
                childAt.setPivotY(0.0f);
                childAt.setScaleX(f2);
                childAt.setScaleY(f2);
                if (!(this.h == null || this.i == null || this.j == null || !(childAt instanceof ViewGroup))) {
                    for (int i5 = 0; i5 < this.h.length; i5++) {
                        View childAt2 = ((ViewGroup) childAt).getChildAt(this.h[i5]);
                        if (childAt2 != null) {
                            double d2 = (1.0d - this.j[i5]) / this.i[i5];
                            double d3 = (double) f2;
                            Double.isNaN(d3);
                            float f3 = (1.0f / f2) * ((float) (((d3 * d2) + 1.0d) - d2));
                            childAt2.setScaleX(f3);
                            childAt2.setScaleY(f3);
                        }
                    }
                }
            }
        }
        this.k = i3;
    }

    private boolean a() {
        if (this.b == null || !(this.b instanceof ViewGroup) || ((ViewGroup) this.b).getChildCount() != 1) {
            return false;
        }
        return true;
    }
}
