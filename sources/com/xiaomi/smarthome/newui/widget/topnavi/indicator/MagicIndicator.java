package com.xiaomi.smarthome.newui.widget.topnavi.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.abs.IPagerNavigator;

public class MagicIndicator extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private IPagerNavigator f20923a;

    public MagicIndicator(Context context) {
        super(context);
    }

    public MagicIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.f20923a != null) {
            this.f20923a.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.f20923a != null) {
            this.f20923a.onPageSelected(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.f20923a != null) {
            this.f20923a.onPageScrollStateChanged(i);
        }
    }

    public IPagerNavigator getNavigator() {
        return this.f20923a;
    }

    public void setNavigator(IPagerNavigator iPagerNavigator) {
        if (this.f20923a != iPagerNavigator) {
            if (this.f20923a != null) {
                this.f20923a.onDetachFromMagicIndicator();
            }
            this.f20923a = iPagerNavigator;
            removeAllViews();
            if (this.f20923a instanceof View) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ((View) this.f20923a).getLayoutParams();
                if (layoutParams == null) {
                    if (this.f20923a.isCompactMode()) {
                        layoutParams = new FrameLayout.LayoutParams(-1, -2);
                    } else {
                        layoutParams = new FrameLayout.LayoutParams(-1, -1);
                    }
                }
                addView((View) this.f20923a, layoutParams);
                this.f20923a.onAttachToMagicIndicator();
            }
        }
    }
}
