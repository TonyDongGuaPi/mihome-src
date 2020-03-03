package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;

public class TitleBarScrollView extends ScrollView implements NestedScrollingParent {

    /* renamed from: a  reason: collision with root package name */
    private int f18968a = 0;
    private int b = 0;
    private WebView c;
    private View d;
    private boolean e = true;
    private float f;
    private boolean g;
    private NestedScrollingParentHelper h = new NestedScrollingParentHelper(this);

    private int a(int i) {
        return 0;
    }

    public boolean isTouchTop() {
        return false;
    }

    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return !z;
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public void onStopNestedScroll(View view) {
    }

    public TitleBarScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TitleBarScrollView(Context context) {
        super(context);
    }

    public void setTitleBar(View view) {
        this.d = view;
    }

    public void setWebView(WebView webView) {
        this.c = webView;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        scrollTo(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        scrollTo(0, 0);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    if (this.f18968a == 0) {
                        this.f18968a = this.d.getHeight();
                    }
                    if (this.b == 0) {
                        this.b = this.c.getHeight();
                        break;
                    }
                    break;
                case 1:
                    break;
            }
        }
        if (this.e) {
            this.e = false;
            return true;
        }
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.h.onNestedScrollAccepted(view, view2, i);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            int a2 = a(i2);
            iArr[0] = 0;
            iArr[1] = a2;
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        super.setNestedScrollingEnabled(z);
    }
}
