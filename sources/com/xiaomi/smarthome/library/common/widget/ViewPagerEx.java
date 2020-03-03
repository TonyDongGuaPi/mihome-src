package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Field;

public class ViewPagerEx extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private onPagerFinishedListener f18974a;
    private Boolean b = true;
    protected ScrollerCustomDuration mScroller = null;

    public interface onPagerFinishedListener {
        void a();
    }

    public ViewPagerEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ViewPagerEx(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        Class<ViewPager> cls = ViewPager.class;
        try {
            Field declaredField = cls.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            this.mScroller = new ScrollerCustomDuration(context, (Interpolator) declaredField2.get((Object) null));
            declaredField.set(this, this.mScroller);
        } catch (Exception unused) {
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.b.booleanValue()) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.b.booleanValue()) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isScrollable() {
        return this.b.booleanValue();
    }

    public void setScrollable(boolean z) {
        this.b = Boolean.valueOf(z);
    }

    public void setViewPagerDuration(int i) {
        this.mScroller.a(i);
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset() && !this.mScroller.computeScrollOffset() && this.f18974a != null) {
            this.f18974a.a();
        }
        super.computeScroll();
    }

    public Scroller getScroller() {
        return this.mScroller;
    }

    public void setPagerFinishedListener(onPagerFinishedListener onpagerfinishedlistener) {
        this.f18974a = onpagerfinishedlistener;
    }

    private class ScrollerCustomDuration extends Scroller {
        private int b = 500;

        public ScrollerCustomDuration(Context context) {
            super(context);
        }

        public ScrollerCustomDuration(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean z) {
            super(context, interpolator);
        }

        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.b);
        }

        public void a(int i) {
            this.b = i;
        }
    }
}
