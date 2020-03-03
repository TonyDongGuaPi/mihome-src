package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class DetectScrollListView extends ListView {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public AbsListView.OnScrollListener f18813a;
    /* access modifiers changed from: private */
    public OnDetectScrollListener b;

    public interface OnDetectScrollListener {
        void a();

        void b();

        void c();

        void d();
    }

    public DetectScrollListView(Context context) {
        super(context);
        a();
    }

    public DetectScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public DetectScrollListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        b();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        GestureListener() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (f2 <= 0.0f) {
                return false;
            }
            DetectScrollListView.this.b.b();
            return false;
        }
    }

    private void b() {
        super.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int b = 0;
            private int c = 0;

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (DetectScrollListView.this.f18813a != null) {
                    DetectScrollListView.this.f18813a.onScrollStateChanged(absListView, i);
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (DetectScrollListView.this.f18813a != null) {
                    DetectScrollListView.this.f18813a.onScroll(absListView, i, i2, i3);
                }
                if (DetectScrollListView.this.b != null) {
                    a(absListView, i, i2, i3);
                }
            }

            private void a(AbsListView absListView, int i, int i2, int i3) {
                int i4 = 0;
                View childAt = absListView.getChildAt(0);
                if (childAt != null) {
                    i4 = childAt.getTop();
                }
                if (i == this.c) {
                    if (i4 > this.b) {
                        a(i, i2, i3);
                    } else if (i4 < this.b) {
                        b(i, i2, i3);
                    }
                } else if (i < this.c) {
                    a(i, i2, i3);
                } else {
                    b(i, i2, i3);
                }
                this.b = i4;
                this.c = i;
            }

            private void a(int i, int i2, int i3) {
                DetectScrollListView.this.b.a();
                if (i == 0) {
                    DetectScrollListView.this.b.c();
                }
            }

            private void b(int i, int i2, int i3) {
                if (i + i2 == i3) {
                    DetectScrollListView.this.b.d();
                }
            }
        });
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.f18813a = onScrollListener;
    }

    public void setOnDetectScrollListener(OnDetectScrollListener onDetectScrollListener) {
        this.b = onDetectScrollListener;
    }
}
