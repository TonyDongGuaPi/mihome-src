package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class AutoScrollViewPager extends ViewPager {
    public static final int DEFAULT_INTERVAL = 5000;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int SCROLL_WHAT = 0;
    public static final int SLIDE_BORDER_MODE_CYCLE = 1;
    public static final int SLIDE_BORDER_MODE_NONE = 0;
    public static final int SLIDE_BORDER_MODE_TO_PARENT = 2;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public long f7149a = 5000;
    private int b = 1;
    private boolean c = true;
    private boolean d = true;
    private boolean e = false;
    private int f = 0;
    private boolean g = true;
    private Handler h;
    private boolean i = false;
    private boolean j = false;
    private float k = 0.0f;
    private float l = 0.0f;
    private Scroller m = null;

    public AutoScrollViewPager(Context context) {
        super(context);
        a();
    }

    public AutoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.h = new MyHandler(this);
        b();
    }

    public void startAutoScroll() {
        this.i = true;
        a(this.f7149a);
    }

    public void startAutoScroll(int i2) {
        this.i = true;
        a((long) i2);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            startAutoScroll();
        } else {
            stopAutoScroll();
        }
    }

    public void stopAutoScroll() {
        this.i = false;
        this.h.removeMessages(0);
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        this.h.removeMessages(0);
        this.h.sendEmptyMessageDelayed(0, j2);
    }

    private void b() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            this.m = new CustomDurationScroller(getContext(), (Interpolator) declaredField2.get((Object) null));
            declaredField.set(this, this.m);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setViewPagerScroller() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            this.m = new Scroller(getContext(), (Interpolator) declaredField2.get((Object) null));
            declaredField.set(this, this.m);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void scrollOnce() {
        int count;
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        if (rect.top > 0) {
            PagerAdapter adapter = getAdapter();
            int currentItem = getCurrentItem();
            if (adapter != null && (count = adapter.getCount()) > 1) {
                int i2 = this.b == 0 ? currentItem - 1 : currentItem + 1;
                if (i2 < 0) {
                    if (this.c) {
                        setCurrentItem(count - 1, this.g);
                    }
                } else if (i2 != count) {
                    setCurrentItem(i2, true);
                } else if (this.c) {
                    setCurrentItem(0, this.g);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i2;
        try {
            if (this.d && motionEvent.getAction() == 1 && this.j && !isStopScrollWhenTouched()) {
                startAutoScroll();
            }
            if (this.f == 2 || this.f == 1) {
                this.k = motionEvent.getX();
                if (motionEvent.getAction() == 0) {
                    this.l = this.k;
                }
                int currentItem = getCurrentItem();
                PagerAdapter adapter = getAdapter();
                if (adapter == null) {
                    i2 = 0;
                } else {
                    i2 = adapter.getCount();
                }
                if ((currentItem == 0 && this.l <= this.k) || (currentItem == i2 - 1 && this.l >= this.k)) {
                    if (this.f == 2) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        if (i2 > 1) {
                            setCurrentItem((i2 - currentItem) - 1, this.g);
                        }
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    return super.onTouchEvent(motionEvent);
                }
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.d && motionEvent.getAction() == 0 && this.i) {
                this.j = true;
                stopAutoScroll();
            }
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<AutoScrollViewPager> f7150a;

        public MyHandler(AutoScrollViewPager autoScrollViewPager) {
            this.f7150a = new WeakReference<>(autoScrollViewPager);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            AutoScrollViewPager autoScrollViewPager = (AutoScrollViewPager) this.f7150a.get();
            if (autoScrollViewPager != null && message.what == 0) {
                autoScrollViewPager.scrollOnce();
                autoScrollViewPager.a(autoScrollViewPager.f7149a);
            }
        }
    }

    public long getInterval() {
        return this.f7149a;
    }

    public void setInterval(long j2) {
        this.f7149a = j2;
    }

    public int getDirection() {
        return this.b == 0 ? 0 : 1;
    }

    public void setDirection(int i2) {
        this.b = i2;
    }

    public boolean isCycle() {
        return this.c;
    }

    public void setCycle(boolean z) {
        this.c = z;
    }

    public boolean isStopScrollWhenTouch() {
        return this.d;
    }

    public void setStopScrollWhenTouch(boolean z) {
        this.d = z;
    }

    public boolean isStopScrollWhenTouched() {
        return this.e;
    }

    public void setStopScrollWhenTouched(boolean z) {
        this.e = z;
    }

    public int getSlideBorderMode() {
        return this.f;
    }

    public void setSlideBorderMode(int i2) {
        this.f = i2;
    }

    public boolean isBorderAnimation() {
        return this.g;
    }

    public void setBorderAnimation(boolean z) {
        this.g = z;
    }
}
