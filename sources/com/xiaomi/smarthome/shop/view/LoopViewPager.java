package com.xiaomi.smarthome.shop.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.miio.Miio;

public class LoopViewPager extends ViewPager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f22206a = "LoopViewPager";
    private static final long b = 3000;
    private static final boolean c = true;
    private static final int d = 0;
    /* access modifiers changed from: private */
    public ViewPager.OnPageChangeListener e;
    /* access modifiers changed from: private */
    public LoopPagerAdapterWrapper f;
    /* access modifiers changed from: private */
    public long g = b;
    /* access modifiers changed from: private */
    public Handler h;
    private boolean i = true;
    private boolean j = false;
    private boolean k = false;
    private ViewPager.OnPageChangeListener l = new ViewPager.OnPageChangeListener() {
        private float b = -1.0f;
        private float c = -1.0f;

        public void onPageSelected(int i) {
            int a2 = LoopViewPager.this.f.a(i);
            float f = (float) a2;
            if (this.c != f) {
                this.c = f;
                if (LoopViewPager.this.e != null) {
                    LoopViewPager.this.e.onPageSelected(a2);
                }
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (LoopViewPager.this.f != null) {
                i = LoopViewPager.this.f.a(i);
            }
            this.b = f;
            if (LoopViewPager.this.e != null && LoopViewPager.this.f != null) {
                if (i != LoopViewPager.this.f.a() - 1) {
                    LoopViewPager.this.e.onPageScrolled(i, f, i2);
                } else if (((double) f) > 0.5d) {
                    LoopViewPager.this.e.onPageScrolled(0, 0.0f, 0);
                } else {
                    LoopViewPager.this.e.onPageScrolled(i, 0.0f, 0);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (LoopViewPager.this.f != null) {
                int access$501 = LoopViewPager.super.getCurrentItem();
                int a2 = LoopViewPager.this.f.a(access$501);
                if (i == 0 && (access$501 == 0 || access$501 == LoopViewPager.this.f.getCount() - 1)) {
                    Miio.h(LoopViewPager.f22206a, "setCurrentItem(realPosition, false);");
                    LoopViewPager.this.setCurrentItem(a2, false);
                }
            }
            if (LoopViewPager.this.e != null) {
                LoopViewPager.this.e.onPageScrollStateChanged(i);
            }
        }
    };

    public LoopViewPager(Context context) {
        super(context);
        a(context, (AttributeSet) null);
    }

    public LoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        super.setOnPageChangeListener(this.l);
        this.h = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message message) {
                if (message.what != 0) {
                    return false;
                }
                LoopViewPager.this.a();
                LoopViewPager.this.h.sendEmptyMessageDelayed(0, LoopViewPager.this.g);
                return true;
            }
        });
    }

    public void setEnableAutoScroll(boolean z) {
        this.i = z;
        if (!this.i) {
            stopAutoScroll();
        }
    }

    public void startAutoScroll(long j2) {
        this.g = j2;
        startAutoScroll();
    }

    public void startAutoScroll() {
        this.j = true;
        if (!this.h.hasMessages(0)) {
            this.h.sendEmptyMessageDelayed(0, this.g);
        }
    }

    public void stopAutoScroll() {
        this.j = false;
        this.h.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public void a() {
        int currentItem = getCurrentItem();
        if (this.f != null && this.f.getCount() > 1) {
            int i2 = currentItem + 1;
            if (i2 < this.f.getCount() - 1) {
                setCurrentItem(i2, true);
            } else {
                setCurrentItem(0, true);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (pagerAdapter != null) {
            this.f = new LoopPagerAdapterWrapper(pagerAdapter);
            super.setAdapter(this.f);
        }
    }

    public PagerAdapter getAdapter() {
        if (this.f != null) {
            return this.f.b();
        }
        return null;
    }

    public int getCurrentItem() {
        if (this.f != null) {
            return this.f.a(super.getCurrentItem());
        }
        return 0;
    }

    public void setCurrentItem(int i2, boolean z) {
        if (this.f != null) {
            super.setCurrentItem(this.f.b(i2), z);
        }
    }

    public void setCurrentItem(int i2) {
        if (getCurrentItem() != i2) {
            setCurrentItem(i2, true);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.j) {
            this.k = true;
            stopAutoScroll();
        } else if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && this.k) {
            this.k = false;
            startAutoScroll();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.e = onPageChangeListener;
    }
}
