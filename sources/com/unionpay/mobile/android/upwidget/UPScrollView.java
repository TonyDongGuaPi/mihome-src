package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import java.lang.ref.WeakReference;

public class UPScrollView extends ScrollView {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public WeakReference<a> f9713a;
    /* access modifiers changed from: private */
    public int b;
    private ViewTreeObserver.OnGlobalLayoutListener c;
    /* access modifiers changed from: private */
    public Handler d;

    public interface a {
        void e(int i);
    }

    public UPScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public UPScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UPScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new v(this);
        this.c = new u(this);
    }

    public final void a(a aVar) {
        this.f9713a = new WeakReference<>(aVar);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this.c);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this.c);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(this.f9713a == null || this.f9713a.get() == null)) {
            int scrollY = getScrollY();
            this.b = scrollY;
            ((a) this.f9713a.get()).e(scrollY);
        }
        if (motionEvent.getAction() == 1) {
            this.d.sendMessageDelayed(this.d.obtainMessage(), 5);
        }
        return super.onTouchEvent(motionEvent);
    }
}
