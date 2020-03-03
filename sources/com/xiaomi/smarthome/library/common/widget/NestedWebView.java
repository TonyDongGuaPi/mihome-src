package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class NestedWebView extends WebView implements NestedScrollingChild {

    /* renamed from: a  reason: collision with root package name */
    private NestedScrollingChildHelper f18900a;
    private GestureDetector b;
    GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return motionEvent.getX() >= 1200.0f || motionEvent.getX() <= 80.0f;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            LinearLayout linearLayout = (LinearLayout) NestedWebView.this.getParent();
            int scrollY = (int) ((((float) NestedWebView.this.getScrollY()) - motionEvent.getY()) + motionEvent2.getY());
            if (scrollY > 0 || NestedWebView.this.getHeight() >= linearLayout.getHeight()) {
                return false;
            }
            NestedWebView.this.dispatchNestedScroll(0, 0, 0, scrollY, (int[]) null);
            return true;
        }
    };

    public NestedWebView(Context context) {
        super(context);
        a();
    }

    public NestedWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public NestedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.f18900a = new NestedScrollingChildHelper(this);
        this.b = new GestureDetector(getContext(), this.mGestureListener);
        setNestedScrollingEnabled(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                startNestedScroll(2);
                break;
            case 1:
                stopNestedScroll();
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.f18900a.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.f18900a.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return this.f18900a.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.f18900a.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.f18900a.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f18900a.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f18900a.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f18900a.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f18900a.dispatchNestedPreFling(f, f2);
    }
}
