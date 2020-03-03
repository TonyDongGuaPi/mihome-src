package com.mi.global.shop.widget.slide;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

public class SlideView extends RelativeLayout {
    public static final int AUTO_DOWN = 1;
    public static final int AUTO_UP = 0;
    public static final int DONE = 2;

    /* renamed from: a  reason: collision with root package name */
    private static String f7296a = "SlideView";
    private VelocityTracker b;
    /* access modifiers changed from: private */
    public int c = 0;
    /* access modifiers changed from: private */
    public int d;
    private float e;
    /* access modifiers changed from: private */
    public int f = 2;
    /* access modifiers changed from: private */
    public SlideListener g;
    private final Runnable h = new Runnable() {
        public void run() {
            SlideView.this.measure(View.MeasureSpec.makeMeasureSpec(SlideView.this.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(SlideView.this.getHeight(), 1073741824));
            SlideView.this.layout(SlideView.this.getLeft(), SlideView.this.getTop(), SlideView.this.getRight(), SlideView.this.getBottom());
        }
    };

    public interface SlideListener {
        void a(int i);
    }

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SlideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setListener(SlideListener slideListener) {
        this.g = slideListener;
    }

    public int getCurrentViewIndex() {
        return this.c;
    }

    public void setCurrentViewIndex(int i) {
        SmoothScrollRunnable smoothScrollRunnable;
        if (this.c != i) {
            if (i == 0) {
                smoothScrollRunnable = new SmoothScrollRunnable(-getMeasuredHeight(), 0);
            } else {
                smoothScrollRunnable = new SmoothScrollRunnable(0, -getMeasuredHeight());
            }
            post(smoothScrollRunnable);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                if (this.b == null) {
                    this.b = VelocityTracker.obtain();
                } else {
                    this.b.clear();
                }
                this.b.addMovement(motionEvent);
                this.e = motionEvent.getY();
                break;
            case 1:
                this.e = motionEvent.getY();
                this.b.addMovement(motionEvent);
                this.b.computeCurrentVelocity(1000);
                float yVelocity = this.b.getYVelocity();
                if (!(this.d == 0 || this.d == (-getMeasuredHeight()))) {
                    if (Math.abs(yVelocity) < 500.0f) {
                        if (this.c == 0) {
                            if (Math.abs(this.d) > getMeasuredHeight() / 3) {
                                this.f = 0;
                            } else {
                                this.f = 1;
                            }
                        } else if (Math.abs(this.d) < (getMeasuredHeight() / 3) * 2) {
                            this.f = 1;
                        } else {
                            this.f = 0;
                        }
                    } else if (yVelocity < 0.0f) {
                        this.f = 0;
                    } else {
                        this.f = 1;
                    }
                    this.b.clear();
                    smoothScroll();
                    break;
                }
            case 2:
                this.b.addMovement(motionEvent);
                if (this.c == 0 && SlideUtil.b(getChildAt(this.c))) {
                    this.d = (int) (((float) this.d) + (motionEvent.getY() - this.e));
                    if (this.d > 0) {
                        this.d = 0;
                        this.c = 0;
                    } else if (this.d < (-getMeasuredHeight())) {
                        this.d = -getMeasuredHeight();
                        this.c = 1;
                    }
                    if (this.d < -8) {
                        motionEvent.setAction(3);
                    }
                } else if (this.c == 1 && SlideUtil.a(getChildAt(this.c))) {
                    this.d = (int) (((float) this.d) + (motionEvent.getY() - this.e));
                    if (this.d < (-getMeasuredHeight())) {
                        this.d = -getMeasuredHeight();
                        this.c = 1;
                    } else if (this.d > 0) {
                        this.d = 0;
                        this.c = 0;
                    }
                    if (this.d > 8 - getMeasuredHeight()) {
                        motionEvent.setAction(3);
                    }
                }
                this.e = motionEvent.getY();
                requestLayout();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void smoothScroll() {
        if (Math.abs(this.d) != 0 && Math.abs(this.d) != getMeasuredHeight()) {
            if (this.f == 0) {
                post(new SmoothScrollRunnable(this.d, -getMeasuredHeight()));
            } else if (this.f == 1) {
                post(new SmoothScrollRunnable(this.d, 0));
            }
        }
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.h);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        View childAt2 = getChildAt(1);
        if (childAt != null && childAt2 != null) {
            childAt.layout(0, this.d, getMeasuredWidth(), childAt.getMeasuredHeight() + this.d);
            childAt2.layout(0, childAt.getMeasuredHeight() + this.d, getMeasuredWidth(), childAt.getMeasuredHeight() + this.d + childAt2.getMeasuredHeight());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    final class SmoothScrollRunnable implements Runnable {
        private final Interpolator b;
        private final int c;
        private final int d;
        private final long e;
        private long f = -1;
        private int g = -1;

        public SmoothScrollRunnable(int i, int i2) {
            this.d = i;
            this.c = i2;
            this.b = new DecelerateInterpolator();
            this.e = (long) (Math.abs(i2 - i) / 8);
        }

        public void run() {
            if (this.f == -1) {
                this.f = System.currentTimeMillis();
            } else {
                this.g = this.d - Math.round(((float) (this.d - this.c)) * this.b.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.f) * 1000) / this.e, 1000), 0)) / 1000.0f));
                int unused = SlideView.this.d = this.g;
                SlideView.this.requestLayout();
            }
            if (this.c != this.g) {
                ViewCompat.postOnAnimation(SlideView.this, this);
                return;
            }
            int unused2 = SlideView.this.f = 2;
            if (this.c == 0) {
                if (!(SlideView.this.c == 0 && SlideView.this.g == null)) {
                    SlideView.this.g.a(0);
                }
                int unused3 = SlideView.this.c = 0;
                return;
            }
            if (SlideView.this.c == 0 || SlideView.this.g != null) {
                SlideView.this.g.a(1);
            }
            int unused4 = SlideView.this.c = 1;
        }
    }
}
