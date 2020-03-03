package com.xiaomi.smarthome.device;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class BleGatewayNestedScrollingChild extends LinearLayout implements NestedScrollingChild {

    /* renamed from: a  reason: collision with root package name */
    private NestedScrollingChildHelper f14755a;
    private final int[] b = new int[2];
    private final int[] c = new int[2];
    private final int[] d = new int[2];
    private int e;
    private int f;

    public BleGatewayNestedScrollingChild(Context context) {
        super(context);
        a();
    }

    public BleGatewayNestedScrollingChild(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public BleGatewayNestedScrollingChild(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.f14755a = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.f14755a.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.f14755a.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return this.f14755a.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.f14755a.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.f14755a.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, @Nullable @Size(2) int[] iArr) {
        return this.f14755a.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, @Nullable @Size(2) int[] iArr, @Nullable @Size(2) int[] iArr2) {
        return this.f14755a.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.f14755a.dispatchNestedFling(f2, f3, z);
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.f14755a.dispatchNestedPreFling(f2, f3);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int[] iArr = this.b;
            this.b[1] = 0;
            iArr[0] = 0;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.e = (int) (motionEvent.getX() + 0.5f);
                this.f = (int) (motionEvent.getY() + 0.5f);
                startNestedScroll(2);
                break;
            case 1:
                stopNestedScroll();
                break;
            case 2:
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                int i = this.e - x;
                int i2 = this.f - y;
                if (dispatchNestedPreScroll(i, i2, this.d, this.c)) {
                    i -= this.d[0];
                    i2 -= this.d[1];
                    int[] iArr2 = this.b;
                    iArr2[0] = iArr2[0] + this.c[0];
                    int[] iArr3 = this.b;
                    iArr3[1] = iArr3[1] + this.c[1];
                }
                int i3 = i;
                int i4 = i2;
                if (getScrollY() + i4 > 0) {
                    dispatchNestedScroll(0, i4, i3, 0, this.b);
                } else if (getScrollY() > 0) {
                    dispatchNestedScroll(0, getScrollY(), i3, i4 - getScrollY(), this.b);
                }
                this.e = x - this.c[0];
                this.f = y - this.c[1];
                break;
        }
        return true;
    }
}
