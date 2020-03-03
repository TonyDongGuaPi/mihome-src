package com.mi.global.bbs.utils;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import com.bigkoo.convenientbanner.ViewPagerScroller;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class TinyTimer {
    private static final int MSG_LOOP = 16;
    private long mDuration = 4000;
    /* access modifiers changed from: private */
    public WeakReference<CBLoopViewPager> mPagerWeakReference;
    private SimpleTimer mTimer = new SimpleTimer();
    /* access modifiers changed from: private */
    public boolean running = false;

    public void setPager(CBLoopViewPager cBLoopViewPager) {
        this.mPagerWeakReference = new WeakReference<>(cBLoopViewPager);
        initViewPagerScroll(cBLoopViewPager);
    }

    private void initViewPagerScroll(CBLoopViewPager cBLoopViewPager) {
        cBLoopViewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3 || action == 4) {
                    TinyTimer.this.start();
                    return false;
                } else if (action != 0) {
                    return false;
                } else {
                    TinyTimer.this.stop();
                    return false;
                }
            }
        });
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(cBLoopViewPager, new ViewPagerScroller(cBLoopViewPager.getContext()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        stop();
        start();
    }

    private final class SimpleTimer extends Handler {
        private SimpleTimer() {
        }

        public void handleMessage(Message message) {
            if (message.what == 16) {
                CBLoopViewPager cBLoopViewPager = TinyTimer.this.mPagerWeakReference != null ? (CBLoopViewPager) TinyTimer.this.mPagerWeakReference.get() : null;
                if (cBLoopViewPager == null || !TinyTimer.this.running) {
                    TinyTimer.this.stop();
                    return;
                }
                cBLoopViewPager.setCurrentItem(cBLoopViewPager.getCurrentItem() + 1);
                TinyTimer.this.start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void start() {
        this.running = true;
        if (this.mTimer != null) {
            this.mTimer.sendEmptyMessageDelayed(16, this.mDuration);
        }
    }

    public void stop() {
        this.running = false;
        if (this.mTimer != null) {
            this.mTimer.removeMessages(16);
        }
    }

    public void release() {
        stop();
        this.mTimer = null;
    }
}
