package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.payment.platform.R;

public class ViewPagerIndicatorBar extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private int f12469a;
    private Context b;
    private int c;

    public ViewPagerIndicatorBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public ViewPagerIndicatorBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        a();
    }

    private void a() {
        this.f12469a = getResources().getDimensionPixelSize(R.dimen.mibi_viewpager_indicator_bar_height);
        setOrientation(0);
    }

    public void setIndicatorNum(int i) {
        setIndicatorNum(i, 0);
    }

    public void setIndicatorNum(int i, int i2) {
        if (i >= 0) {
            this.c = i;
            if (i2 <= 0) {
                i2 = 0;
            } else if (i2 >= i) {
                i2 = i - 1;
            }
            removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
            layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.mibi_viewpager_indicator_item_interval), 0, getResources().getDimensionPixelSize(R.dimen.mibi_viewpager_indicator_item_interval), 0);
            int i3 = 0;
            while (i3 < i) {
                ImageView imageView = new ImageView(this.b);
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.mibi_viewpager_indicator_item_bg));
                imageView.setSelected(i3 == i2);
                addView(imageView, layoutParams);
                i3++;
            }
        }
    }

    public void setSelected(int i) {
        if (i >= 0 && i < this.c) {
            int i2 = 0;
            while (i2 < getChildCount()) {
                ((ImageView) getChildAt(i2)).setSelected(i2 == i);
                i2++;
            }
        }
    }
}
