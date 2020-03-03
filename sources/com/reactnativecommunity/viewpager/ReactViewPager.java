package com.reactnativecommunity.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import java.util.ArrayList;
import java.util.List;

public class ReactViewPager extends ViewPager {
    /* access modifiers changed from: private */
    public final EventDispatcher mEventDispatcher;
    /* access modifiers changed from: private */
    public boolean mIsCurrentItemFromJs;
    private boolean mScrollEnabled = true;
    /* access modifiers changed from: private */
    public final Runnable measureAndLayout = new Runnable() {
        public void run() {
            ReactViewPager.this.measure(View.MeasureSpec.makeMeasureSpec(ReactViewPager.this.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactViewPager.this.getHeight(), 1073741824));
            ReactViewPager.this.layout(ReactViewPager.this.getLeft(), ReactViewPager.this.getTop(), ReactViewPager.this.getRight(), ReactViewPager.this.getBottom());
        }
    };

    private class Adapter extends PagerAdapter {
        private final List<View> b;
        private boolean c;

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private Adapter() {
            this.b = new ArrayList();
            this.c = false;
        }

        /* access modifiers changed from: package-private */
        public void a(View view, int i) {
            this.b.add(i, view);
            notifyDataSetChanged();
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.b.remove(i);
            notifyDataSetChanged();
        }

        /* access modifiers changed from: package-private */
        public void a(List<View> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
            this.c = false;
        }

        /* access modifiers changed from: package-private */
        public void a(ViewPager viewPager) {
            this.b.clear();
            viewPager.removeAllViews();
            this.c = true;
        }

        /* access modifiers changed from: package-private */
        public View b(int i) {
            return this.b.get(i);
        }

        public int getCount() {
            return this.b.size();
        }

        public int getItemPosition(Object obj) {
            if (this.c || !this.b.contains(obj)) {
                return -2;
            }
            return this.b.indexOf(obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = this.b.get(i);
            viewGroup.addView(view, 0, ReactViewPager.this.generateDefaultLayoutParams());
            ReactViewPager.this.post(ReactViewPager.this.measureAndLayout);
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        private PageChangeListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageScrollEvent(ReactViewPager.this.getId(), i, f));
        }

        public void onPageSelected(int i) {
            if (!ReactViewPager.this.mIsCurrentItemFromJs) {
                ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageSelectedEvent(ReactViewPager.this.getId(), i));
            }
        }

        public void onPageScrollStateChanged(int i) {
            String str;
            switch (i) {
                case 0:
                    str = PrinterParmater.i;
                    break;
                case 1:
                    str = "dragging";
                    break;
                case 2:
                    str = "settling";
                    break;
                default:
                    throw new IllegalStateException("Unsupported pageScrollState");
            }
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageScrollStateChangedEvent(ReactViewPager.this.getId(), str));
        }
    }

    public ReactViewPager(ReactContext reactContext) {
        super(reactContext);
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.mIsCurrentItemFromJs = false;
        setOnPageChangeListener(new PageChangeListener());
        setAdapter(new Adapter());
    }

    public Adapter getAdapter() {
        return (Adapter) super.getAdapter();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                return true;
            }
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", (Throwable) e);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error handling touch event.", (Throwable) e);
            return false;
        }
    }

    public void setCurrentItemFromJs(int i, boolean z) {
        this.mIsCurrentItemFromJs = true;
        setCurrentItem(i, z);
        this.mEventDispatcher.dispatchEvent(new PageSelectedEvent(getId(), i));
        this.mIsCurrentItemFromJs = false;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestLayout();
        post(this.measureAndLayout);
    }

    /* access modifiers changed from: package-private */
    public void addViewToAdapter(View view, int i) {
        getAdapter().a(view, i);
    }

    /* access modifiers changed from: package-private */
    public void removeViewFromAdapter(int i) {
        getAdapter().a(i);
    }

    /* access modifiers changed from: package-private */
    public int getViewCountInAdapter() {
        return getAdapter().getCount();
    }

    /* access modifiers changed from: package-private */
    public View getViewFromAdapter(int i) {
        return getAdapter().b(i);
    }

    public void setViews(List<View> list) {
        getAdapter().a(list);
    }

    public void removeAllViewsFromAdapter() {
        getAdapter().a((ViewPager) this);
    }
}
