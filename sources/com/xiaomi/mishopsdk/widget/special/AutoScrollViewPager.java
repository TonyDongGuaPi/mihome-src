package com.xiaomi.mishopsdk.widget.special;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
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
    public double autoScrollFactor = 4.5d;
    private int direction = 1;
    private float downX = 0.0f;
    private Handler handler;
    /* access modifiers changed from: private */
    public long interval = 5000;
    /* access modifiers changed from: private */
    public boolean isAutoScroll = false;
    private boolean isAutoScrollBefore = false;
    private boolean isBorderAnimation = true;
    private boolean isCycle = true;
    private boolean isStopByTouch = false;
    /* access modifiers changed from: private */
    public CustomDurationScroller scroller = null;
    private int slideBorderMode = 0;
    private boolean stopScrollWhenTouch = true;
    private boolean stopScrollWhenTouched = false;
    /* access modifiers changed from: private */
    public double swipeScrollFactor = 1.0d;
    private float touchX = 0.0f;

    public AutoScrollViewPager(Context context) {
        super(context);
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.handler = new MyHandler(this);
        setViewPagerCustomScroller();
    }

    public void startAutoScroll() {
        this.isAutoScroll = true;
        sendScrollMessage(this.interval);
    }

    public void startAutoScroll(int i) {
        this.isAutoScroll = true;
        double d = (double) this.interval;
        double duration = (double) this.scroller.getDuration();
        double d2 = this.autoScrollFactor;
        Double.isNaN(duration);
        Double.isNaN(d);
        sendScrollMessage((long) (d + ((duration / d2) * this.swipeScrollFactor)));
    }

    public void stopAutoScroll() {
        this.isAutoScroll = false;
        this.handler.removeMessages(0);
    }

    public void setSwipeScrollDurationFactor(double d) {
        this.swipeScrollFactor = d;
    }

    public void setAutoScrollDurationFactor(double d) {
        this.autoScrollFactor = d;
    }

    /* access modifiers changed from: private */
    public void sendScrollMessage(long j) {
        this.handler.removeMessages(0);
        this.handler.sendEmptyMessageDelayed(0, j);
    }

    private void setViewPagerCustomScroller() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.scroller = new CustomDurationScroller(getContext(), new CubicBezierInterpolator(0.4d, 0.0d, 0.2d, 1.0d));
            declaredField.set(this, this.scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollOnce() {
        int count;
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        if (adapter != null && (count = adapter.getCount()) > 1) {
            int i = this.direction == 0 ? currentItem - 1 : currentItem + 1;
            if (i < 0) {
                if (this.isCycle) {
                    setCurrentItem(count - 1, this.isBorderAnimation);
                }
            } else if (i != count) {
                setCurrentItem(i, true);
            } else if (this.isCycle) {
                setCurrentItem(0, this.isBorderAnimation);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        if (getChildCount() == 0) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.stopScrollWhenTouch) {
            if (actionMasked == 0 && this.isAutoScroll) {
                this.isStopByTouch = true;
                stopAutoScroll();
            } else if (motionEvent.getAction() == 1 && this.isStopByTouch) {
                startAutoScroll();
            }
        }
        if (this.slideBorderMode == 2 || this.slideBorderMode == 1) {
            this.touchX = motionEvent.getX();
            if (motionEvent.getAction() == 0) {
                this.downX = this.touchX;
            }
            int currentItem = getCurrentItem();
            PagerAdapter adapter = getAdapter();
            if (adapter == null) {
                i = 0;
            } else {
                i = adapter.getCount();
            }
            if ((currentItem == 0 && this.downX <= this.touchX) || (currentItem == i - 1 && this.downX >= this.touchX)) {
                if (this.slideBorderMode == 2) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    if (i > 1) {
                        setCurrentItem((i - currentItem) - 1, this.isBorderAnimation);
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return super.onTouchEvent(motionEvent);
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(motionEvent);
    }

    private class MyHandler extends Handler {
        private final WeakReference<AutoScrollViewPager> mAutoScrollViewPagerWeakReference;

        private MyHandler(AutoScrollViewPager autoScrollViewPager) {
            this.mAutoScrollViewPagerWeakReference = new WeakReference<>(autoScrollViewPager);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                if (AutoScrollViewPager.this.activityFinished() || !AutoScrollViewPager.this.isAutoScroll) {
                    AutoScrollViewPager.this.stopAutoScroll();
                    return;
                }
                AutoScrollViewPager autoScrollViewPager = (AutoScrollViewPager) this.mAutoScrollViewPagerWeakReference.get();
                if (autoScrollViewPager != null) {
                    autoScrollViewPager.scroller.setScrollDurationFactor(autoScrollViewPager.autoScrollFactor);
                    autoScrollViewPager.scrollOnce();
                    autoScrollViewPager.scroller.setScrollDurationFactor(autoScrollViewPager.swipeScrollFactor);
                    autoScrollViewPager.sendScrollMessage(autoScrollViewPager.interval + ((long) autoScrollViewPager.scroller.getDuration()));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean activityFinished() {
        Context context = getContext();
        return (context instanceof Activity) && ((Activity) context).isFinishing();
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long j) {
        this.interval = j;
    }

    public int getDirection() {
        return this.direction == 0 ? 0 : 1;
    }

    public void setDirection(int i) {
        this.direction = i;
    }

    public boolean isCycle() {
        return this.isCycle;
    }

    public void setCycle(boolean z) {
        this.isCycle = z;
    }

    public boolean isStopScrollWhenTouch() {
        return this.stopScrollWhenTouch;
    }

    public void setStopScrollWhenTouch(boolean z) {
        this.stopScrollWhenTouch = z;
    }

    public boolean isStopScrollWhenTouched() {
        return this.stopScrollWhenTouched;
    }

    public void setStopScrollWhenTouched(boolean z) {
        this.stopScrollWhenTouched = z;
    }

    public int getSlideBorderMode() {
        return this.slideBorderMode;
    }

    public void setSlideBorderMode(int i) {
        this.slideBorderMode = i;
    }

    public boolean isBorderAnimation() {
        return this.isBorderAnimation;
    }

    public void setBorderAnimation(boolean z) {
        this.isBorderAnimation = z;
    }

    public void onDetachedFromWindow() {
        stopAutoScroll();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 4) {
            this.isAutoScrollBefore = this.isAutoScroll;
            if (this.isAutoScroll) {
                stopAutoScroll();
            }
        } else if (i == 0 && this.isAutoScrollBefore && !this.isAutoScroll) {
            startAutoScroll();
        }
    }
}
