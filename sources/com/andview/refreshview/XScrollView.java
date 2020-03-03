package com.andview.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;
import com.andview.refreshview.XRefreshView;

public class XScrollView extends ScrollView {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public OnScrollListener f4776a;
    /* access modifiers changed from: private */
    public OnScrollListener b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public int d;
    private XRefreshView e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public float g;
    /* access modifiers changed from: private */
    public Runnable h;

    public interface OnScrollListener {
        public static final int b = 0;
        public static final int c = 1;
        public static final int d = 2;

        void a(int i, int i2, int i3, int i4);

        void a(ScrollView scrollView, int i, boolean z);
    }

    public XScrollView(Context context) {
        super(context, (AttributeSet) null);
        this.c = false;
        this.d = 0;
        this.h = new Runnable() {
            public void run() {
                if (XScrollView.this.d == XScrollView.this.getScrollY() && !XScrollView.this.c) {
                    XScrollView.this.f4776a.a(XScrollView.this, 0, XScrollView.this.a());
                    if (XScrollView.this.b != null) {
                        XScrollView.this.b.a(XScrollView.this, 0, XScrollView.this.a());
                    }
                }
            }
        };
    }

    public XScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.d = 0;
        this.h = new Runnable() {
            public void run() {
                if (XScrollView.this.d == XScrollView.this.getScrollY() && !XScrollView.this.c) {
                    XScrollView.this.f4776a.a(XScrollView.this, 0, XScrollView.this.a());
                    if (XScrollView.this.b != null) {
                        XScrollView.this.b.a(XScrollView.this, 0, XScrollView.this.a());
                    }
                }
            }
        };
        this.f = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f4776a != null) {
            if (this.c) {
                if (i2 != i4) {
                    this.f4776a.a(this, 1, a());
                    if (this.b != null) {
                        this.b.a(this, 1, a());
                    }
                }
            } else if (i2 != i4) {
                this.f4776a.a(this, 2, a());
                if (this.b != null) {
                    this.b.a(this, 2, a());
                }
                this.d = i2;
                removeCallbacks(this.h);
                postDelayed(this.h, 20);
            }
            this.f4776a.a(i, i2, i3, i4);
            if (this.b != null) {
                this.b.a(i, i2, i3, i4);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean a() {
        return getScrollY() + getHeight() >= computeVerticalScrollRange();
    }

    /* access modifiers changed from: protected */
    public void setOnScrollListener(XRefreshView xRefreshView, OnScrollListener onScrollListener) {
        this.e = xRefreshView;
        this.f4776a = onScrollListener;
        this.e.addTouchLifeCycle(new XRefreshView.TouchLifeCycle() {
            public void a(MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        float unused = XScrollView.this.g = motionEvent.getRawY();
                        break;
                    case 1:
                    case 3:
                        boolean unused2 = XScrollView.this.c = false;
                        int unused3 = XScrollView.this.d = XScrollView.this.getScrollY();
                        if (XScrollView.this.g - motionEvent.getRawY() >= ((float) XScrollView.this.f)) {
                            XScrollView.this.removeCallbacks(XScrollView.this.h);
                            XScrollView.this.postDelayed(XScrollView.this.h, 20);
                            return;
                        }
                        return;
                    case 2:
                        break;
                    default:
                        return;
                }
                boolean unused4 = XScrollView.this.c = true;
            }
        });
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.b = onScrollListener;
    }
}
