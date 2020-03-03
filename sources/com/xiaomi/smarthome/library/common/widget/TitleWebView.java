package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

public class TitleWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private int f18969a;
    private boolean b;

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public TitleWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = 0;
        View childAt = getChildAt(0);
        if (childAt != null) {
            i3 = childAt.getMeasuredHeight();
        }
        this.f18969a = i3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r0 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getActionMasked()
            r1 = 3
            r2 = 0
            if (r0 == r1) goto L_0x0020
            switch(r0) {
                case 0: goto L_0x000c;
                case 1: goto L_0x0020;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x001e
        L_0x000c:
            float r0 = r5.getY()
            int r1 = r4.a()
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x001b
            r0 = 1
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            r4.b = r0
        L_0x001e:
            r0 = 0
            goto L_0x0024
        L_0x0020:
            boolean r0 = r4.b
            r4.b = r2
        L_0x0024:
            boolean r1 = r4.b
            r3 = 0
            if (r1 != 0) goto L_0x002b
            if (r0 == 0) goto L_0x003e
        L_0x002b:
            android.view.View r0 = r4.getChildAt(r2)
            if (r0 == 0) goto L_0x003e
            int r1 = r4.getScrollY()
            float r1 = (float) r1
            r5.offsetLocation(r3, r1)
            boolean r5 = r0.dispatchTouchEvent(r5)
            return r5
        L_0x003e:
            int r0 = r4.f18969a
            int r0 = -r0
            float r0 = (float) r0
            r5.offsetLocation(r3, r0)
            boolean r5 = super.dispatchTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.TitleWebView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private int a() {
        return this.f18969a - getScrollY();
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.offsetLeftAndRight(i - childAt.getLeft());
        }
        float contentHeight = (((float) getContentHeight()) * getScale()) - ((float) (getHeight() + getScrollY()));
        if (contentHeight == 0.0f && this.f18969a != 0) {
            System.out.println("WebView滑动到了底端");
            this.f18969a = 0;
        }
        if (contentHeight >= ((float) childAt.getMeasuredHeight()) && this.f18969a == 0) {
            System.out.println("WebView滑动到了底端");
            this.f18969a = childAt.getMeasuredHeight();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.save();
        int a2 = a();
        if (a2 > 0) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            canvas.clipRect(scrollX, a2 + scrollY, getWidth() + scrollX, scrollY + getHeight());
        }
        canvas.translate(0.0f, (float) this.f18969a);
        super.onDraw(canvas);
        canvas.restore();
    }
}
