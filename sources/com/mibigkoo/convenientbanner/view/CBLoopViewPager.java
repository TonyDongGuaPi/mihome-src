package com.mibigkoo.convenientbanner.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.mibigkoo.convenientbanner.adapter.CBPageAdapter;
import com.mibigkoo.convenientbanner.listener.OnItemClickListener;

public class CBLoopViewPager extends ViewPager {
    private static final float g = 5.0f;

    /* renamed from: a  reason: collision with root package name */
    private OnItemClickListener f7620a;
    /* access modifiers changed from: private */
    public CBPageAdapter b;
    private boolean c = true;
    private boolean d = true;
    private float e = 0.0f;
    private float f = 0.0f;
    private ViewPager.OnPageChangeListener h = new ViewPager.OnPageChangeListener() {
        private float b = -1.0f;

        public void onPageSelected(int i) {
            int a2 = CBLoopViewPager.this.b.a(i);
            float f = (float) a2;
            if (this.b != f) {
                this.b = f;
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    CBLoopViewPager.this.mOuterPageChangeListener.onPageSelected(a2);
                }
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, f, i2);
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                CBLoopViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i);
            }
        }
    };
    ViewPager.OnPageChangeListener mOuterPageChangeListener;

    public void setAdapter(PagerAdapter pagerAdapter, boolean z) {
        this.b = (CBPageAdapter) pagerAdapter;
        this.b.a(z);
        this.b.a(this);
        super.setAdapter(this.b);
        setCurrentItem(getFristItem(), false);
    }

    public int getFristItem() {
        if (this.d) {
            return this.b.a();
        }
        return 0;
    }

    public int getLastItem() {
        return this.b.a() - 1;
    }

    public boolean isCanScroll() {
        return this.c;
    }

    public void setCanScroll(boolean z) {
        this.c = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.c || getChildCount() == 0) {
            return false;
        }
        if (this.f7620a != null) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.e = motionEvent.getX();
                    break;
                case 1:
                    this.f = motionEvent.getX();
                    if (Math.abs(this.e - this.f) < g) {
                        this.f7620a.onItemClick(getRealItem());
                    }
                    this.e = 0.0f;
                    this.f = 0.0f;
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.c) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public CBPageAdapter getAdapter() {
        return this.b;
    }

    public int getRealItem() {
        if (this.b != null) {
            return this.b.a(super.getCurrentItem());
        }
        return 0;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOuterPageChangeListener = onPageChangeListener;
    }

    public CBLoopViewPager(Context context) {
        super(context);
        a();
    }

    public CBLoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        super.setOnPageChangeListener(this.h);
    }

    public boolean isCanLoop() {
        return this.d;
    }

    public void setCanLoop(boolean z) {
        this.d = z;
        if (!z) {
            setCurrentItem(getRealItem(), false);
        }
        if (this.b != null) {
            this.b.a(z);
            this.b.notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f7620a = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.f7620a;
    }
}
