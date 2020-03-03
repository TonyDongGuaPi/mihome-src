package com.bigkoo.convenientbanner.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

public class CBLoopViewPager extends ViewPager {
    private static final float g = 5.0f;

    /* renamed from: a  reason: collision with root package name */
    private OnItemClickListener f4793a;
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
            if (CBLoopViewPager.this.mOuterPageChangeListener == null) {
                return;
            }
            if (i != CBLoopViewPager.this.b.a() - 1) {
                CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, f, i2);
            } else if (((double) f) > 0.5d) {
                CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
            } else {
                CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, 0.0f, 0);
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
        if (!this.c) {
            return false;
        }
        if (this.f4793a != null) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.e = motionEvent.getX();
                    break;
                case 1:
                    this.f = motionEvent.getX();
                    if (Math.abs(this.e - this.f) < g) {
                        this.f4793a.onItemClick(getRealItem());
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
        this.f4793a = onItemClickListener;
    }
}
