package com.xiaomi.smarthome.miio.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class WifiLogHorizontalScrollView extends HorizontalScrollView {

    /* renamed from: a  reason: collision with root package name */
    private int f11827a = 0;
    private int b = 0;
    private int c = 20;
    private int d = 0;

    public WifiLogHorizontalScrollView(Context context) {
        super(context);
    }

    public WifiLogHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WifiLogHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        if (i < 0) {
            this.c = 0;
        } else {
            this.c = i;
        }
        this.d = i2;
    }

    public void setItemWidth(int i) {
        this.f11827a = i;
    }

    public void setTotalCount(int i) {
        this.b = i;
    }

    public int getItemIndex() {
        return this.c / this.f11827a;
    }

    public boolean scrollItemCount(int i) {
        return scrollToIndex(getItemIndex() + i);
    }

    public boolean scrollToIndex(int i) {
        if (i < 0) {
            i = 0;
        }
        int i2 = i * this.f11827a;
        if (this.b > 0 && i2 > this.b * this.f11827a) {
            i2 = this.b * this.f11827a;
        }
        if (i2 == this.c) {
            return false;
        }
        this.c = i2;
        smoothScrollTo(i2, this.d);
        return true;
    }
}
