package com.xiaomi.smarthome.library.common.pageindicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IconPageIndicator extends HorizontalScrollView implements PageIndicator {

    /* renamed from: a  reason: collision with root package name */
    private final LinearLayout f18644a;
    private ViewPager b;
    private ViewPager.OnPageChangeListener c;
    /* access modifiers changed from: private */
    public Runnable d;
    private int e;

    public IconPageIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHorizontalScrollBarEnabled(false);
        this.f18644a = new LinearLayout(context);
        this.f18644a.setOrientation(0);
        addView(this.f18644a, new FrameLayout.LayoutParams(-2, -1, 17));
    }

    private void a(int i) {
        final View childAt = this.f18644a.getChildAt(i);
        if (this.d != null) {
            removeCallbacks(this.d);
        }
        this.d = new Runnable() {
            public void run() {
                IconPageIndicator.this.smoothScrollTo(childAt.getLeft() - ((IconPageIndicator.this.getWidth() - childAt.getWidth()) / 2), 0);
                Runnable unused = IconPageIndicator.this.d = null;
            }
        };
        post(this.d);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d != null) {
            post(this.d);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            removeCallbacks(this.d);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.c != null) {
            this.c.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.c != null) {
            this.c.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        setCurrentItem(i);
        if (this.c != null) {
            this.c.onPageSelected(i);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.b != viewPager) {
            if (this.b != null) {
                this.b.setOnPageChangeListener((ViewPager.OnPageChangeListener) null);
            }
            if (viewPager.getAdapter() != null) {
                this.b = viewPager;
                viewPager.setOnPageChangeListener(this);
                notifyDataSetChanged();
                return;
            }
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.b != null) {
            this.e = i;
            this.b.setCurrentItem(i);
            int childCount = this.f18644a.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.f18644a.getChildAt(i2);
                boolean z = i2 == i;
                childAt.setSelected(z);
                if (z) {
                    a(i);
                }
                i2++;
            }
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void notifyDataSetChanged() {
        this.f18644a.removeAllViews();
        IconPageAdapter iconPageAdapter = (IconPageAdapter) this.b.getAdapter();
        int a2 = iconPageAdapter.a();
        for (int i = 0; i < a2; i++) {
            ImageView imageView = new ImageView(getContext(), (AttributeSet) null);
            imageView.setImageResource(iconPageAdapter.a(i));
            if (i > 0) {
                imageView.setPadding((int) (getResources().getDisplayMetrics().density * 10.0f), 0, 0, 0);
            }
            this.f18644a.addView(imageView);
        }
        if (this.e > a2) {
            this.e = a2 - 1;
        }
        setCurrentItem(this.e);
        requestLayout();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.c = onPageChangeListener;
    }
}
