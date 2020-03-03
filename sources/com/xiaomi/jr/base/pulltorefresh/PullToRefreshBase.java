package com.xiaomi.jr.base.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.xiaomi.jr.base.pulltorefresh.HeaderLoadingLayout;

public abstract class PullToRefreshBase<T extends View> extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10336a = 350;
    private static final float b = 2.5f;
    private float c = -1.0f;
    /* access modifiers changed from: private */
    public OnRefreshListener d;
    /* access modifiers changed from: private */
    public HeaderLoadingLayout e;
    /* access modifiers changed from: private */
    public int f;
    private boolean g = true;
    private boolean h = true;
    private boolean i = false;
    private int j;
    private HeaderLoadingLayout.State k = HeaderLoadingLayout.State.NONE;
    private PullToRefreshBase<T>.SmoothScrollRunnable l;
    private FrameLayout m;
    protected T mRefreshableView;

    public interface OnRefreshListener<V extends View> {
        void onPullDownToRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    private void a(HeaderLoadingLayout.State state, boolean z) {
    }

    private long getSmoothScrollDuration() {
        return 350;
    }

    /* access modifiers changed from: protected */
    public abstract T createRefreshableView(Context context, AttributeSet attributeSet);

    /* access modifiers changed from: protected */
    public abstract boolean isReadyForPullDown();

    public PullToRefreshBase(Context context) {
        super(context);
        a(context, (AttributeSet) null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setOrientation(1);
        this.j = ViewConfiguration.get(context).getScaledTouchSlop() + 70;
        this.e = b(context, attributeSet);
        this.mRefreshableView = createRefreshableView(context, attributeSet);
        if (this.mRefreshableView != null) {
            a(context, this.mRefreshableView);
            a(context);
            return;
        }
        throw new NullPointerException("Refreshable view can not be null.");
    }

    private void a() {
        int i2 = 0;
        int contentSize = this.e != null ? this.e.getContentSize() : 0;
        if (contentSize < 0) {
            contentSize = 0;
        }
        this.f = contentSize;
        if (this.e != null) {
            i2 = this.e.getMeasuredHeight();
        }
        int paddingLeft = getPaddingLeft();
        getPaddingTop();
        setPadding(paddingLeft, -i2, getPaddingRight(), getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        a();
        a(i2, i3);
        post(new Runnable() {
            public void run() {
                PullToRefreshBase.this.requestLayout();
            }
        });
    }

    public void setOrientation(int i2) {
        if (1 == i2) {
            super.setOrientation(i2);
            return;
        }
        throw new IllegalArgumentException("This class only supports VERTICAL orientation.");
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!d() || !isPullRefreshEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.i = false;
            return false;
        } else if (action != 0 && this.i) {
            return true;
        } else {
            if (action == 0) {
                this.c = motionEvent.getY();
                this.i = false;
            } else if (action == 2) {
                float y = motionEvent.getY() - this.c;
                if (Math.abs(y) > ((float) this.j) || isPullRefreshing()) {
                    this.c = motionEvent.getY();
                    if (isPullRefreshEnabled() && isReadyForPullDown()) {
                        if (Math.abs(getScrollYValue()) > 0 || y > 0.5f) {
                            z = true;
                        }
                        this.i = z;
                        if (this.i) {
                            this.mRefreshableView.onTouchEvent(motionEvent);
                        }
                    }
                }
            }
            return this.i;
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        switch (motionEvent.getAction()) {
            case 0:
                this.c = motionEvent.getY();
                this.i = false;
                break;
            case 1:
            case 3:
                if (this.i) {
                    this.i = false;
                    if (isReadyForPullDown()) {
                        if (!this.g || this.k != HeaderLoadingLayout.State.RELEASE_TO_REFRESH) {
                            z = false;
                        } else {
                            c();
                        }
                        b();
                        return z;
                    }
                }
                break;
            case 2:
                float y = motionEvent.getY() - this.c;
                this.c = motionEvent.getY();
                if (!isPullRefreshEnabled() || !isReadyForPullDown()) {
                    this.i = false;
                    break;
                } else {
                    a(y / b);
                    return true;
                }
        }
        return false;
    }

    public void setPullRefreshEnabled(boolean z) {
        this.g = z;
    }

    public boolean isPullRefreshEnabled() {
        return this.g && this.e != null;
    }

    public void setOnRefreshListener(OnRefreshListener<T> onRefreshListener) {
        this.d = onRefreshListener;
    }

    public void onPullDownRefreshComplete() {
        if (isPullRefreshing()) {
            this.k = HeaderLoadingLayout.State.RESET;
            a(HeaderLoadingLayout.State.RESET, true);
            postDelayed(new Runnable() {
                public void run() {
                    PullToRefreshBase.this.setInterceptTouchEventEnabled(true);
                    PullToRefreshBase.this.e.setState(HeaderLoadingLayout.State.RESET);
                }
            }, getSmoothScrollDuration());
            b();
            setInterceptTouchEventEnabled(false);
        }
    }

    public T getRefreshableView() {
        return this.mRefreshableView;
    }

    public HeaderLoadingLayout getHeaderLoadingLayout() {
        return this.e;
    }

    public void doPullRefreshing(final boolean z, long j2) {
        postDelayed(new Runnable() {
            public void run() {
                int i = -PullToRefreshBase.this.f;
                int i2 = z ? PullToRefreshBase.f10336a : 0;
                PullToRefreshBase.this.c();
                PullToRefreshBase.this.a(i, (long) i2, 0);
            }
        }, j2);
    }

    private HeaderLoadingLayout b(Context context, AttributeSet attributeSet) {
        return new HeaderLoadingLayout(context, attributeSet);
    }

    private void a(int i2, int i3) {
        if (this.m != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.m.getLayoutParams();
            if (layoutParams.height != i3) {
                layoutParams.height = i3;
                this.m.requestLayout();
            }
        }
    }

    private void a(Context context, T t) {
        this.m = new FrameLayout(context);
        this.m.addView(t, -1, -1);
        addView(this.m, new LinearLayout.LayoutParams(-1, 10));
    }

    private void a(Context context) {
        HeaderLoadingLayout headerLoadingLayout = this.e;
        if (headerLoadingLayout != null) {
            if (this == headerLoadingLayout.getParent()) {
                removeView(headerLoadingLayout);
            }
            addView(headerLoadingLayout, 0);
        }
    }

    private void a(float f2) {
        int scrollYValue = getScrollYValue();
        if (f2 >= 0.0f || ((float) scrollYValue) - f2 < 0.0f) {
            c(0, -((int) f2));
            int abs = Math.abs(getScrollYValue());
            if (isPullRefreshEnabled() && !isPullRefreshing()) {
                if (abs > this.f) {
                    this.k = HeaderLoadingLayout.State.RELEASE_TO_REFRESH;
                } else {
                    this.k = HeaderLoadingLayout.State.PULL_TO_REFRESH;
                }
                this.e.setState(this.k);
                a(this.k, true);
                return;
            }
            return;
        }
        b(0, 0);
    }

    private void b() {
        int abs = Math.abs(getScrollYValue());
        boolean isPullRefreshing = isPullRefreshing();
        if (isPullRefreshing && abs <= this.f) {
            a(0);
        } else if (isPullRefreshing) {
            a(-this.f);
        } else {
            a(0);
        }
    }

    public boolean isPullRefreshing() {
        return this.k == HeaderLoadingLayout.State.REFRESHING;
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!isPullRefreshing()) {
            this.k = HeaderLoadingLayout.State.REFRESHING;
            a(HeaderLoadingLayout.State.REFRESHING, true);
            if (this.e != null) {
                this.e.setState(HeaderLoadingLayout.State.REFRESHING);
            }
            if (this.d != null) {
                postDelayed(new Runnable() {
                    public void run() {
                        PullToRefreshBase.this.d.onPullDownToRefresh(PullToRefreshBase.this);
                    }
                }, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2, int i3) {
        scrollTo(i2, i3);
    }

    private void c(int i2, int i3) {
        scrollBy(i2, i3);
    }

    private int getScrollYValue() {
        return getScrollY();
    }

    private void a(int i2) {
        a(i2, getSmoothScrollDuration(), 0);
    }

    /* access modifiers changed from: private */
    public void a(int i2, long j2, long j3) {
        if (this.l != null) {
            this.l.a();
        }
        int scrollYValue = getScrollYValue();
        boolean z = scrollYValue != i2;
        if (z) {
            this.l = new SmoothScrollRunnable(scrollYValue, i2, j2);
        }
        if (!z) {
            return;
        }
        if (j3 > 0) {
            postDelayed(this.l, j3);
        } else {
            post(this.l);
        }
    }

    /* access modifiers changed from: private */
    public void setInterceptTouchEventEnabled(boolean z) {
        this.h = z;
    }

    private boolean d() {
        return this.h;
    }

    final class SmoothScrollRunnable implements Runnable {
        private final Interpolator b;
        private final int c;
        private final int d;
        private final long e;
        private boolean f = true;
        private long g = -1;
        private int h = -1;

        public SmoothScrollRunnable(int i, int i2, long j) {
            this.d = i;
            this.c = i2;
            this.e = j;
            this.b = new DecelerateInterpolator();
        }

        public void run() {
            if (this.e <= 0) {
                PullToRefreshBase.this.b(0, this.c);
                return;
            }
            if (this.g == -1) {
                this.g = System.currentTimeMillis();
            } else {
                this.h = this.d - Math.round(((float) (this.d - this.c)) * this.b.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.g) * 1000) / this.e, 1000), 0)) / 1000.0f));
                PullToRefreshBase.this.b(0, this.h);
            }
            if (this.f && this.c != this.h) {
                PullToRefreshBase.this.postDelayed(this, 16);
            }
        }

        public void a() {
            this.f = false;
            PullToRefreshBase.this.removeCallbacks(this);
        }
    }
}
