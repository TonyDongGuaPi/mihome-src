package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.shop.utils.LogUtil;

public class NestedScrollHeaderLayout extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private NestedScrollingParentHelper f18899a;
    private int b;
    private ScrollerCompat c;
    private boolean d;

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return false;
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public NestedScrollHeaderLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public NestedScrollHeaderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollHeaderLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.f18899a = new NestedScrollingParentHelper(this);
        this.c = ScrollerCompat.create(getContext());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.b = getChildAt(0).getMeasuredHeight();
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.f18899a.onNestedScrollAccepted(view, view2, i);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        int scrollY = getScrollY();
        if (i2 > 0 && scrollY < this.b && scrollY >= 0) {
            int min = Math.min(i2, this.b - scrollY);
            iArr[1] = min;
            scrollBy(0, min);
            if (!this.d) {
                this.d = true;
                setLayoutParams(new FrameLayout.LayoutParams(getWidth(), getHeight() + this.b));
            }
        } else if (i2 < 0 && scrollY == this.b) {
            iArr[1] = i2;
            scrollBy(0, i2);
        } else if (i2 < 0 && scrollY < this.b && scrollY > 0) {
            int max = Math.max(i2, -scrollY);
            iArr[1] = max;
            scrollBy(0, max);
        }
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        LogUtil.a("hzd", "onNestedScroll dxConsumed = " + i + " , dyConsumed = " + i2 + " , dxUnconsumed = " + i3 + " , dyUnconsumed = " + i4);
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        int scrollY = getScrollY();
        if (f2 <= 0.0f || scrollY >= this.b || scrollY <= 0) {
            return false;
        }
        if (!this.c.isFinished()) {
            this.c.abortAnimation();
        }
        this.c.fling(0, scrollY, (int) f, (int) f2, 0, 0, 0, this.b);
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void onStopNestedScroll(View view) {
        this.f18899a.onStopNestedScroll(view);
    }

    public int getNestedScrollAxes() {
        return this.f18899a.getNestedScrollAxes();
    }

    public void computeScroll() {
        if (this.c.computeScrollOffset()) {
            scrollTo(this.c.getCurrX(), this.c.getCurrY());
            postInvalidate();
        }
    }
}
