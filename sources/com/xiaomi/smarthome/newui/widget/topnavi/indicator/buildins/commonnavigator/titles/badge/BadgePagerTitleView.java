package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.titles.badge;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerTitleView;

public class BadgePagerTitleView extends FrameLayout implements IMeasurablePagerTitleView {

    /* renamed from: a  reason: collision with root package name */
    private IPagerTitleView f20938a;
    private View b;
    private boolean c = true;
    private BadgeRule d;
    private BadgeRule e;

    public BadgePagerTitleView(Context context) {
        super(context);
    }

    public void onSelected(int i, int i2) {
        if (this.f20938a != null) {
            this.f20938a.onSelected(i, i2);
        }
        if (this.c) {
            setBadgeView((View) null);
        }
    }

    public void onDeselected(int i, int i2) {
        if (this.f20938a != null) {
            this.f20938a.onDeselected(i, i2);
        }
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        if (this.f20938a != null) {
            this.f20938a.onLeave(i, i2, f, z);
        }
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        if (this.f20938a != null) {
            this.f20938a.onEnter(i, i2, f, z);
        }
    }

    public IPagerTitleView getInnerPagerTitleView() {
        return this.f20938a;
    }

    public void setInnerPagerTitleView(IPagerTitleView iPagerTitleView) {
        if (this.f20938a != iPagerTitleView) {
            this.f20938a = iPagerTitleView;
            removeAllViews();
            if (this.f20938a instanceof View) {
                addView((View) this.f20938a, new FrameLayout.LayoutParams(-1, -1));
            }
            if (this.b != null) {
                addView(this.b, new FrameLayout.LayoutParams(-2, -2));
            }
        }
    }

    public View getBadgeView() {
        return this.b;
    }

    public void setBadgeView(View view) {
        if (this.b != view) {
            this.b = view;
            removeAllViews();
            if (this.f20938a instanceof View) {
                addView((View) this.f20938a, new FrameLayout.LayoutParams(-1, -1));
            }
            if (this.b != null) {
                addView(this.b, new FrameLayout.LayoutParams(-2, -2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if ((this.f20938a instanceof View) && this.b != null) {
            int[] iArr = new int[14];
            View view = (View) this.f20938a;
            iArr[0] = view.getLeft();
            iArr[1] = view.getTop();
            iArr[2] = view.getRight();
            iArr[3] = view.getBottom();
            if (this.f20938a instanceof IMeasurablePagerTitleView) {
                IMeasurablePagerTitleView iMeasurablePagerTitleView = (IMeasurablePagerTitleView) this.f20938a;
                iArr[4] = iMeasurablePagerTitleView.getContentLeft();
                iArr[5] = iMeasurablePagerTitleView.getContentTop();
                iArr[6] = iMeasurablePagerTitleView.getContentRight();
                iArr[7] = iMeasurablePagerTitleView.getContentBottom();
            } else {
                for (int i5 = 4; i5 < 8; i5++) {
                    iArr[i5] = iArr[i5 - 4];
                }
            }
            iArr[8] = view.getWidth() / 2;
            iArr[9] = view.getHeight() / 2;
            iArr[10] = iArr[4] / 2;
            iArr[11] = iArr[5] / 2;
            iArr[12] = iArr[6] + ((iArr[2] - iArr[6]) / 2);
            iArr[13] = iArr[7] + ((iArr[3] - iArr[7]) / 2);
            if (this.d != null) {
                this.b.offsetLeftAndRight((iArr[this.d.a().ordinal()] + this.d.b()) - this.b.getLeft());
            }
            if (this.e != null) {
                this.b.offsetTopAndBottom((iArr[this.e.a().ordinal()] + this.e.b()) - this.b.getTop());
            }
        }
    }

    public int getContentLeft() {
        if (this.f20938a instanceof IMeasurablePagerTitleView) {
            return getLeft() + ((IMeasurablePagerTitleView) this.f20938a).getContentLeft();
        }
        return getLeft();
    }

    public int getContentTop() {
        if (this.f20938a instanceof IMeasurablePagerTitleView) {
            return ((IMeasurablePagerTitleView) this.f20938a).getContentTop();
        }
        return getTop();
    }

    public int getContentRight() {
        if (this.f20938a instanceof IMeasurablePagerTitleView) {
            return getLeft() + ((IMeasurablePagerTitleView) this.f20938a).getContentRight();
        }
        return getRight();
    }

    public int getContentBottom() {
        if (this.f20938a instanceof IMeasurablePagerTitleView) {
            return ((IMeasurablePagerTitleView) this.f20938a).getContentBottom();
        }
        return getBottom();
    }

    public BadgeRule getXBadgeRule() {
        return this.d;
    }

    public void setXBadgeRule(BadgeRule badgeRule) {
        BadgeAnchor a2;
        if (badgeRule == null || (a2 = badgeRule.a()) == BadgeAnchor.LEFT || a2 == BadgeAnchor.RIGHT || a2 == BadgeAnchor.CONTENT_LEFT || a2 == BadgeAnchor.CONTENT_RIGHT || a2 == BadgeAnchor.CENTER_X || a2 == BadgeAnchor.LEFT_EDGE_CENTER_X || a2 == BadgeAnchor.RIGHT_EDGE_CENTER_X) {
            this.d = badgeRule;
            return;
        }
        throw new IllegalArgumentException("x badge rule is wrong.");
    }

    public BadgeRule getYBadgeRule() {
        return this.e;
    }

    public void setYBadgeRule(BadgeRule badgeRule) {
        BadgeAnchor a2;
        if (badgeRule == null || (a2 = badgeRule.a()) == BadgeAnchor.TOP || a2 == BadgeAnchor.BOTTOM || a2 == BadgeAnchor.CONTENT_TOP || a2 == BadgeAnchor.CONTENT_BOTTOM || a2 == BadgeAnchor.CENTER_Y || a2 == BadgeAnchor.TOP_EDGE_CENTER_Y || a2 == BadgeAnchor.BOTTOM_EDGE_CENTER_Y) {
            this.e = badgeRule;
            return;
        }
        throw new IllegalArgumentException("y badge rule is wrong.");
    }

    public boolean isAutoCancelBadge() {
        return this.c;
    }

    public void setAutoCancelBadge(boolean z) {
        this.c = z;
    }
}
