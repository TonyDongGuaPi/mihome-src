package com.xiaomi.smarthome.lite.scene;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class HomeSceneScrollView extends ScrollView {

    /* renamed from: a  reason: collision with root package name */
    private int f19379a;
    private int b;
    private int c;
    private OnScrollListener d;

    public interface OnScrollListener {
        void onScrollY(int i);
    }

    /* access modifiers changed from: protected */
    public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    public HomeSceneScrollView(Context context) {
        super(context);
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public HomeSceneScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public HomeSceneScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f19379a = (int) motionEvent.getRawX();
            this.b = (int) motionEvent.getRawY();
        } else if (action == 2 && Math.abs(((int) motionEvent.getRawY()) - this.b) > this.c) {
            super.onInterceptTouchEvent(motionEvent);
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setListener(OnScrollListener onScrollListener) {
        this.d = onScrollListener;
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        if (this.d != null) {
            this.d.onScrollY(i2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
